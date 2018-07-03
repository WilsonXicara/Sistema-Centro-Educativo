/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoCursosEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoCursosJpaController;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author juan_
 */
public class AsignacionCatedraticoCursos implements AsignacionCommand{
    
    private final EntityManagerFactory emf;
    private Long idAsignacionCat;
    private final ArrayList<Long> idAsignacionCurso; 

    public AsignacionCatedraticoCursos(EntityManagerFactory emf) {
        this.emf = emf;
        this.idAsignacionCurso = new ArrayList<>();
    }

    public void setIdAsignacionCat(Long idAsignacionCat) {
        this.idAsignacionCat = idAsignacionCat;
    }

    public void addIdAsignacionCurso(Long idAsignacionCurso) {
        this.idAsignacionCurso.add(idAsignacionCurso);
       
    }
    
   

    @Override
    public void crearAsignacion() throws NonexistentEntityException {
        EntityManager em = null;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        AsignacionCatedraticoEntity catedraticoExistente = new AsignacionCatedraticoJpaController(emf).findAsignacionCatedraticoEntity(idAsignacionCat);
        if (catedraticoExistente != null){
        AsignacionCatedraticoCursosEntity asignacionCatCursos = new AsignacionCatedraticoCursosEntity();
        asignacionCatCursos.setAsignacion_catedratico_id(idAsignacionCat);
           for (Long elementos : idAsignacionCurso){
            AsignacionCursoEntity AsigCursosExistente = new AsignacionCursoJpaController(emf).findAsignacion_Curso(elementos);
            if (AsigCursosExistente != null){
                asignacionCatCursos.setAsignacion_curso_id(elementos);
                new AsignacionCatedraticoCursosJpaController(emf).create(asignacionCatCursos, em);
                AsignacionCatedraticoCursosJpaController a = new AsignacionCatedraticoCursosJpaController(emf);
                
            } else{
                em.getTransaction().rollback();
                throw new NonexistentEntityException("No existen una asignación curso con el id siguiente: " + elementos);
            }
        }
        } else{
            em.getTransaction().rollback();
            throw new NonexistentEntityException("No existen una asignación catedrático con el id siguiente: " + idAsignacionCat);
        }
        
        em.getTransaction().commit();
    }
 
}
