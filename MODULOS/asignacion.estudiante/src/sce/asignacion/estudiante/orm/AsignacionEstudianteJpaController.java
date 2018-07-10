/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante.orm;

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
public class AsignacionEstudianteJpaController implements Serializable {

    public AsignacionEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<AsignacionEstudianteEntity> buscarPorAsignacionCarrera(Long idAsignacionCarrera) {
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionEstudianteEntity> query = em.createNamedQuery("AsignacionEstudiante.buscarPorAsignacionCarrera", AsignacionEstudianteEntity.class);
        return query.setParameter("idAsignacionCarrera", idAsignacionCarrera).getResultList();
    }
    public List<AsignacionEstudianteEntity> buscarPorAsignacionGrado(Long idAsignacionGrado) {
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionEstudianteEntity> query = em.createNamedQuery("AsignacionEstudiante.buscarPorAsignacionGrado", AsignacionEstudianteEntity.class);
        return query.setParameter("idAsignacionGrado", idAsignacionGrado).getResultList();
    }

    public void create(AsignacionEstudianteEntity asignacionEstudiante) {
        EntityManager em = null;
        try {
            /*AsignacionEstudianteEntity existente = buscarPorAsignacionCarrera(asignacionEstudiante);
            if (existente != null) {
                asignacionEstudiante.copy(existente);
                return;
            }*/
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacionEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(AsignacionEstudianteEntity asignacionEstudiante, EntityManager em) {
        em.persist(asignacionEstudiante);
    }

    public void edit(AsignacionEstudianteEntity asignacionEstudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        Long id=null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacionEstudiante = em.merge(asignacionEstudiante);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                id = asignacionEstudiante.getId();
                if (findAsignacion_Estudiante(id) == null) {
                    throw new NonexistentEntityException("No existe una Asignación de Estudiante con id="+id);
                }
            }
            throw new NonexistentEntityException("Ocurrió un eror al intentar anular la Asignación de Estudiante con id="+id);
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
            AsignacionEstudianteEntity asignacionEstudiante;
            try {
                asignacionEstudiante = em.getReference(AsignacionEstudianteEntity.class, id);
                asignacionEstudiante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("No existe una Asignación de Estudiante con id="+id);
            }
            em.remove(asignacionEstudiante);
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
