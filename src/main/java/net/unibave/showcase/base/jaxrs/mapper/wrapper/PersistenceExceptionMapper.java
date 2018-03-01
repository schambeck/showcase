package net.unibave.showcase.base.jaxrs.mapper.wrapper;

import net.unibave.showcase.base.jaxrs.ErrorMessage;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Context
    private Providers providers;

    @Override
    public Response toResponse(PersistenceException exception) {
        if (exception.getCause() == null) {
            return Response.serverError().build();
        }
        Class cause = exception.getCause().getClass();
        ExceptionMapper mapper = providers.getExceptionMapper(cause);
        if (mapper == null) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage("Ocorreu um erro interno: " + exception.getMessage());
            return Response.serverError().entity(errorMessage).build();
        } else {
            return mapper.toResponse(exception.getCause());
        }
    }

}
