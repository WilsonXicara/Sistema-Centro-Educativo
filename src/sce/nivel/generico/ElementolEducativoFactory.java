/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.generico;

import javax.persistence.EntityManagerFactory;
import sce.principal.entity.CatedraticoEntity;
import sce.principal.entity.CicloEscolarEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.entity.GradoEntity;
import sce.principal.ormjpa.CatedraticoJpaController;
import sce.principal.ormjpa.CicloEscolarJpaController;
import sce.principal.ormjpa.CursoJpaController;
import sce.principal.ormjpa.EstudianteJpaController;
import sce.principal.ormjpa.GradoJpaController;
import sce.principal.Asignacion;
import sce.principal.AsignacionBuilder;
import sce.principal.Persona;
import sce.principal.entity.AsignacionCatedraticoEntity;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.entity.AsignacionEstudianteEntity;
import sce.principal.entity.AsignacionGradoEntity;
import sce.principal.ormjpa.AsignacionCatedraticoJpaController;
import sce.principal.ormjpa.AsignacionCursoJpaController;
import sce.principal.ormjpa.AsignacionEstudianteJpaController;
import sce.principal.ormjpa.AsignacionGradoJpaController;
import sce.principal.ElementoAsignatura;
import sce.principal.ElementoEducativoCreator;

/**
 *
 * @author Usuario
 */
public class ElementolEducativoFactory implements ElementoEducativoCreator {
    private final EntityManagerFactory emf;
    
    public ElementolEducativoFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public Persona obtenerPersona(int tipoPersona, Long ID) {
        switch(tipoPersona) {
            case ElementoEducativoCreator.TIPO_PERSONA_ESTUDIANTE:
                EstudianteJpaController est = new EstudianteJpaController(emf);
                EstudianteEntity estudiante = est.findEstudianteEntity(ID);
                if (estudiante != null) {
                    return estudiante;
                }
                return new EstudianteEntity();
            case ElementoEducativoCreator.TIPO_PERSONA_CATEDRATICO:
                CatedraticoJpaController cat = new CatedraticoJpaController(emf);
                CatedraticoEntity catedratico = cat.findCatedraticoEntity(ID);
                if (catedratico != null) {
                    return catedratico;
                }
                return new CatedraticoEntity();
        }
        return null;
    }

    @Override
    public Persona obtenerPersona(int tipoPersona) {
        switch(tipoPersona) {
            case ElementoEducativoCreator.TIPO_PERSONA_ESTUDIANTE:
                return new EstudianteEntity();
            case ElementoEducativoCreator.TIPO_PERSONA_CATEDRATICO:
                return new CatedraticoEntity();
        }
        return null;
    }

    @Override
    public ElementoAsignatura obtenerElementoAsignatura(int tipoElemento, Long ID) {
        switch(tipoElemento) {
            case ElementoEducativoCreator.TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR:
                CicloEscolarJpaController cc = new CicloEscolarJpaController(emf);
                CicloEscolarEntity cicloEscolar = cc.findCicloEscolarEntity(ID);
                if (cicloEscolar != null) {
                    return cicloEscolar;
                }
                return new CicloEscolarEntity();
            case ElementoEducativoCreator.TIPO_ELEMENTO_EDUCATIVO_GRADO:
                GradoJpaController gr = new GradoJpaController(emf);
                GradoEntity grado = gr.findGradoEntity(ID);
                if (grado != null) {
                    return grado;
                }
                return new GradoEntity();
            case ElementoEducativoCreator.TIPO_ELEMENTO_EDUCATIVO_CURSO:
                CursoJpaController cu = new CursoJpaController(emf);
                CursoEntity curso = cu.findCursoEntity(ID);
                if (curso != null) {
                    return curso;
                }
                return new CursoEntity();
        }
        return null;
    }
    
    @Override
    public ElementoAsignatura obtenerElementoAsignatura(int tipoElemento) {
        switch(tipoElemento) {
            case ElementoEducativoCreator.TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR:
                return new CicloEscolarEntity();
            case ElementoEducativoCreator.TIPO_ELEMENTO_EDUCATIVO_GRADO:
                return new GradoEntity();
            case ElementoEducativoCreator.TIPO_ELEMENTO_EDUCATIVO_CURSO:
                return new CursoEntity();
        }
        return null;
    }

    @Override
    public Asignacion obtenerAsignacion(int tipoAsignacion, Long ID) {
        switch(tipoAsignacion) {
            case ElementoEducativoCreator.TIPO_ASIGNACION_GRADO:
                AsignacionGradoJpaController agc = new AsignacionGradoJpaController(emf);
                AsignacionGradoEntity asigGr = agc.findAsignacion_Grado(ID);
                if (asigGr != null) {
                    return asigGr;
                }
                return new AsignacionGradoEntity();
            case ElementoEducativoCreator.TIPO_ASIGNACION_CURSO:
                AsignacionCursoJpaController acc = new AsignacionCursoJpaController(emf);
                AsignacionCursoEntity asigCur = acc.findAsignacion_Curso(ID);
                if (asigCur != null) {
                    return asigCur;
                }
                return new AsignacionCursoEntity();
            case ElementoEducativoCreator.TIPO_ASIGNACION_ESTUDIANTE:
                AsignacionEstudianteJpaController aec = new AsignacionEstudianteJpaController(emf);
                AsignacionEstudianteEntity asigEst = aec.findAsignacion_Estudiante(ID);
                if (asigEst != null) {
                    return asigEst;
                }
                return new AsignacionEstudianteEntity();
            case ElementoEducativoCreator.TIPO_ASIGNACION_CATEDRATICO:
                AsignacionCatedraticoJpaController ac = new AsignacionCatedraticoJpaController(emf);
                AsignacionCatedraticoEntity asigCat = ac.findAsignacion_Catedratico(ID);
                if (asigCat != null) {
                    return asigCat;
                }
                return new AsignacionCatedraticoEntity();
        }
        return null;
    }
    
    @Override
    public Asignacion obtenerAsignacion(int tipoAsignacion) {
        switch(tipoAsignacion) {
            case ElementoEducativoCreator.TIPO_ASIGNACION_GRADO:
                return new AsignacionGradoEntity();
            case ElementoEducativoCreator.TIPO_ASIGNACION_CURSO:
                return new AsignacionCursoEntity();
            case ElementoEducativoCreator.TIPO_ASIGNACION_ESTUDIANTE:
                return new AsignacionEstudianteEntity();
            case ElementoEducativoCreator.TIPO_ASIGNACION_CATEDRATICO:
                return new AsignacionCatedraticoEntity();
        }
        return null;
    }

    @Override
    public AsignacionBuilder obtenerAsignacionBuilder() {
        return new AsignacionConcretBuilder();
    }
}