/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.consultor.ConsultorGeneral;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author juan_
 */
public class AsignacionCarrera implements AsignacionCommand {
    
    private final EntityManagerFactory emf;
    private Long idCicloEscolar;
    private Long idCarrera;

    public AsignacionCarrera(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setIdCicloEscolar(Long idCicloEscolar) {
        this.idCicloEscolar = idCicloEscolar;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    
    
    @Override
    public void crearAsignacion() throws NonexistentEntityException {
        EntityManager em = null;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        if (ConsultorGeneral.cicloExistente(idCicloEscolar, emf) && ConsultorGeneral.cicloVigente(idCicloEscolar, emf)){
            AsignacionCarreraEntity asignacionCarrera = new AsignacionCarreraEntity();
            asignacionCarrera.setCiclo_escolar_id(idCicloEscolar);
            if (ConsultorGeneral.carreraExistente(idCarrera, emf)){
                asignacionCarrera.setCarrera_id(idCarrera);
                new AsignacionCarreraJpaController(emf).create(asignacionCarrera,em);
            } else {
                em.getTransaction().rollback();
                throw new NonexistentEntityException("No existe una carrera con el id siguiente: " + idCarrera);
            }
        } else {
            em.getTransaction().rollback();
            throw new NonexistentEntityException("El ciclo escolar con el id siguiente: " + idCicloEscolar + "no está disponible o pueda que no exista");
        }
        
     em.getTransaction().commit();
    }
    
}
