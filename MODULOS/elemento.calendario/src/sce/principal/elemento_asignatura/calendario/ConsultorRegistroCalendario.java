/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.calendario;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.calendario.orm.CalendarioCicloEscolarEntity;
import sce.principal.elemento_asignatura.calendario.orm.CalendarioCicloEscolarJpaController;

/**
 *
 * @author juan_
 */
public class ConsultorRegistroCalendario {
    public static Boolean existeCalendarioCicloEscolar(Long idCalendarioCicloEscolar, EntityManagerFactory emf){
        CalendarioCicloEscolarEntity calendarioExistente = new CalendarioCicloEscolarJpaController(emf).findCalendarioCicloEscolarEntity(idCalendarioCicloEscolar);
        return calendarioExistente != null;
    }

}
