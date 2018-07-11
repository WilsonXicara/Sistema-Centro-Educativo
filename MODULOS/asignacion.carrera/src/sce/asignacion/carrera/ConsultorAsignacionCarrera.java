/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.principal.elemento_asignatura.carrera.ConsultorRegistroCarrera;
import sce.principal.elemento_asignatura.ciclo.ConsultorRegistroCiclo;

/**
 *
 * @author juan_
 */
public class ConsultorAsignacionCarrera {
    public static Boolean existeAsignacionCarrera(Long idAsignacionCarrera, EntityManagerFactory emf){
        AsignacionCarreraEntity asigCarrera = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        return asigCarrera != null;
    }
    
    public static Boolean isAsignacionCarreraAnulada(Long idAsignacionCarrera, EntityManagerFactory emf){
        System.out.println("ID = "+idAsignacionCarrera);
        if (!existeAsignacionCarrera(idAsignacionCarrera,emf)){
            return true;
        }
        AsignacionCarreraEntity asigCarrera = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        
        //Consulta al módulo "Carrera"
        Long idAuxiliarCarrera = asigCarrera.getCarrera_id();
        if (!ConsultorRegistroCarrera.existeCarrera(idAuxiliarCarrera, emf)){
            
            return true;
        }
        //Consultra al módilo "CicloEscolar"
        Long idAuxiliarCicloE = asigCarrera.getCiclo_escolar_id();
        if (!ConsultorRegistroCiclo.existeCicloEscolar(idAuxiliarCicloE, emf) || !ConsultorRegistroCiclo.esVigenteCicloEscolar(idAuxiliarCicloE, emf))
        {
            return true;
        }
        return asigCarrera.getAnulado();
    }
    
}
