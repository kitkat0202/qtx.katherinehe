package API_Homework;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class RestApi {
	// 1. (GET single user) Write a test that verifies the email address for employee id 2 is 'janet.weaver@reqres.in'
//	@Test
	public void getSingleUser() {
		String endpoint = "https://reqres.in/api/users/2";
		String expectedCode = "janet.weaver@reqres.in";

		given()
		.when()
		.get(endpoint)
		.then()
		.contentType(ContentType.JSON)
		.statusCode(200)
		.body("data.email", equalTo(expectedCode));
	}

	
	//	2. (POST login - succesful) Write a test that verifies the token return value.
//	@Test
	public void postLoginSuccess() {
		String json = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
		String endpoint = "https://reqres.in/api/login";
		String expectedCode = "QpwL5tke4Pnpja7X4";

		given()
			.contentType("application/json")
			.body(json)
		.when()
			.post(endpoint)
		.then()
			.statusCode(200)
			.contentType("application/json")
			.body("token", equalTo(expectedCode));
	}

	
	//	3. (DELETE)
	//	a. Write a test that verifies the response code of 204.
	//	b. Lookup response code 204 and add it to your test as a comment.

	/*
	 * The HTTP 204 No Content success status response code indicates that the request has succeeded, 
	 * but that the client doesn't need to go away from its current page. A 204 response is cacheable by default. 
	 * An ETag header is included in such a response
	 */
//	@Test
	public void DeleteUser() {
		String endpoint = "https://reqres.in/api/users/2";
		given()
		.when()
			.delete(endpoint)
		.then()
			.statusCode(equalTo(204));
	}

	
	
	//	4. (PATCH) Write a test that updates 'Morpheus' to 'Morpheus2'. Ensure that UpdatedAt timestamp is updated.
//	@Test
	public void PatchUser() {
		String endpoint = "https://reqres.in/api/users/2";
		
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		
		String json = "{\"name\": \"morpheus2\", \"updatedAt\": \"" + currentTimestamp + "\"}";
		
		given()
			.contentType("application/json")
			.body(json)
		.when()
			.patch(endpoint)
		.then()
			.statusCode(200)
			.contentType("application/json")
			.body("name", equalTo("morpheus2"))
			.body("updatedAt", equalTo(currentTimestamp));
	}
	

	//	5. (PUT) Write a test that updates 'Morpheus' to 'Morpheus2'. Ensure that UpdatedAt timestamp is updated.
//	@Test
	public void PutUser() {
		String endpoint = "https://reqres.in/api/users/2";
		
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		
		String json = "{\"name\": \"morpheus2\", \"updatedAt\": \"" + currentTimestamp + "\"}";
		
		given()
			.contentType("application/json")
			.body(json)
		.when()
			.put(endpoint)
		.then()
			.statusCode(200)
			.contentType("application/json")
			.body("name", equalTo("morpheus2"))
			.body("updatedAt", equalTo(currentTimestamp));
	}

	
	//	6. (GET single user). Deserialize the user data into an object.
//	@Test
	public void getSingleAndDeserialize() {
		String endpoint = "https://reqres.in/api/users/2";
		String expectedEmail = "janet.weaver@reqres.in";
		String expectedCmpany = "StatusCode Weekly";
		
		JsonPath json = given()
				.when()
					.get(endpoint)
				.then()
					.statusCode(200)
					.contentType("application/json")
				.extract()
					.jsonPath();
		
		
		HashMap<String, Object> data = json.get("data");
		HashMap<String, Object> ads = json.get("ad");
		
		Person p = new Person(Integer.parseInt(data.get("id").toString()), data.get("email").toString(), data.get("first_name").toString(), data.get("last_name").toString(), data.get("avatar").toString());
		Ad a = new Ad(ads.get("company").toString(), ads.get("url").toString(), ads.get("text").toString());
		
		Assert.assertEquals(p.getEmail(), expectedEmail, "getSingleAndDeserialize data method failed");
		Assert.assertEquals(a.getCompany(), expectedCmpany, "getSingleAndDeserialize ad method failed");
	}

	//	7. (GET list users). Deserialize the user data into a collection of objects.
	@Test
	public void c() {
		ArrayList<Person> pList = new ArrayList<Person>();
		ArrayList<Ad> adList = new ArrayList<Ad>();
		
		String endpoint = "https://reqres.in/api/users?page=2";
		String expectedEmail = "michael.lawson@reqres.in";
		String expectedCompany = "StatusCode Weekly";
		
		JsonPath json = given()
				.when()
					.get(endpoint)
				.then()
					.statusCode(200)
					.contentType("application/json")
				.extract()
					.jsonPath();
		
		List<HashMap<String, Object>> DataList = json.getList("data");
		List<HashMap<String, Object>> AdList = json.getList("ad");
		
		for (HashMap<String, Object> data : DataList) {
			pList.add(new Person(Integer.parseInt(data.get("id").toString()), data.get("email").toString(), data.get("first_name").toString(), data.get("last_name").toString(), data.get("avatar").toString()));
		}
		
		for (HashMap<String, Object> ads : AdList) {
			adList.add(new Ad(ads.get("company").toString(), ads.get("url").toString(), ads.get("text").toString()));
		}

		Assert.assertEquals(pList.get(0).getEmail(), expectedEmail, "getSingleAndDeserialize data method failed");
		Assert.assertEquals(adList.get(0).getCompany(), expectedCompany, "getSingleAndDeserialize ad method failed");

	
	}
}
