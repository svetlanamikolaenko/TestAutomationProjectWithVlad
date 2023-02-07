package trainingxyz;

import com.salesforce.framework.models.Product;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ApiTests {

    @Test
    public void getCategories() {
        String endpoint = "http://localhost:80/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProduct() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response =
                given().
                        queryParam("id", 2).
                        when().
                        get(endpoint).
                        then();
        response.log().body();
    }

    @Test
    public void createProduct() {
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        String body = """
                {
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 12.00,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        Product product = new Product(20 ,"Water Bottle", "Blue water bottle. Holds 64 ounces", 15, 3);
        var response = given().body(product).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = """
                {
                "id": 20
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSweatband() {
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        Product sweatband = new Product("Sweatband", "Sweatband description", 5, 2);
        var response = given().body(sweatband).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateSweatband() {
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        Product sweatband = new Product(21, "Sweatband", "Sweatband description", 6, 3);
        var response = given().body(sweatband).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getSweatband(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response = given().queryParam("id", 21).when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteSweatband() {
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = """
                {
                "id": 21
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void getMultiVitaminProduct() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        Product expectedProduct = new Product(
                18,
                "Multi-Vitamin (90 capsules)",
                "A daily dose of our Multi-Vitamins fulfills a day\u2019s nutritional needs for over 12 vitamins and minerals.",
                10.00,
                4,
                "Supplements"
        );

        Product actualProduct =
                given().
                        queryParam("id", "18").
                when().
                        get(endpoint).
                        as(Product.class);

        assertThat(actualProduct, samePropertyValuesAs(expectedProduct));
    }

    @Test
    public void getMultiVitamin(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";

        given().
                queryParam("id", "18").
        when().
                get(endpoint).
        then().
                assertThat().
                        log().
                        body().
                statusCode(200).
                header("Content-Type", equalTo("application/json")).
                body("id", equalTo("18")).
                body("name", equalTo("Multi-Vitamin (90 capsules)")).
                body("description", equalTo("A daily dose of our Multi-Vitamins fulfills a day\u2019s nutritional needs for over 12 vitamins and minerals.")).
                body("price", equalTo("10.00")).
                body("category_id", equalTo("4")).
                body("category_name", equalTo("Supplements"));
    }
}
