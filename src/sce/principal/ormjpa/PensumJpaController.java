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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.principal.entity.PensumEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class PensumJpaController implements Serializable {

    public PensumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PensumEntity buscarPorCodigo(String pensumCodigo){
        EntityManager em = getEntityManager();
        TypedQuery<PensumEntity> query = em.createNamedQuery("Pensum.buscarPorCodigo", PensumEntity.class);
        List<PensumEntity> encontrados = query.setParameter("pensumCodigo", pensumCodigo).getResultList();
        if (encontrados.isEmpty()) {
            return null;
        }
        return encontrados.get(0);
    }
    
    public void create(PensumEntity pensumEntity) {
        EntityManager em = null;
        try {
            PensumEntity existente = buscarPorCodigo(pensumEntity.getCodigo());
            if (existente != null) {
                pensumEntity.copy(existente);
                return;
            }
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pensumEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PensumEntity pensumEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pensumEntity = em.merge(pensumEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pensumEntity.getId();
                if (findPensumEntity(id) == null) {
                    throw new NonexistentEntityException("The pensumEntity with id " + id + " no longer exists.");
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
            PensumEntity pensumEntity;
            try {
                pensumEntity = em.getReference(PensumEntity.class, id);
                pensumEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pensumEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(pensumEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PensumEntity> findPensumEntityEntities() {
        return findPensumEntityEntities(true, -1, -1);
    }

    public List<PensumEntity> findPensumEntityEntities(int maxResults, int firstResult) {
        return findPensumEntityEntities(false, maxResults, firstResult);
    }

    private List<PensumEntity> findPensumEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PensumEntity.class));
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

    public PensumEntity findPensumEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PensumEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getPensumEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PensumEntity> rt = cq.from(PensumEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
