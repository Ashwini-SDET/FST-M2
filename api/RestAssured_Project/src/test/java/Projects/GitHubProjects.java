package Projects;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GitHubProjects {
	// Declare request specification
	RequestSpecification requestSpec;
	// Declare response specification
	ResponseSpecification responseSpec;
	// Global properties
	String sshKey;
	int sshKeyId;
	@BeforeClass
	public void setUp() {
		// Create request specification
		requestSpec = new RequestSpecBuilder()
				// Set content type
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "token ")
				// Set base URL
				.setBaseUri("https://api.github.com")
				// Build request specification
				.build();
		sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCdS7VfH7ZehWbLl0AAFss8JE+deYid8KtF/0rUKn3aqLac0+m3T2ABq8YrHBS0FHopxBgBfE9pBuaMkIiDVAs+InX1MwuDeiLd2JlSF4aPXijjaoLtEL7tGF8EeAZ0tU+kJi80vkYgFkj0WJ6+r9oYj6paisNoHIF9WLycCm6dK763Cc+MOutqAuxsNE1sAl8V017GVSUcGz4tAeE2L8R8IKEIAoNSM/SUs5Ukjt5AIZDE0iiAqLbRfb3h2H8MV+yopdAcX4xNnI2avSotVBTTdFvVu+3hZYO7cmPwVfKkBHMfyhVWYCdSI7NDw3AmSzIirpk0PC6VJRTppIKC";
	}
	@Test(priority = 1)
	// Test case using a DataProvider
	public void addKeys() {
		String reqBody = "{\"title\": \"TestKey\", \"key\": \"" + sshKey + "\" }";
		Response response = given().spec(requestSpec) // Use requestSpec
				.body(reqBody) // Send request body
				.when().post("/user/keys"); // Send POST request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		sshKeyId = response.then().extract().path("id");
		// Assertions
		response.then().statusCode(201);
	}
	@Test(priority = 2)
	// Test case using a DataProvider
	public void getKeys() {
		Response response = given().spec(requestSpec) // Use requestSpec
				.when().get("/user/keys"); // Send GET Request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		// Assertions
		response.then().statusCode(200);
	}
	@Test(priority = 3)
	// Test case using a DataProvider
	public void deleteKeys() {
		Response response = given().spec(requestSpec) // Use requestSpec
				.pathParam("keyId", sshKeyId).when().delete("/user/keys/{keyId}"); // Send GET Request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		// Assertions
		response.then().statusCode(204);
	}
}
