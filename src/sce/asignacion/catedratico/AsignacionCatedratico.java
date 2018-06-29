/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.principal.command.AsignacionCommand;
import sce.principal.entity.AsignacionCarreraEntity;
import sce.principal.entity.AsignacionCatedraticoCursosEntity;
import sce.principal.entity.AsignacionCatedraticoEntity;
import sce.principal.entity.AsignacionCatedraticoGradosEntity;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.entity.AsignacionGradoEntity;
import sce.principal.entity.CatedraticoEntity;
import sce.principal.ormjpa.AsignacionCatedraticoCursosJpaController;
import sce.principal.ormjpa.AsignacionCatedraticoGradosJpaController;
import sce.principal.ormjpa.AsignacionCatedraticoJpaController;
import sce.principal.ormjpa.CatedraticoJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionCatedratico implements AsignacionCommand{
        private final EntityManagerFactory emf;
        private AsignacionCarreraEntity ace;
        private CatedraticoEntity catedratico;
        private ArrayList<AsignacionGradoEntity> listaGrados;
        private ArrayList<AsignacionCursoEntity> listaCursos;

    public AsignacionCatedratico(EntityManagerFactory emf) {
        this.emf = emf;
        this.listaGrados = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
    }

    public void setAce(AsignacionCarreraEntity ace) {
        this.ace = ace;
    }

    public void setCatedratico(CatedraticoEntity catedratico) {
        this.catedratico = catedratico;
    }

    public void addGrado(AsignacionGradoEntity grado) { 
         this.listaGrados.add(grado); 
    }
    
    public void addCurso(AsignacionCursoEntity curso) { 
         this.listaCursos.add(curso); 
    }
    
    
    @Override
    public void crearAsignacion() {
        //Se crea la asignacion de un catedrático con una carrera en espicífico
        AsignacionCatedraticoJpaController asignadorCat = new AsignacionCatedraticoJpaController(emf);
        AsignacionCatedraticoEntity asignacionCat = new AsignacionCatedraticoEntity();
        asignacionCat.setAsignacion_carrera_id(ace.getId());
        asignacionCat.setCatedratico_id(catedratico.getId());
        asignadorCat.create(asignacionCat);
        catedratico.setAsignacion_id(asignacionCat.getId());
            try {
                new CatedraticoJpaController(emf).edit(catedratico);
            } catch (Exception ex) {
                Logger.getLogger(AsignacionCatedratico.class.getName()).log(Level.SEVERE, null, ex);
            }
        //Se asignan tantos cursos sean necesarios a un catedrático
        for (AsignacionCursoEntity cursos : listaCursos){
            AsignacionCatedraticoCursosJpaController asignadorCursos = new AsignacionCatedraticoCursosJpaController(emf);
            AsignacionCatedraticoCursosEntity asignacionCursos = new AsignacionCatedraticoCursosEntity();
            asignacionCursos.setAsignacion_catedratico_id(asignacionCat.getId());
            asignacionCursos.setAsignacion_curso_id(cursos.getId());
            asignadorCursos.create(asignacionCursos);
        }
        //Se asignan los grados necesarios a un catedrático 
        for (AsignacionGradoEntity grados : listaGrados){
            AsignacionCatedraticoGradosJpaController asignadorGrados = new AsignacionCatedraticoGradosJpaController(emf);
            AsignacionCatedraticoGradosEntity asignacionGrados = new AsignacionCatedraticoGradosEntity();
            asignacionGrados.setAsignacion_catedratico_id(asignacionCat.getId());
            asignacionGrados.setAsignacion_grado_id(grados.getId());
            asignadorGrados.create(asignacionGrados);
        }
        
    }
    
}
