/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoCursosEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosEntity;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoCursosJpaController;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosJpaController;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.persona.catedratico.orm.CatedraticoJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author juan_
 */
public class AsignacionCatedratico implements AsignacionCommand{
        private final EntityManagerFactory emf;
        private Long idAsignacionCarrera;
        private Long idCatedratico;
       
    public AsignacionCatedratico(EntityManagerFactory emf) {
        this.emf = emf;    
    }

    public void setIdAsignacionCarrera(Long idAsignacionCarrera) {
        this.idAsignacionCarrera = idAsignacionCarrera;
    }

    public void setIdCatedratico(Long idCatedratico) {
        this.idCatedratico = idCatedratico;
    }

    
    @Override
    public void crearAsignacion() throws NonexistentEntityException{
        EntityManager em = null;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        AsignacionCarreraEntity carreraExistente = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        CatedraticoEntity catedraticoExistente = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
        if (carreraExistente != null && catedraticoExistente != null){
            AsignacionCatedraticoEntity asignacionCat = new AsignacionCatedraticoEntity();
            asignacionCat.setAsignacion_carrera_id(idAsignacionCarrera);
            asignacionCat.setCatedratico_id(idCatedratico);
            new AsignacionCatedraticoJpaController(emf).create(asignacionCat);
            catedraticoExistente.setAsignacion_id(asignacionCat.getId());
            try {
                new CatedraticoJpaController(emf).edit(catedraticoExistente);
            } catch (Exception ex) {
                Logger.getLogger(AsignacionCatedratico.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else {
            em.getTransaction().rollback();
            throw new NonexistentEntityException("No existen un catedrático con el id siguiente: " + idCatedratico); 
        }
        
        em.getTransaction().commit();

    }
    
}
