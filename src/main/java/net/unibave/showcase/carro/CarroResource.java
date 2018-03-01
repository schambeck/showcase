package net.unibave.showcase.carro;

import net.unibave.showcase.base.CrudResource;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Stateless
@Path("carros")
public class CarroResource extends CrudResource<Carro, Long> {

//    @Inject
//    private JPAQueryFactory queryFactory;

    @GET
    public Response findAll(@QueryParam("nome") String nome) {
//        List<Predicate> predicates = new ArrayList<>();
//        if (nome != null && !nome.isEmpty()) {
//            predicates.add(QCarro.carro.nome.containsIgnoreCase(nome));
//        }
//        List<Carro> carros = queryFactory.selectFrom(QCarro.carro)
//                .where(predicates.toArray(new Predicate[predicates.size()]))
//                .fetch();
//        return Response.ok(carros).build();
        return super.findAll();
    }

}
