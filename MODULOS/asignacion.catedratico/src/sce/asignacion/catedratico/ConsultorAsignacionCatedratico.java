/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.ConsultorAsignacionCarrera;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.persona.catedratico.ConsultorCatedratico;

/**
 *
 * @author juan_
 */
public class ConsultorAsignacionCatedratico {
    
    public static boolean existeAsignacionCatedratico(Long idAsignacionCatedratico, EntityManagerFactory emf) {
        AsignacionCatedraticoEntity asigCat = new AsignacionCatedraticoJpaController(emf).findAsignacionCatedraticoEntity(idAsignacionCatedratico);
        return !(asigCat == null);
    }
    
    public static boolean isAsignacionCatedraticoAnulada(Long idAsignacionCatedratico, EntityManagerFactory emf){
        if (existeAsignacionCatedratico(idAsignacionCatedratico,emf)) {
            return true;
        }
        AsignacionCatedraticoEntity asigCat = new AsignacionCatedraticoJpaController(emf).findAsignacionCatedraticoEntity(idAsignacionCatedratico);
        if (asigCat.getAnulado()){
            return true;
        }
        //Se consulta al modulo de "Asignacion Carrera"
        Long idAsigSup = asigCat.getAsignacion_carrera_id();
        if (ConsultorAsignacionCarrera.existeAsignacionCarrera(idAsigSup, emf) && !ConsultorAsignacionCarrera.isAsignacionCarreraAnulada(idAsigSup, emf)){
            return true;
        }
        //Se consulta al módulo de persona "Catedratico"
        Long idAsignacionSuperior = asigCat.getCatedratico_id();
        return ConsultorCatedratico.existeCatedratico(idAsignacionSuperior, emf) && !ConsultorCatedratico.isCatedraticoAnulado(idAsignacionSuperior, emf);
    } 
     
}
