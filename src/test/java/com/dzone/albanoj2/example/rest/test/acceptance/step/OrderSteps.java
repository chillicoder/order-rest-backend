package com.dzone.albanoj2.example.rest.test.acceptance.step;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.dzone.albanoj2.example.rest.repository.OrderRepository;
import com.dzone.albanoj2.example.rest.test.acceptance.util.AbstractSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderSteps extends AbstractSteps {
	
	private Long lastCreatedId;
	
	@Autowired
	private OrderRepository orders;

	@Given("^no orders are present$")
	public void noOrdersArePresent() throws Throwable {
		givenNumberOfOrdersArePresent(0);
	}
	
	@When("^the order calls /order$")
	public void theUserCallsGetOrders() throws Throwable {
		get("/order");
	}
	
	@And("^(\\d+) orders are present$")
	public void givenNumberOfOrdersArePresent(int count) throws Throwable {
		Assert.assertEquals(count, orders.getCount());
	}
	 
	@Then("^the order receives status code of (\\d+)$")
	public void theUserReceivesStatusCodeOf(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, getLastGetResponse().getStatus());
	}
	
	@And("^the list of order is empty$")
	public void testListOfOrdersIsEmpty() throws Throwable {
		Assert.assertEquals("[]", getLastGetResponse().getContentAsString());
	}
}
