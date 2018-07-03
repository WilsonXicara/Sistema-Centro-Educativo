/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.consultor;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.carrera.orm.CarreraEntity;
import sce.asignacion.carrera.orm.CarreraJpaController;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.persona.catedratico.orm.CatedraticoJpaController;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarEntity;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarJpaController;
import sce.principal.elemento_asignatura.grado.orm.GradoEntity;
import sce.principal.elemento_asignatura.grado.orm.GradoJpaController;

/**
 *
 * @author juan_
 */
public class ConsultorGeneral {
    
    public static Boolean asignacionCarreraAnulada(Long idAsignacionCarrera, EntityManagerFactory emf){
        AsignacionCarreraEntity carreraAnulada = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        return carreraAnulada.getAnulado();
    }
    
    public static Boolean asignacionCarreraExistente(Long idAsignacionCarrera, EntityManagerFactory emf){
        AsignacionCarreraEntity carreraExistente = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        return carreraExistente != null;
    }
    
    public static Boolean carreraExistente(Long idCarrera, EntityManagerFactory emf){
        CarreraEntity carrera = new CarreraJpaController(emf).findCarreraEntity(idCarrera);
        return carrera != null;
    }
    
    public static Boolean catedraticoExistente(Long idCatedratico, EntityManagerFactory emf){
        CatedraticoEntity catedraticoExistente = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
        return catedraticoExistente != null;
    }
    
    public static Boolean asignacionCatedraticoAnulada(Long idAsignacionCatedratico, EntityManagerFactory emf){
        AsignacionCatedraticoEntity catedraticoAnulado = new AsignacionCatedraticoJpaController(emf).findAsignacionCatedraticoEntity(idAsignacionCatedratico);
        return catedraticoAnulado.getAnulado();
    }
    public static Boolean asignacionCatedraticoExistente(Long idAsignacionCat, EntityManagerFactory emf){
        AsignacionCatedraticoEntity asignacionExistente = new AsignacionCatedraticoJpaController(emf).findAsignacionCatedraticoEntity(idAsignacionCat);
        return asignacionExistente != null;
    }
    public static Boolean asignaciongradoExistente(Long idAsignacionGrado, EntityManagerFactory emf){
        AsignacionGradoEntity asignacionExistente = new AsignacionGradoJpaController(emf).findAsignacion_Grado(idAsignacionGrado);
        return asignacionExistente != null;
    }
    public static Boolean gradoExistente(Long iGrado, EntityManagerFactory emf){
        GradoEntity asigGrado = new GradoJpaController(emf).findGradoEntity(iGrado);
        return asigGrado != null;  
    }   
    public static Boolean asignacionGradoAnulada(Long idAsignacionGrado, EntityManagerFactory emf){
       AsignacionGradoEntity asigGrado = new AsignacionGradoJpaController(emf).findAsignacion_Grado(idAsignacionGrado);
      return asigGrado.getAnulado();
    }   
    public static Boolean cicloExistente(Long idCiclo, EntityManagerFactory emf){
        CicloEscolarEntity cicloEscolar = new CicloEscolarJpaController(emf).findCicloEscolarEntity(idCiclo);
        return cicloEscolar != null;
    }
    public static Boolean cicloVigente(Long idCiclo, EntityManagerFactory emf){
        CicloEscolarEntity cicloEscolar = new CicloEscolarJpaController(emf).findCicloEscolarEntity(idCiclo);
        return !cicloEscolar.isCerrado() && cicloEscolar.isListo();
    }
    
  
}
