package com.infobip.bipcars.repositories;

import com.infobip.bipcars.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductRepository {
    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    public void create(final Product product) {
        entityManager.persist(product);
    }

    public void update(final Product product) {
        entityManager.merge(product);
    }

    public Product find(final Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findMany() {
        return entityManager.createQuery("SELECT p FROM Product p").getResultList();
    }

    public void delete(final Product product) {
        entityManager.remove(entityManager.merge(product));
    }
}
