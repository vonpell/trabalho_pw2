package com.maoDeObra.resource;

import com.maoDeObra.model.MaoDeObra;
import com.maoDeObra.service.MaoDeObraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/mao-de-obra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaoDeObraResource {

    @Inject
    MaoDeObraService service;

    @POST
    public Response criar(MaoDeObra item) {
        MaoDeObra criado = service.criar(item);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @GET
    public List<MaoDeObra> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") String id) {
        return service.buscarPorId(id)
                .map(item -> Response.ok(item).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") String id, MaoDeObra item) {
        return service.atualizar(id, item)
                .map(atualizado -> Response.ok(atualizado).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") String id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}