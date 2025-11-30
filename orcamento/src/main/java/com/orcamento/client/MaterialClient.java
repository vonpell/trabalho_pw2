package com.orcamento.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/materiais")
@RegisterRestClient(configKey = "material-api")
public interface MaterialClient {

    @GET
    @Path("/{id}")
    MaterialDTO buscarPorId(@PathParam("id") Long id);
}