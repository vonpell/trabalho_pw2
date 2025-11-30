package com.orcamento.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.orcamento.dto.MaterialDTO;

@Path("/api/materiais")
@RegisterRestClient(configKey = "material-api")
public interface MaterialClient {

    @GET
    @Path("/{id}")
    MaterialDTO buscarPorId(@PathParam("id") String id);
}