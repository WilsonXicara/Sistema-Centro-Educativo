/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.estudiante.orm;

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
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EstudianteEntity buscarPorCui(String cui) {
        EntityManager em = getEntityManager();
        TypedQuery<EstudianteEntity> query = em.createNamedQuery("Estudiante.buscarPorCui", EstudianteEntity.class);
        List<EstudianteEntity> encontrados = query.setParameter("cuiEstudiante", cui).getResultList();
        if (encontrados.isEmpty()) {
            return null;
        }
        return encontrados.get(0);
    }
    public List<EstudianteEntity> buscarPorAsignacionId(Long idAsignacion) {
        EntityManager em = getEntityManager();
        TypedQuery<EstudianteEntity> query = em.createNamedQuery("Estudiante.buscarPorAsignacionId", EstudianteEntity.class);
        return query.setParameter("idAsignacion", idAsignacion).getResultList();
    }
    public List<EstudianteEntity> buscarAnulados() {
        EntityManager em = getEntityManager();
        TypedQuery<EstudianteEntity> query = em.createNamedQuery("Estudiante.buscarAnulados", EstudianteEntity.class);
        return query.getResultList();
    }
    public List<EstudianteEntity> buscarNoAsignados() {
        EntityManager em = getEntityManager();
        TypedQuery<EstudianteEntity> query = em.createNamedQuery("Estudiante.buscarNoAsignados", EstudianteEntity.class);
        return query.getResultList();
    }
    public List<EstudianteEntity> buscarPorActividad(Long idAsignacionCurso) {
        EntityManager em = getEntityManager();
        TypedQuery<EstudianteEntity> query = em.createNamedQuery("Estudiante.buscarPorActividad", EstudianteEntity.class);
        return query.setParameter("idAsignacionCurso", idAsignacionCurso).getResultList();
    }

    public void create(EstudianteEntity estudianteEntity) {
        EntityManager em = null;
        try {
            EstudianteEntity existente = buscarPorCui(estudianteEntity.getCui());
            if (existente != null) {
                estudianteEntity.copy(existente);
                return;
            }
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estudianteEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(EstudianteEntity estudianteEntity, EntityManager em) {
        em.persist(estudianteEntity);
    }

    public void edit(EstudianteEntity estudianteEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        Long id = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estudianteEntity = em.merge(estudianteEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                id = estudianteEntity.getId();
                if (findEstudianteEntity(id) == null) {
                    throw new NonexistentEntityException("No existe un Estudiante con id="+id);
                }
            }
            throw new Exception("Ocurrió un error al intentar actualizar el Estudiante con id="+id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void edit(EstudianteEntity estudianteEntity, EntityManager em) {
        em.merge(estudianteEntity);
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstudianteEntity estudianteEntity;
            try {
                estudianteEntity = em.getReference(EstudianteEntity.class, id);
                estudianteEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudianteEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(estudianteEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstudianteEntity> findEstudianteEntityEntities() {
        return findEstudianteEntityEntities(true, -1, -1);
    }

    public List<EstudianteEntity> findEstudianteEntityEntities(int maxResults, int firstResult) {
        return findEstudianteEntityEntities(false, maxResults, firstResult);
    }

    private List<EstudianteEntity> findEstudianteEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstudianteEntity.class));
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

    public EstudianteEntity findEstudianteEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstudianteEntity.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getEstudianteEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstudianteEntity> rt = cq.from(EstudianteEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
