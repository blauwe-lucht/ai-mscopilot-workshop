package shop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderExportTest {

    private final List<Order> orders = List.of(
            new Order("A-1001", "Jansen", new BigDecimal("24.95")),
            new Order("A-1002", "De Vries", new BigDecimal("112.50")));

    @Test
    void exportsOrdersAsJson() throws Exception {
        assertEquals(
                "[{\"id\":\"A-1001\",\"customer\":\"Jansen\",\"total\":24.95},"
                        + "{\"id\":\"A-1002\",\"customer\":\"De Vries\",\"total\":112.50}]",
                new OrderExport().toJson(orders));
    }

    @Test
    void exportsOrdersAsCsv() throws Exception {
        assertEquals(
                "id,customer,total\nA-1001,Jansen,24.95\nA-1002,\"De Vries\",112.50\n",
                new OrderExport().toCsv(orders));
    }
}
