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
import sce.principal.entity.NotaDistribucionNotasEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class NotaDistribucionNotasJpaController implements Serializable {

    public NotaDistribucionNotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotaDistribucionNotasEntity notaDistribucionNotasEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notaDistribucionNotasEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotaDistribucionNotasEntity notaDistribucionNotasEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notaDistribucionNotasEntity = em.merge(notaDistribucionNotasEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notaDistribucionNotasEntity.getId();
                if (findNotaDistribucionNotasEntity(id) == null) {
                    throw new NonexistentEntityException("The notaDistribucionNotasEntity with id " + id + " no longer exists.");
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
            NotaDistribucionNotasEntity notaDistribucionNotasEntity;
            try {
                notaDistribucionNotasEntity = em.getReference(NotaDistribucionNotasEntity.class, id);
                notaDistribucionNotasEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notaDistribucionNotasEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(notaDistribucionNotasEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotaDistribucionNotasEntity> findNotaDistribucionNotasEntityEntities() {
        return findNotaDistribucionNotasEntityEntities(true, -1, -1);
    }

    public List<NotaDistribucionNotasEntity> findNotaDistribucionNotasEntityEntities(int maxResults, int firstResult) {
        return findNotaDistribucionNotasEntityEntities(false, maxResults, firstResult);
    }

    private List<NotaDistribucionNotasEntity> findNotaDistribucionNotasEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotaDistribucionNotasEntity.class));
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

    public NotaDistribucionNotasEntity findNotaDistribucionNotasEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaDistribucionNotasEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaDistribucionNotasEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotaDistribucionNotasEntity> rt = cq.from(NotaDistribucionNotasEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
