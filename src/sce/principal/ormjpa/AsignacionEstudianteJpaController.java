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
import sce.principal.entity.AsignacionEstudianteEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudianteJpaController implements Serializable {

    public AsignacionEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionEstudianteEntity asignacion_Estudiante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacion_Estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionEstudianteEntity asignacion_Estudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacion_Estudiante = em.merge(asignacion_Estudiante);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacion_Estudiante.getId();
                if (findAsignacion_Estudiante(id) == null) {
                    throw new NonexistentEntityException("The asignacion_Estudiante with id " + id + " no longer exists.");
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
            AsignacionEstudianteEntity asignacion_Estudiante;
            try {
                asignacion_Estudiante = em.getReference(AsignacionEstudianteEntity.class, id);
                asignacion_Estudiante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacion_Estudiante with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacion_Estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionEstudianteEntity> findAsignacion_EstudianteEntities() {
        return findAsignacion_EstudianteEntities(true, -1, -1);
    }

    public List<AsignacionEstudianteEntity> findAsignacion_EstudianteEntities(int maxResults, int firstResult) {
        return findAsignacion_EstudianteEntities(false, maxResults, firstResult);
    }

    private List<AsignacionEstudianteEntity> findAsignacion_EstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionEstudianteEntity.class));
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

    public AsignacionEstudianteEntity findAsignacion_Estudiante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionEstudianteEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacion_EstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionEstudianteEntity> rt = cq.from(AsignacionEstudianteEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
