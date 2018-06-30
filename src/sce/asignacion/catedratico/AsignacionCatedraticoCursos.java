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
import sce.principal.command.AsignacionCommand;
import sce.principal.entity.AsignacionCatedraticoCursosEntity;
import sce.principal.entity.AsignacionCatedraticoEntity;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.ormjpa.AsignacionCatedraticoCursosJpaController;
import sce.principal.ormjpa.AsignacionCatedraticoJpaController;
import sce.principal.ormjpa.AsignacionCursoJpaController;

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
    public void crearAsignacion() {
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
            } else{
                //Pendiente (excepcion)
                em.getTransaction().rollback();
            }
        }
        } else{
            //Excepcion pendiente 
            em.getTransaction().rollback();
        }
        
        em.getTransaction().commit();
    }
 
}
