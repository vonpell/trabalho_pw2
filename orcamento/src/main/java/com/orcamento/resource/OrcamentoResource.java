package com.orcamento.resource;

import com.orcamento.dto.OrcamentoDTO;
import com.orcamento.model.Orcamento;
import com.orcamento.service.OrcamentoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/orcamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrcamentoResource {

    @Inject
    OrcamentoService service;

    @POST
    public Response criar(OrcamentoDTO dto) {
        try {
            Orcamento orcamento = service.criar(dto);
            return Response.status(Response.Status.CREATED).entity(orcamento).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity("Erro ao criar orçamento: " + e.getMessage())
                          .build();
        }
    }

    @GET
    public Response listarTodos() {
        List<Orcamento> orcamentos = service.listarTodos();
        return Response.ok(orcamentos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Optional<Orcamento> orcamento = service.buscarPorId(id);
        if (orcamento.isPresent()) {
            return Response.ok(orcamento.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                      .entity("Orçamento não encontrado")
                      .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, OrcamentoDTO dto) {
        try {
            Orcamento orcamento = service.atualizar(id, dto);
            if (orcamento != null) {
                return Response.ok(orcamento).build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("Orçamento não encontrado")
                          .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity("Erro ao atualizar orçamento: " + e.getMessage())
                          .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                      .entity("Orçamento não encontrado")
                      .build();
    }
}
