/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.persona.estudiante.orm.EstudianteEntity;
import sce.persona.estudiante.orm.EstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudianteBuscador {
    public static ArrayList<InformacionEstudianteParaListado> obtenerListadoEstudiantesPorCurso(EntityManagerFactory emf, Long idAsignacionCurso) {
        ArrayList<InformacionEstudianteParaListado> listaEstudiantes = new ArrayList<>();
        List<EstudianteEntity> estudiantes = new EstudianteJpaController(emf).buscarPorActividad(idAsignacionCurso);
        for (EstudianteEntity estudiante : estudiantes) {
            listaEstudiantes.add(new InformacionEstudianteParaListado(
                    estudiante.getId(),
                    estudiante.getNombres(), estudiante.getApellidos()));
        }
        return listaEstudiantes;
    }
}