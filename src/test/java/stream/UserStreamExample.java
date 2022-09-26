package stream;

import com.salesforce.framework.models.Customer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Створити Customer з полями ім‘я, прізвище.
 * Створити декілька Customer, два з однаковим прізвищем
 * Через стрім відфільтрувати по однакому прізвищу
 * і вивести окремим списком ім‘я відфільтрованих юзерів.
 */

public class UserStreamExample {
    private List<Customer> customers;
    @BeforeMethod
    public void setCustomers(){
        customers = List.of(
                Customer.newBuilder().withName("Jane").withLastName("Smith").build(),
                Customer.newBuilder().withName("Harry").withLastName("Potter").build(),
                Customer.newBuilder().withName("John").withLastName("Smith").build(),
                Customer.newBuilder().withName("Test").withLastName("Test").build()
        );
    }

    @Test
    public void streamTest() {
        List<String> expectedList = List.of("Jane", "John");
        findCustomersNamesWithSameSurname(customers).forEach(System.out::println);
        Assert.assertTrue(findCustomersNamesWithSameSurname(customers).containsAll(expectedList));
    }

    private List<String> findCustomersNamesWithSameSurname(List<Customer> customers) {
        return customers.stream()
                .filter(user -> user.getLastName().equals("Smith"))
                .map(Customer::getName)
                .collect(Collectors.toList());
    }
}

