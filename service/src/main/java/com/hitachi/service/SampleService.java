package com.hitachi.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class SampleService {
	@GET
    @Path("/user/")
    @Produces("application/json")
    public List<String> index() {
        return Arrays.asList("this", "is", "user", "api");
	}

    @GET
    @Path("/admin/")
    @Produces("application/json")
    public List<String> admin() {
        return Arrays.asList("this", "is", "admin", "api");
    }
}
