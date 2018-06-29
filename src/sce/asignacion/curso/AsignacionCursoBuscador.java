/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.ormjpa.AsignacionCursoJpaController;

/**
 *
 * @author Usuario
 */
public class AsignacionCursoBuscador {
    public static final int CURSOS_A_CARRERA = 300;
    public static final int CURSOS_A_GRADO = 301;
    
    public static ArrayList<AsignacionCursoEntity> obtenerCursoAsignados(EntityManagerFactory emf, int tipoAsignacion, Long idAsociado) {
        switch(tipoAsignacion) {
            case CURSOS_A_CARRERA:
                return (ArrayList<AsignacionCursoEntity>)new AsignacionCursoJpaController(emf)
                        .buscarPorCarrera(idAsociado);
            case CURSOS_A_GRADO:
                return (ArrayList<AsignacionCursoEntity>)new AsignacionCursoJpaController(emf)
                        .buscarPorGrado(idAsociado);
        }
        return new ArrayList<>();
    }
}
