/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.curso;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.curso.orm.CursoEntity;
import sce.principal.elemento_asignatura.curso.orm.CursoJpaController;

/**
 *
 * @author Usuario
 */
public class ConsultorRegistroCurso {
    public static boolean existeCurso(Long idCurso, EntityManagerFactory emf) {
        if (idCurso == null) {
            return false;
        } if (emf == null) {
            return false;
        } if (!emf.isOpen()) {
            return false;
        }
        CursoEntity asigCurso = new CursoJpaController(emf).findCursoEntity(idCurso);
        return !(asigCurso == null);
    }
    public static boolean existeCurso(String nombreCurso, EntityManagerFactory emf) {
        if (nombreCurso == null) {
            return false;
        } if (emf == null) {
            return false;
        } if (!emf.isOpen()) {
            return false;
        }
        CursoEntity asigCurso = new CursoJpaController(emf).buscarPorNombre(nombreCurso);
        return !(asigCurso == null);
    }
    public static RegistroInformacionCurso obtenerRegistroInformacionCurso(Long idCurso, EntityManagerFactory emf) {
        if (!existeCurso(idCurso, emf)) {
            return null;
        }
        RegistroCurso auxRegistro = new RegistroCurso(idCurso, emf);
        RegistroInformacionCurso registro = new RegistroInformacionCurso();
        registro.setIdCurso(auxRegistro.getIdElementoAsignatura());
        registro.setNombreCurso(auxRegistro.getNombreCurso());
        registro.setDescripcionCurso(auxRegistro.getDescripcionCurso());
        return registro;
    }
    public static RegistroCurso obtenerRegistroCurso(Long idCurso, EntityManagerFactory emf) {
        if (!existeCurso(idCurso, emf)) {
            return null;
        }
        return new RegistroCurso(idCurso, emf);
    }
    public static ArrayList<RegistroCurso> obtenerTodosLosCursos(EntityManagerFactory emf) {
        ArrayList<RegistroCurso> cursos = new ArrayList<>();
        List<CursoEntity> cursosExistentes = new CursoJpaController(emf).findCursoEntityEntities();
        for (CursoEntity cursoExistente : cursosExistentes) {
            RegistroCurso nuevo = new RegistroCurso();
            nuevo.setIdElementoAsignatura(cursoExistente.getId());
            nuevo.setNombreCurso(cursoExistente.getCurso());
            nuevo.setDescripcionCurso(cursoExistente.getDescripcion());
            cursos.add(nuevo);
        }
        return cursos;
    }
}