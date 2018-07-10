/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.grado;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.ConsultorAsignacionCarrera;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.principal.elemento_asignatura.grado.ConsultorRegistroGrado;

/**
 *
 * @author juan_
 */
public class ConsultorAsignacionGrado {
    public static Boolean existeAsignacionGrado(Long idAsignacionGrado, EntityManagerFactory emf){
        AsignacionGradoEntity asigGrado = new AsignacionGradoJpaController(emf).findAsignacion_Grado(idAsignacionGrado);
        return asigGrado != null; 
    }
    
    public static Boolean isAsignacionGradoAnulada(Long idAsignacionGrado, EntityManagerFactory emf){
    AsignacionGradoEntity asigGrado = new AsignacionGradoJpaController(emf).findAsignacion_Grado(idAsignacionGrado);
    Long idAuxiliarCarrera = asigGrado.getAsignacion_carrera_id();
    //Consulta al módulo "AsignacionCarrera"
    if (!ConsultorAsignacionCarrera.existeAsignacionCarrera(idAuxiliarCarrera, emf)){
        return true;
    }
    if (ConsultorAsignacionCarrera.isAsignacionCarreraAnulada(idAsignacionGrado, emf)){
        return true;
    }
    if (!asigGrado.getAnulado()){
    } else {
        return true;
        } 
    //Coonsulta al modulo "Grado"
    Long idAuxiliarGrado = asigGrado.getGrado_id();
    return ConsultorRegistroGrado.existeGrado(idAuxiliarGrado, emf);
    }
    
    
}
