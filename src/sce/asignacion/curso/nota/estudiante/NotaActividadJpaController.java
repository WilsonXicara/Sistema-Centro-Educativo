/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.estudiante;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.asignacion.curso.nota.estudiante.NotaActividadEntity;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class NotaActividadJpaController implements Serializable {

    public NotaActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotaActividadEntity notaActividadEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notaActividadEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotaActividadEntity notaActividadEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notaActividadEntity = em.merge(notaActividadEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notaActividadEntity.getId();
                if (findNotaActividadEntity(id) == null) {
                    throw new NonexistentEntityException("The notaActividadEntity with id " + id + " no longer exists.");
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
            NotaActividadEntity notaActividadEntity;
            try {
                notaActividadEntity = em.getReference(NotaActividadEntity.class, id);
                notaActividadEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notaActividadEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(notaActividadEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void destroy(NotaActividadEntity notaActividadEntity, EntityManager em) {
        em.remove(notaActividadEntity);
    }
    
    public List<NotaActividadEntity> buscarPorAsignacionEstudianteCurso(Long idAsignacionEstudianteCurso) {
        EntityManager em = getEntityManager();
        TypedQuery<NotaActividadEntity> query = em.createNamedQuery("NotaActividad.buscarPorAsignacionEstudianteCurso", NotaActividadEntity.class);
        return query.setParameter("idAsignacionEstudianteCurso", idAsignacionEstudianteCurso).getResultList();
    }
    public List<NotaActividadEntity> buscarPoraActividad(Long idActividad) {
        EntityManager em = getEntityManager();
        TypedQuery<NotaActividadEntity> query = em.createNamedQuery("NotaActividad.buscarPorActividad", NotaActividadEntity.class);
        return query.setParameter("idActividad", idActividad).getResultList();
    }

    public List<NotaActividadEntity> findNotaActividadEntityEntities() {
        return findNotaActividadEntityEntities(true, -1, -1);
    }

    public List<NotaActividadEntity> findNotaActividadEntityEntities(int maxResults, int firstResult) {
        return findNotaActividadEntityEntities(false, maxResults, firstResult);
    }

    private List<NotaActividadEntity> findNotaActividadEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotaActividadEntity.class));
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

    public NotaActividadEntity findNotaActividadEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaActividadEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaActividadEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotaActividadEntity> rt = cq.from(NotaActividadEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
