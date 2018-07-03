/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.curso;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.ElementoAsignaturaEntity;
import sce.principal.elemento_asignatura.curso.orm.CursoEntity;
import sce.principal.elemento_asignatura.curso.orm.CursoJpaController;

/**
 *
 * @author Usuario
 */
public class RegistroCurso extends AbstractElementoAsignatura {
    private final RegistroInformacionCurso registroCurso;
    
    public RegistroCurso() {
        this.registroCurso = new RegistroInformacionCurso();
        this.registroCurso.setIdCurso(idElementoAsignatura = null);
    }
    public RegistroCurso(Long idCurso, EntityManagerFactory emf) {
        CursoEntity auxCurso = new CursoJpaController(emf).findCursoEntity(idCurso);
        this.registroCurso = new RegistroInformacionCurso();
        if (auxCurso != null) {
            this.registroCurso.setIdCurso(this.idElementoAsignatura = idCurso);
            this.registroCurso.setNombreCurso(auxCurso.getCurso());
            this.registroCurso.setDescripcionCurso(auxCurso.getDescripcion());
        } else {
            this.registroCurso.setIdCurso(this.idElementoAsignatura = null);
        }
    }
    
    public String getNombreCurso() { return this.registroCurso.getNombreCurso(); }
    public String getDescripcionCurso() { return this.registroCurso.getDescripcionCurso(); }
    public boolean setNombreCurso(String nombreCurso) {
        if (this.idElementoAsignatura == null) {
            this.registroCurso.setNombreCurso(nombreCurso);
            return true;
        }
        return false;
    }
    public void setDescripcionCurso(String descripcionCurso) { this.registroCurso.setDescripcionCurso(descripcionCurso); }
    
    @Override
    public ElementoAsignaturaEntity getElementoAsignaturaEntity() {
        CursoEntity curso = new CursoEntity(registroCurso.getNombreCurso(), registroCurso.getDescripcionCurso());
        curso.setId(idElementoAsignatura);
        return curso;
    }
}