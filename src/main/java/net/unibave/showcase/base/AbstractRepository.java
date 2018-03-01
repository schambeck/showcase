package net.unibave.showcase.base;

import net.unibave.showcase.base.jaxrs.Page;
import net.unibave.showcase.base.jaxrs.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractRepository<T, PK> {

    private static final int FIRST_RESULT = 0;
    private static final int MAX_RESULTS = 10;

    @PersistenceContext
    private EntityManager em;

    private Class<T> tClass;

    public List<T> findAll() {
        return findAll(null, null, null);
    }

    public Page<T> findAll(Pageable pageable) {
        int firstResult = pageable.getFirstResult() == null ? FIRST_RESULT : pageable.getFirstResult();
        int maxResults = pageable.getMaxResults() == null ? MAX_RESULTS : pageable.getMaxResults();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root<T> from = query.from(getClazz());
        TypedQuery<T> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(maxResults);
        List<T> content_ = typedQuery.getResultList();
//        List<T> content_ = findAll(pageable.getWhere(), firstResult, maxResults + 1);

        CriteriaQuery<Long> queryCount = builder.createQuery(Long.class);
        queryCount.select(builder.count(queryCount.from(getClazz())));
        Long count = em.createQuery(queryCount).getSingleResult();

        boolean hasMore = content_.size() > maxResults;
        List<T> content = hasMore ? content_.subList(0, content_.size() - 1) : content_;
        Page<T> page = Page.<T>builder()
                .content(content)
                .firstResult(firstResult)
                .maxResults(maxResults)
                .hasMore(hasMore)
                .numberOfElements(content.size())
                .total(count)
                .build();
        return page;
    }

    public List<T> findAll(Predicate... predicates) {
        return findAll(null, null, null, predicates);
    }

    public List<T> findAll(String where, Integer firstResult, Integer maxResults) {
        return findAll(firstResult, maxResults, where);
    }

    private List<T> findAll(Integer firstResult, Integer maxResults, String where, Predicate... predicates) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root<T> from = query.from(getClazz());
        if (predicates.length > 0) {
            query.where(predicates);
        }
        TypedQuery<T> typedQuery = em.createQuery(query);
        if (firstResult != null) {
            typedQuery.setFirstResult(firstResult);
        }
        if (maxResults != null) {
            typedQuery.setMaxResults(maxResults);
        }
        return typedQuery.getResultList();
    }

    public T find(PK id) {
        T entity = em.find(getClazz(), id);
        return entity;
    }

    public T findOrThrow(PK id) {
        T entity = find(id);
        if (entity == null) {
            throw new EntityNotFoundException("Entidade com id " + id.toString() + " não encontrada.");
        }
        return entity;
    }

    public T findReferenceOrThrow(PK id) {
        T entity = em.getReference(getClazz(), id);
        return entity;
    }

    public Class<T> getClazz() {
        if (tClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            tClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return tClass;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public CriteriaBuilder builder() {
        return null;
    }

    public Root<T> from() {
        return null;
    }

}
