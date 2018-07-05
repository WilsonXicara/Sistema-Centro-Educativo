/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.carrera;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.carrera.orm.CarreraEntity;
import sce.principal.elemento_asignatura.carrera.orm.CarreraJpaController;

/**
 *
 * @author juan_
 */
public class ConsultorRegistroCarrera {
    public static Boolean existeCarrera(Long idCarrera, EntityManagerFactory emf){
        CarreraEntity carrera = new CarreraJpaController(emf).findCarreraEntity(idCarrera);
        return carrera != null;
    }
    
}
