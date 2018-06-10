package api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class RegresApi {
	
	//send a request to https://regres.in/api/users
	//including query param page=2
	//accept type is json
	//verify status code 200, verify response body
	
	
	@Test
	public void getUsersTest() {
//		given().accept(ContentType.JSON)
//		.and().params("page", 2)
//		.when().get("https://reqres.in/api/users/")
//		.then().assertThat().statusCode(200);
		
		
		Response response=given().accept(ContentType.JSON)
						.and().params("page", 2)
						.when().get("https://reqres.in/api/users/");
		
		System.out.println(response.getStatusLine());
		System.out.println(response.getContentType());
		System.out.println(response.headers());
		System.out.println(response.body().asString());
		
		//add assertions for parts of response
		
		assertEquals(200, response.statusCode());
		assertTrue(response.contentType().contains("application/json"));
		
		
		Header header=new Header("X-Powered-By", "Express");
		assertTrue(response.headers().asList().contains(header));
		
		
		JsonPath json=response.jsonPath();
		assertEquals(12, json.getInt("total"));
		assertEquals(4, json.getInt("total_pages"));
		
		System.out.println(json.getInt("data.id[0]"));
		assertEquals(4, json.getInt("data.id[0]"));
		
		//how to find someone's id in json path
		//this is groovy language
		//we are using json path
		//this is object array
		System.out.println(json.getString("data.find{it.first_name == 'Charles'}.id"));
		assertEquals(5, Integer.parseInt(json.getString("data.find{it.first_name == 'Charles'}.id")));
		
		//assert using jsonpath that person with id 6 first name is tracey 
		//and last name is ramos
		
		assertEquals(6, Integer.parseInt(json.getString("data.find{it.first_name == 'Tracey'}.id")));
		
		
		
		
		
		
		
		
		
	}

}
