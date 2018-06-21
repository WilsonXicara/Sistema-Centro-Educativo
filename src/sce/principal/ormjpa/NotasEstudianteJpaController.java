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
import sce.principal.entity.NotasEstudianteEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class NotasEstudianteJpaController implements Serializable {

    public NotasEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotasEstudianteEntity notasEstudianteEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notasEstudianteEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotasEstudianteEntity notasEstudianteEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notasEstudianteEntity = em.merge(notasEstudianteEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notasEstudianteEntity.getId();
                if (findNotasEstudianteEntity(id) == null) {
                    throw new NonexistentEntityException("The notasEstudianteEntity with id " + id + " no longer exists.");
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
            NotasEstudianteEntity notasEstudianteEntity;
            try {
                notasEstudianteEntity = em.getReference(NotasEstudianteEntity.class, id);
                notasEstudianteEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notasEstudianteEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(notasEstudianteEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotasEstudianteEntity> findNotasEstudianteEntityEntities() {
        return findNotasEstudianteEntityEntities(true, -1, -1);
    }

    public List<NotasEstudianteEntity> findNotasEstudianteEntityEntities(int maxResults, int firstResult) {
        return findNotasEstudianteEntityEntities(false, maxResults, firstResult);
    }

    private List<NotasEstudianteEntity> findNotasEstudianteEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotasEstudianteEntity.class));
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

    public NotasEstudianteEntity findNotasEstudianteEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotasEstudianteEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotasEstudianteEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotasEstudianteEntity> rt = cq.from(NotasEstudianteEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
