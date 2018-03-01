package net.unibave.showcase.categoria;

import net.unibave.showcase.base.CrudResource;
import net.unibave.showcase.carro.CarroCategoriaResource;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

@Stateless
@Path("categorias")
public class CategoriaResource extends CrudResource<Categoria, Long> {

    @Context
    private ResourceContext context;

    @Path("{codigoCategoria}/carros")
    public CarroCategoriaResource carro() {
        return context.getResource(CarroCategoriaResource.class);
    }

}
