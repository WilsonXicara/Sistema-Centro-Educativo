/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoCursosEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoCursosJpaController;
//import sce.asignacion.curso.ConsultorAsignacionCurso;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
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
        if (!ConsultorAsignacionCatedratico.existeAsignacionCatedratico(idAsignacionCat, emf)){
            throw new NonexistentEntityException("No existen una asignacion catedrático con el id siguiente: " + idAsignacionCat);   
        }
        if (ConsultorAsignacionCatedratico.isAsignacionCatedraticoAnulada(idAsignacionCat, emf)){
            throw new NonexistentEntityException("La asignación catedratico con id " + idAsignacionCat + "está anulada");
        }
        AsignacionCatedraticoCursosEntity asignacionCatCursos = new AsignacionCatedraticoCursosEntity();
        asignacionCatCursos.setAsignacion_catedratico_id(idAsignacionCat);
           for (Long elementos : idAsignacionCurso){
            AsignacionCursoEntity AsigCursosExistente = new AsignacionCursoJpaController(emf).findAsignacion_Curso(elementos);
            /*if (!ConsultorAsignacionCurso.existeAsignacionCurso(emf, elementos)){
                throw new NonexistentEntityException("No existe una asignacion curso con id "+elementos);
            }
            if (ConsultorAsignacionCurso.esAsignacionCursoAnulada(emf, elementos)){
                throw new NonexistentEntityException("La asignacion curso con id "+elementos+" ha sido anulada.");
            }*/

            asignacionCatCursos.setAsignacion_curso_id(elementos);
            new AsignacionCatedraticoCursosJpaController(emf).create(asignacionCatCursos);
           
            }
    } 
        
    
 
}
