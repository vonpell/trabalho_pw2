package com.material.resource;

import com.material.model.Material;
import com.material.service.MaterialService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/materiais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaterialResource {

    @Inject
    MaterialService service;

    @POST
    public Response criar(Material material) {
        Material criado = service.criar(material);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @GET
    public List<Material> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") String id) {
        return service.buscarPorId(id)
                .map(material -> Response.ok(material).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") String id, Material material) {
        return service.atualizar(id, material)
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