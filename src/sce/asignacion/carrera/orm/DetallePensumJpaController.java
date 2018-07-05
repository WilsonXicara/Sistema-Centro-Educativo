/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.asignacion.carrera.orm.DetallePensumEntity;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class DetallePensumJpaController implements Serializable {

    public DetallePensumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetallePensumEntity detallePensumEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detallePensumEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetallePensumEntity detallePensumEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detallePensumEntity = em.merge(detallePensumEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallePensumEntity.getId();
                if (findDetallePensumEntity(id) == null) {
                    throw new NonexistentEntityException("The detallePensumEntity with id " + id + " no longer exists.");
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
            DetallePensumEntity detallePensumEntity;
            try {
                detallePensumEntity = em.getReference(DetallePensumEntity.class, id);
                detallePensumEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallePensumEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(detallePensumEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetallePensumEntity> findDetallePensumEntityEntities() {
        return findDetallePensumEntityEntities(true, -1, -1);
    }

    public List<DetallePensumEntity> findDetallePensumEntityEntities(int maxResults, int firstResult) {
        return findDetallePensumEntityEntities(false, maxResults, firstResult);
    }

    private List<DetallePensumEntity> findDetallePensumEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetallePensumEntity.class));
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

    public DetallePensumEntity findDetallePensumEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetallePensumEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallePensumEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetallePensumEntity> rt = cq.from(DetallePensumEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
