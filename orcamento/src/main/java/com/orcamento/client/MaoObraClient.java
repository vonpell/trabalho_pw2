package com.orcamento.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.orcamento.dto.MaoObraDTO;

@Path("/mao-obra")
@RegisterRestClient(configKey = "maoobra-api")
public interface MaoObraClient {

    @GET
    @Path("/{id}")
    MaoObraDTO buscarPorId(@PathParam("id") Long id);
}