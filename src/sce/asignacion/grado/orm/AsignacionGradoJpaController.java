/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.grado.orm;

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
public class AsignacionGradoJpaController implements Serializable {

    public AsignacionGradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<AsignacionGradoEntity> buscarPorCarrera(Long idAsigCarrera){
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionGradoEntity> query = em.createNamedQuery("AsignacionGrado.buscarPorCarrera",AsignacionGradoEntity.class);
        return query.setParameter("idAsigCarrera", idAsigCarrera).getResultList();
    }
    
    public void create(AsignacionGradoEntity asignacion_Grado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacion_Grado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void create(AsignacionGradoEntity asignacion_Grado, EntityManager em) {
        em.persist(asignacion_Grado);
    }

    public void edit(AsignacionGradoEntity asignacion_Grado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacion_Grado = em.merge(asignacion_Grado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacion_Grado.getId();
                if (findAsignacion_Grado(id) == null) {
                    throw new NonexistentEntityException("The asignacion_Grado with id " + id + " no longer exists.");
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
            AsignacionGradoEntity asignacion_Grado;
            try {
                asignacion_Grado = em.getReference(AsignacionGradoEntity.class, id);
                asignacion_Grado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacion_Grado with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacion_Grado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionGradoEntity> findAsignacion_GradoEntities() {
        return findAsignacion_GradoEntities(true, -1, -1);
    }

    public List<AsignacionGradoEntity> findAsignacion_GradoEntities(int maxResults, int firstResult) {
        return findAsignacion_GradoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionGradoEntity> findAsignacion_GradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionGradoEntity.class));
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

    public AsignacionGradoEntity findAsignacion_Grado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionGradoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacion_GradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionGradoEntity> rt = cq.from(AsignacionGradoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
