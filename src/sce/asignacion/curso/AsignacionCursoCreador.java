/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.principal.command.AsignacionCommand;
import sce.principal.entity.AsignacionCarreraEntity;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.entity.AsignacionGradoEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.DistribucionNotasEntity;
import sce.principal.ormjpa.AsignacionCursoJpaController;
import sce.principal.ormjpa.DistribucionNotasJpaController;

/**
 *
 * @author Usuario
 */
public class AsignacionCursoCreador implements AsignacionCommand {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCarrera;
    private Long idAsignacionGrado;
    private final ArrayList<CursoEntity> listaCursos;
    private final ArrayList<DistribucionNotasEntity> listaDistribucionNotas;
    
    public AsignacionCursoCreador(EntityManagerFactory emf, AsignacionCarreraEntity carrera) {
        this.emf = emf;
        this.idAsignacionCarrera = carrera.getId();
        this.listaCursos = new ArrayList<>();
        this.listaDistribucionNotas = new ArrayList<>();
    }
    
    public void setGrado(AsignacionGradoEntity grado) { this.idAsignacionGrado = grado.getId(); }
    public void addCurso(CursoEntity curso, DistribucionNotasEntity distribucionNota) {
        this.listaCursos.add(curso);
        this.listaDistribucionNotas.add(distribucionNota);
    }

    @Override
    public void crearAsignacion() {
        // Se crean tantos registros en 'asignacion_curso' como CursoEntity estén agregados. Los pasos son:
        // 1. Para cada curso:
        //    1.1. Crear el registro en 'distribucion_notas'
        //    1.2. Crear el registro en 'asignacion_curso'
        int cantidad = listaCursos.size(), index;
        for(index=0; index<cantidad; index++) {
            DistribucionNotasEntity distribucion = listaDistribucionNotas.get(index);
            // PASO 1.1:
            new DistribucionNotasJpaController(emf).create(distribucion);
            // PASO 1.2:
            CursoEntity curso = listaCursos.get(index);
            AsignacionCursoEntity asignacion = new AsignacionCursoEntity();
            asignacion.setAsignacion_carrera_id(idAsignacionCarrera);
            asignacion.setAsignacion_grado_id(idAsignacionGrado);
            asignacion.setCurso_id(curso.getId());
            asignacion.setDistribucion_notas_id(distribucion.getId());
            new AsignacionCursoJpaController(emf).create(asignacion);
        }
    }
}
