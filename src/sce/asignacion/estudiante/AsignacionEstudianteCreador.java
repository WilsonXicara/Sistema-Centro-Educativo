/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.principal.command.AsignacionCommand;
import sce.principal.entity.AsignacionCarreraEntity;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.entity.AsignacionCursosEstudianteEntity;
import sce.principal.entity.AsignacionEstudianteEntity;
import sce.principal.entity.AsignacionGradoEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.ormjpa.AsignacionCursosEstudianteJpaController;
import sce.principal.ormjpa.AsignacionEstudianteJpaController;
import sce.principal.ormjpa.EstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudianteCreador implements AsignacionCommand {
    private final EntityManagerFactory emf;
    private final AsignacionCarreraEntity carrera;
    private AsignacionGradoEntity grado;
    private EstudianteEntity estudiante;
    private ArrayList<AsignacionCursoEntity> listaCursos;

    public AsignacionEstudianteCreador(EntityManagerFactory emf, AsignacionCarreraEntity carrera) {
        this.emf = emf;
        this.carrera = carrera;
        this.listaCursos = new ArrayList<>();
    }
    
    public void setGrado(AsignacionGradoEntity grado) { this.grado = grado; }
    public void setEstudiante(EstudianteEntity estudiante) { this.estudiante = estudiante; }
    public void addCurso(AsignacionCursoEntity curso) { this.listaCursos.add(curso); }
    public void setCursos(ArrayList<AsignacionCursoEntity> listaCursos) { this.listaCursos = listaCursos; }

    @Override
    public void crearAsignacion() {
        // Se crean tantos registros en 'asignacion_estudiante_cursos' como AsignacionCursoEntity estén agregados. Los pasos son:
        // 1. Crear el registro en 'asignacion_estudiante'
        // 2. Actualizar 'estudiante.asignacion_id' = 'asignacion_estudiante.id'
        // 3. Para cada AsignacionCursoEntity:
	//    3.1. Crear el registro en 'asignacion_estudiante_cursos'
        Long idAsginacionCarrera = carrera.getId();
        Long idGrado = grado == null ? null : grado.getId();
        Long idEstudiante = estudiante.getId();
        // PASO 1:
        AsignacionEstudianteEntity asignacionEst = new AsignacionEstudianteEntity();
        asignacionEst.setAsignacion_carrera_id(idAsginacionCarrera);
        asignacionEst.setAsignacion_grado_id(idGrado);
        asignacionEst.setEstudiante_id(idEstudiante);
        new AsignacionEstudianteJpaController(emf).create(asignacionEst);
        // PASO 2:
        estudiante.setAsignacion_id(asignacionEst.getId());
        try {
            new EstudianteJpaController(emf).edit(estudiante);
        } catch (Exception ex) {
            Logger.getLogger(AsignacionEstudianteCreador.class.getName()).log(Level.SEVERE, null, ex);
        }
        // PASO 3:
        for (AsignacionCursoEntity curso : listaCursos) {
            AsignacionCursosEstudianteEntity asignacionCurso = new AsignacionCursosEstudianteEntity();
            asignacionCurso.setAsignacion_estudiante_id(asignacionEst.getId());
            asignacionCurso.setAsignacion_curso_id(curso.getId());
            asignacionCurso.setAcumulado(0f);
            new AsignacionCursosEstudianteJpaController(emf).create(asignacionCurso);
        }
    }
}
