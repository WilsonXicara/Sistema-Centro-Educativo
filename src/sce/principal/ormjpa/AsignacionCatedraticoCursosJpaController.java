/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.ormjpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.principal.entity.AsignacionCatedraticoCursosEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionCatedraticoCursosJpaController implements Serializable {

    public AsignacionCatedraticoCursosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionCatedraticoCursosEntity asignacionCatedraticoCursosEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacionCatedraticoCursosEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionCatedraticoCursosEntity asignacionCatedraticoCursosEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacionCatedraticoCursosEntity = em.merge(asignacionCatedraticoCursosEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionCatedraticoCursosEntity.getId();
                if (findAsignacionCatedraticoCursosEntity(id) == null) {
                    throw new NonexistentEntityException("The asignacionCatedraticoCursosEntity with id " + id + " no longer exists.");
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
            AsignacionCatedraticoCursosEntity asignacionCatedraticoCursosEntity;
            try {
                asignacionCatedraticoCursosEntity = em.getReference(AsignacionCatedraticoCursosEntity.class, id);
                asignacionCatedraticoCursosEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionCatedraticoCursosEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacionCatedraticoCursosEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionCatedraticoCursosEntity> findAsignacionCatedraticoCursosEntityEntities() {
        return findAsignacionCatedraticoCursosEntityEntities(true, -1, -1);
    }

    public List<AsignacionCatedraticoCursosEntity> findAsignacionCatedraticoCursosEntityEntities(int maxResults, int firstResult) {
        return findAsignacionCatedraticoCursosEntityEntities(false, maxResults, firstResult);
    }

    private List<AsignacionCatedraticoCursosEntity> findAsignacionCatedraticoCursosEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionCatedraticoCursosEntity.class));
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

    public AsignacionCatedraticoCursosEntity findAsignacionCatedraticoCursosEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionCatedraticoCursosEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionCatedraticoCursosEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionCatedraticoCursosEntity> rt = cq.from(AsignacionCatedraticoCursosEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
