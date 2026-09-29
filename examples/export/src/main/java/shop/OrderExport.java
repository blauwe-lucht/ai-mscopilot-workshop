package shop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.util.List;

public class OrderExport {

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final CsvMapper csvMapper = new CsvMapper();

    public String toJson(List<Order> orders) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(orders);
    }

    public String toCsv(List<Order> orders) throws JsonProcessingException {
        CsvSchema schema = csvMapper.schemaFor(Order.class).withHeader();
        return csvMapper.writer(schema).writeValueAsString(orders);
    }
}
