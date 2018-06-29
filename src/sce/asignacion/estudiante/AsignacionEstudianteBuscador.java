/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.principal.entity.AsignacionEstudianteEntity;
import sce.principal.ormjpa.AsignacionEstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudianteBuscador {
    public static final int ESTUDIANTES_A_CARRERA = 400;
    public static final int ESTUDIANTES_A_GRADO = 401;
    
    public static ArrayList<AsignacionEstudianteEntity> obtenerEstudiantesAsignados(EntityManagerFactory emf, int tipoAsignacion, Long idAsociado) {
        switch(tipoAsignacion) {
            case ESTUDIANTES_A_CARRERA:
                return (ArrayList<AsignacionEstudianteEntity>)new AsignacionEstudianteJpaController(emf)
                        .buscarPorCarrera(idAsociado);
            case ESTUDIANTES_A_GRADO:
                return (ArrayList<AsignacionEstudianteEntity>)new AsignacionEstudianteJpaController(emf)
                        .buscarPorGrado(idAsociado);
        }
        return new ArrayList<>();
    }
}
