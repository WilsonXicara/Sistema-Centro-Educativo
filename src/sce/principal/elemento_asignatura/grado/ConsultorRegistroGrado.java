/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.grado;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.grado.orm.GradoEntity;
import sce.principal.elemento_asignatura.grado.orm.GradoJpaController;

/**
 *
 * @author juan_
 */
public class ConsultorRegistroGrado {
    public static Boolean existeGrado(Long idGrado, EntityManagerFactory emf){
        GradoEntity grado = new GradoJpaController(emf).findGradoEntity(idGrado);
        return grado != null;
    }
}
