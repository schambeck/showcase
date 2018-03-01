package net.unibave.showcase.base.jaxrs.mapper;

import javax.ws.rs.core.Response;

/**
 * Apenas para depuração do ExceptionMapper
 */
//@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        System.out.println(getClass().getSimpleName() + " entrou");
        return Response.serverError().build();
    }

}
