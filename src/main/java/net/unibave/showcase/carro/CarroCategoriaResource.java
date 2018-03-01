package net.unibave.showcase.carro;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarroCategoriaResource {
    
//    @Inject
//    private JPAQueryFactory queryFactory;

    @Inject
    private CarroRepository repository;

    @PathParam("codigoCategoria")
    private Long codigoCategoria;

    @GET
    public Response findAll(/*@PathParam("codigoCategoria") Long codigoCategoria, */
            @QueryParam("nome") String nome) {
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(QCarro.carro.categoria.id.eq(codigoCategoria));
//        if (nome != null && !nome.isEmpty()) {
//            predicates.add(QCarro.carro.nome.containsIgnoreCase(nome));
//        }
//        List<Carro> carros = queryFactory.selectFrom(QCarro.carro)
//                .where(predicates.toArray(new Predicate[predicates.size()]))
//                .fetch();
//        return Response.ok(carros).build();
        return Response.ok(repository.findAll()).build();
    }
    
}
