package com.infobip.bipcars.repositories;

import com.infobip.bipcars.entities.WorkingHour;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class WorkingHourRepository {
    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    public void create(final WorkingHour workingHour) {
        entityManager.persist(workingHour);
    }

    public void update(final WorkingHour product) {
        entityManager.merge(product);
    }

    public WorkingHour find(final Long id) {
        return entityManager.find(WorkingHour.class, id);
    }

    public List<WorkingHour> findMany() {
        return entityManager.createQuery("SELECT w FROM WorkingHour w").getResultList();
    }

    public void delete(final WorkingHour product) {
        entityManager.remove(entityManager.merge(product));
    }
}
