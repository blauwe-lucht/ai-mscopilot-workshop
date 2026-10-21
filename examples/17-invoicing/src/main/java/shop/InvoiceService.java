package shop;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Generates the monthly invoices for all customers that ordered something in
 * the given month, and writes them to the output directory.
 */
public class InvoiceService {

    private static final BigDecimal VAT_RATE = new BigDecimal("0.21");
    private static final BigDecimal GOLD_DISCOUNT_RATE = new BigDecimal("0.05");

    private final Path outputDirectory;
    private int skippedOrders;

    public InvoiceService(Path outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    /**
     * @param ordersFile export from the webshop, see orders.csv for the format
     * @return the number of invoices written
     */
    public int generateMonthlyInvoices(Path ordersFile, YearMonth month) throws IOException {
        Map<String, List<Order>> ordersPerCustomer = readOrders(ordersFile).stream()
                .filter(order -> isInvoiceable(order, month))
                .collect(Collectors.groupingBy(Order::customerId, TreeMap::new, Collectors.toList()));

        for (Map.Entry<String, List<Order>> entry : ordersPerCustomer.entrySet()) {
            String invoice = formatInvoice(entry.getKey(), entry.getValue(), month);
            Path invoiceFile = outputDirectory.resolve("invoice-" + month + "-" + entry.getKey() + ".txt");
            Files.writeString(invoiceFile, invoice);
            System.out.println("Invoice written: " + invoiceFile);
        }
        System.out.println("Skipped orders: " + skippedOrders);
        return ordersPerCustomer.size();
    }

    private List<Order> readOrders(Path ordersFile) throws IOException {
        return Files.readAllLines(ordersFile).stream()
                .skip(1)
                .filter(line -> !line.isBlank())
                .map(Order::parse)
                .toList();
    }

    private boolean isInvoiceable(Order order, YearMonth month) {
        boolean invoiceable = YearMonth.from(order.date()).equals(month) && !order.status().equals("CANCELLED");
        if (!invoiceable) {
            skippedOrders++;
        }
        return invoiceable;
    }

    private String formatInvoice(String customerId, List<Order> orders, YearMonth month) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("INVOICE ").append(month).append('-').append(customerId).append('\n');
        invoice.append("Date: ").append(LocalDate.now()).append("\n\n");
        orders.forEach(order -> invoice.append(String.format("%-8s %-24s %3s x %8s%n",
                order.id(), order.product(), order.quantity(), order.unitPrice())));
        invoice.append('\n');
        appendTotals(invoice, orders, orders.get(0).customerLevel().equals("GOLD"));
        return invoice.toString();
    }

    private void appendTotals(StringBuilder invoice, List<Order> orders, boolean gold) {
        BigDecimal subtotal = orders.stream()
                .map(order -> order.unitPrice().multiply(BigDecimal.valueOf(order.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discount = gold
                ? subtotal.multiply(GOLD_DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        BigDecimal vat = subtotal.subtract(discount).multiply(VAT_RATE).setScale(2, RoundingMode.HALF_UP);

        appendLine(invoice, "Subtotal", subtotal.setScale(2, RoundingMode.HALF_UP));
        if (gold) {
            appendLine(invoice, "Gold discount (5%)", discount.negate());
        }
        appendLine(invoice, "VAT (21%)", vat);
        appendLine(invoice, "Total", subtotal.subtract(discount).add(vat).setScale(2, RoundingMode.HALF_UP));
    }

    private void appendLine(StringBuilder invoice, String label, BigDecimal amount) {
        invoice.append(String.format("%-38s %10s%n", label, amount));
    }

    record Order(String id, LocalDate date, String customerId, String product,
                 int quantity, BigDecimal unitPrice, String status, String customerLevel) {

        static Order parse(String line) {
            String[] fields = line.split(";");
            return new Order(fields[0].trim(), LocalDate.parse(fields[1].trim()), fields[2].trim(),
                    fields[3].trim(), Integer.parseInt(fields[4].trim()), new BigDecimal(fields[5].trim()),
                    fields[6].trim(), fields[7].trim());
        }
    }
}
