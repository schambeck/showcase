package net.unibave.showcase.base;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public abstract class CrudResource<T extends EntityId, PK> {

    @Inject
    private AbstractRepository<T, PK> repository;

    @Inject
    private AbstractService<T, PK> service;

//    @GET
//    public Response findAll(@QueryParam("firstResult") Integer firstResult,
//                            @QueryParam("maxResults") Integer maxResults) {
//        Pageable pageable = Pageable.of(firstResult, maxResults);
//        Page<T> page = repository.findAll(pageable);
//        return Response.ok(page).build();
//    }

    @GET
    public Response findAll() {
        List<T> entities = repository.findAll();
        return Response.ok(entities).build();
    }

    @GET
    @Path(value = "{id : \\d+}")
    public Response find(@PathParam(value = "id") PK id) {
        return Response.ok(repository.findOrThrow(id)).build();
    }

    @POST
    public Response create(T entidade, @Context UriInfo info) {
        T entidade_ = service.save(entidade);
        UriBuilder builder = info.getAbsolutePathBuilder();
        builder.path(entidade_.getId().toString());
        return Response.created(builder.build()).entity(entidade_).build();
    }

    @DELETE
    @Path(value = "{id : \\d+}")
    public Response delete(@PathParam(value = "id") PK id) {
        service.deleteOrThrow(id);
        return Response.noContent().build();
    }

    @PUT
    @Path(value = "{id : \\d+}")
    public Response update(@PathParam(value = "id") PK id, T entidade) {
        repository.findReferenceOrThrow(id);
        service.save(entidade);
        return Response.noContent().build();
    }

}
