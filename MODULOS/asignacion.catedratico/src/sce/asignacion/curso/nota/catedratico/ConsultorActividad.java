/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.catedratico;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.curso.nota.orm.ActividadEntity;
import sce.asignacion.curso.nota.orm.ActividadJpaController;

/**
 *
 * @author Usuario
 */
public class ConsultorActividad {
    /**
     * Verifica que idAsignacionCurso != null, que idActividad != null, que exista la Asignación de Curso con
     * ID == idAsigancionCurso y que no esté anulada.
     * @param emf
     * @param idActividad el ID de la Actividad que se busca.
     * @return true si pasa las verificaciones; false si no pasa alguna.
     */
    public boolean existeActividad(EntityManagerFactory emf, Long idActividad) {
        if (idActividad==null) {
            return false;
        }
        ActividadEntity actividad = new ActividadJpaController(emf).findActividadEntity(idActividad);
        return actividad != null;
    }
    /**
     * Obtiene la Actividad con ID == idActividad.
     * @param emf
     * @param idActividad el ID de la Actividad que se desea obtener.
     * @return el Entity de la Actividad; o null si no existe.
     */
    public ActividadEntity obtenerActividad(EntityManagerFactory emf, Long idActividad) {
        if (!existeActividad(emf, idActividad)) {
            return null;
        }
        return new ActividadJpaController(emf).findActividadEntity(idActividad);
    }
}
