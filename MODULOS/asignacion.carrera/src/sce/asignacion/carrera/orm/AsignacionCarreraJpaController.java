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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionCarreraJpaController implements Serializable {

    public AsignacionCarreraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<AsignacionCarreraEntity> buscarPorCiclo(Long idCicloEscolar){
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionCarreraEntity> query = em.createNamedQuery("AsignacionCarrera.buscarPorCiclo",AsignacionCarreraEntity.class);
        return query.setParameter("idCicloEscolar", idCicloEscolar).getResultList();
    }
    
    public AsignacionCarreraEntity buscarPorCarreraId(Long idCarrera){
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionCarreraEntity> query = em.createNamedQuery("AsignacionCarrera.buscarPorCarreraID",AsignacionCarreraEntity.class);
        return query.setParameter("idCarrera",idCarrera).getResultList().get(0);
    }
    
    
    public void create(AsignacionCarreraEntity asignacionCarreraEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacionCarreraEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(AsignacionCarreraEntity asignacionCarreraEntity, EntityManager em) {
        em.persist(asignacionCarreraEntity);
    }

    public void edit(AsignacionCarreraEntity asignacionCarreraEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacionCarreraEntity = em.merge(asignacionCarreraEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionCarreraEntity.getId();
                if (findAsignacionCarreraEntity(id) == null) {
                    throw new NonexistentEntityException("The asignacionCarreraEntity with id " + id + " no longer exists.");
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
            AsignacionCarreraEntity asignacionCarreraEntity;
            try {
                asignacionCarreraEntity = em.getReference(AsignacionCarreraEntity.class, id);
                asignacionCarreraEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionCarreraEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacionCarreraEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionCarreraEntity> findAsignacionCarreraEntityEntities() {
        return findAsignacionCarreraEntityEntities(true, -1, -1);
    }

    public List<AsignacionCarreraEntity> findAsignacionCarreraEntityEntities(int maxResults, int firstResult) {
        return findAsignacionCarreraEntityEntities(false, maxResults, firstResult);
    }

    private List<AsignacionCarreraEntity> findAsignacionCarreraEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionCarreraEntity.class));
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

    public AsignacionCarreraEntity findAsignacionCarreraEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionCarreraEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionCarreraEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionCarreraEntity> rt = cq.from(AsignacionCarreraEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
