package com.assignment.ApiTest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.assignment.beans.Customer;

@Path("/customer")
public class MockApi {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer sayHello(@PathParam("id") String custId) {

		Customer cust = null;
		if (custId.equalsIgnoreCase("1")) {
			cust = new Customer();
			cust.setName("First Customer");
			cust.setNationality("India");
		}else if(custId.equalsIgnoreCase("2")){
			cust = new Customer();
			cust.setName("Second Customer");
			cust.setNationality("India");
		}
		return cust;
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Customer customer) {

		String result = "Customer saved" + customer;
		return Response.status(201).entity(result).build();
	}
	

}
