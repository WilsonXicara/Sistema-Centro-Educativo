/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.generico;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.principal.Asignacion;
import sce.principal.command.AsignacionCommand;
import sce.principal.entity.AsignacionEstudianteEntity;
import sce.principal.entity.CicloEscolarEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.DistribucionNotasEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.entity.GradoEntity;
import sce.principal.entity.NotaDistribucionNotasEntity;
import sce.principal.entity.NotasEstudianteEntity;
import sce.principal.ormjpa.AsignacionEstudianteJpaController;
import sce.principal.ormjpa.CursoJpaController;
import sce.principal.ormjpa.DistribucionNotasJpaController;
import sce.principal.ormjpa.EstudianteJpaController;
import sce.principal.ormjpa.NotaDistribucionNotasJpaController;
import sce.principal.ormjpa.NotasEstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudiante implements Asignacion, AsignacionCommand {
    public static final int ASIGNACION_A_GRADO = 0;
    public static final int ASIGNACION_A_CURSOS = 1;
    
    private final int tipoAsignacion;
    private EntityManagerFactory emf;
    private ArrayList<AsignacionEstudianteEntity> asignacionEst;
    private CicloEscolarEntity ciclo;
    private GradoEntity grado;
    private ArrayList<CursoEntity> cursos;
    private ArrayList<EstudianteEntity> estudiantes;
    
    
    public AsignacionEstudiante(EntityManagerFactory emf, int tipoAsignacion) {
        this.emf = emf;
        this.tipoAsignacion = tipoAsignacion;
    }

    public void setCiclo(CicloEscolarEntity ciclo) {
        this.ciclo = ciclo;
    }

    public void setGrado(GradoEntity grado) {
        this.grado = grado;
    }

    public void addCursos(CursoEntity curso) {
        this.cursos.add(curso);
    }

    public void addEstudiantes(EstudianteEntity estudiante) {
        this.estudiantes.add(estudiante);
    }

    @Override
    public void crearAsignacion() {
        AsignacionEstudianteJpaController asignador = new AsignacionEstudianteJpaController(emf);
        for (EstudianteEntity estudiante : estudiantes) {
            AsignacionEstudianteEntity nuevaAsignacion = new AsignacionEstudianteEntity();
            nuevaAsignacion.setCiclo_escolar_id(ciclo.getId());
            if (tipoAsignacion == ASIGNACION_A_GRADO) {
                nuevaAsignacion.setGrado_id(grado.getId());
            } else {
                // QUEDA PENDIENTE LA ASIGNACION A CURSOS
            }
            nuevaAsignacion.setEstudiante_id(estudiante.getId());
            // GUARDAR EN LA BD
            asignador.create(nuevaAsignacion);
            estudiante.setAsignacion_id(nuevaAsignacion.getId());
            try {
                // Actualiza estudiante
                new EstudianteJpaController(emf).edit(estudiante);
            } catch (Exception ex) {
                Logger.getLogger(AsignacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
            // OBTENER LA DISTRIBUCIÓN DE NOTAS DADA POR EL DIRECTOR (a cada curso)
            List<CursoEntity> cursos = new CursoJpaController(emf).findCursoEntityEntities();
            for (CursoEntity curso : cursos) {
                DistribucionNotasEntity distribucion = new DistribucionNotasJpaController(emf).findDistribucionNotasEntity(0l);
                NotaDistribucionNotasEntity dist = new NotaDistribucionNotasEntity();
                dist.setDistribucion_notas_id(distribucion.getId());
                dist.setAcumulado(0f);
                new NotaDistribucionNotasJpaController(emf).create(dist);
                // dist ya tiene ID
                NotasEstudianteEntity notaEst = new NotasEstudianteEntity();
                notaEst.setEstudiante_id(estudiante.getId());
                notaEst.setCurso_id(curso.getId());
                notaEst.setNota_obtenida(0f);
                notaEst.setNota_distribucion_notas_id(dist.getId());
                // Crea el registro en sce_bd.nuevaAsignacion
                new NotasEstudianteJpaController(emf).create(notaEst);
            }
            
        }
    }
    
}
