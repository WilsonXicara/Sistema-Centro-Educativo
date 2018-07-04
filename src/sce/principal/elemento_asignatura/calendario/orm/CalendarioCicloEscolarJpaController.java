/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.calendario.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.principal.elemento_asignatura.calendario.orm.CalendarioCicloEscolarEntity;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class CalendarioCicloEscolarJpaController implements Serializable {

    public CalendarioCicloEscolarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CalendarioCicloEscolarEntity calendarioCicloEscolarEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(calendarioCicloEscolarEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CalendarioCicloEscolarEntity calendarioCicloEscolarEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            calendarioCicloEscolarEntity = em.merge(calendarioCicloEscolarEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = calendarioCicloEscolarEntity.getId();
                if (findCalendarioCicloEscolarEntity(id) == null) {
                    throw new NonexistentEntityException("The calendarioCicloEscolarEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioCicloEscolarEntity calendarioCicloEscolarEntity;
            try {
                calendarioCicloEscolarEntity = em.getReference(CalendarioCicloEscolarEntity.class, id);
                calendarioCicloEscolarEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendarioCicloEscolarEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(calendarioCicloEscolarEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CalendarioCicloEscolarEntity> findCalendarioCicloEscolarEntityEntities() {
        return findCalendarioCicloEscolarEntityEntities(true, -1, -1);
    }

    public List<CalendarioCicloEscolarEntity> findCalendarioCicloEscolarEntityEntities(int maxResults, int firstResult) {
        return findCalendarioCicloEscolarEntityEntities(false, maxResults, firstResult);
    }

    private List<CalendarioCicloEscolarEntity> findCalendarioCicloEscolarEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CalendarioCicloEscolarEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CalendarioCicloEscolarEntity findCalendarioCicloEscolarEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CalendarioCicloEscolarEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioCicloEscolarEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CalendarioCicloEscolarEntity> rt = cq.from(CalendarioCicloEscolarEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
