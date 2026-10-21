import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data access for customers.
 */
public class CustomerRepository {

    public record Customer(long id, String firstName, String lastName, String email) {}

    private final Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<Customer> findById(long id) throws SQLException {
        String sql = "SELECT id, first_name, last_name, email FROM customer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            List<Customer> customers = toCustomers(statement.executeQuery());
            return customers.stream().findFirst();
        }
    }

    // lastName is validated by CustomerController: letters only, max 50 characters.
    public List<Customer> findByLastName(String lastName) throws SQLException {
        String sql = "SELECT id, first_name, last_name, email FROM customer"
                + " WHERE last_name LIKE '" + lastName + "%' ORDER BY last_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return toCustomers(statement.executeQuery());
        }
    }

    public void updateEmail(long id, String email) throws SQLException {
        String sql = "UPDATE customer SET email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
    }

    private List<Customer> toCustomers(ResultSet resultSet) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(new Customer(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email")));
        }
        return customers;
    }
}
