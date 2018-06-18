/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel;

import java.sql.Connection;
import sce.nivel.basico.AsignacionBasicoBuilder;
import sce.nivel.basico.AsignacionCatedraticoBasico;
import sce.nivel.basico.AsignacionCursoBasico;
import sce.nivel.basico.AsignacionEstudianteBasico;
import sce.nivel.basico.AsignacionGradoBasico;
import sce.nivel.basico.CatedraticoBasico;
import sce.nivel.basico.CicloEscolarBasico;
import sce.nivel.basico.CursoBasico;
import sce.nivel.basico.EstudianteBasico;
import sce.nivel.basico.GradoBasico;
import sce.principal.Asignacion;
import sce.principal.AsignacionBuilder;
import sce.principal.CentroEducativo;
import sce.principal.ElementoEducativo;
import sce.principal.Persona;

/**
 *
 * @author Usuario
 */
public class NivelEducativo implements CentroEducativo {
    private final Connection conexion;
    
    public NivelEducativo(Connection conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public Persona crearPersona(int tipoPersona) {
        switch(tipoPersona) {
            case CentroEducativo.TIPO_PERSONA_ESTUDIANTE:
                return new EstudianteBasico(conexion);
            case CentroEducativo.TIPO_PERSONA_CATEDRATICO:
                return new CatedraticoBasico(conexion);
        }
        return null;
    }

    @Override
    public ElementoEducativo crearElementoEducativo(int tipoElemento) {
        switch(tipoElemento) {
            case CentroEducativo.TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR:
                return new CicloEscolarBasico(conexion);
            case CentroEducativo.TIPO_ELEMENTO_EDUCATIVO_GRADO:
                System.out.println("NivelBasico->crearElementoEducativo(TIPO_ELEMENTO_EDUCATIVO_GRADO)");
                return new GradoBasico();
            case CentroEducativo.TIPO_ELEMENTO_EDUCATIVO_CURSO:
                System.out.println("NivelBasico->crearElementoEducativo(TIPO_ELEMENTO_EDUCATIVO_CURSO)");
                return new CursoBasico();
        }
        System.out.println("NivelBasico->crearElementoEducativo(No reconocido = "+tipoElemento+")");
        return null;
    }

    @Override
    public Asignacion crearAsignacion(int tipoAsignacion) {
        switch(tipoAsignacion) {
            case CentroEducativo.TIPO_ASIGNACION_GRADO:
                System.out.println("NivelBasico->crearAsignacion(TIPO_ASIGNACION_GRADO)");
                return new AsignacionGradoBasico();
            case CentroEducativo.TIPO_ASIGNACION_CURSO:
                System.out.println("NivelBasico->crearAsignacion(TIPO_ASIGNACION_CURSO)");
                return new AsignacionCursoBasico();
            case CentroEducativo.TIPO_ASIGNACION_ESTUDIANTE:
                System.out.println("NivelBasico->crearAsignacion(TIPO_ASIGNACION_ESTUDIANTE)");
                return new AsignacionEstudianteBasico();
            case CentroEducativo.TIPO_ASIGNACION_CATEDRATICO:
                System.out.println("NivelBasico->crearAsignacion(TIPO_ASIGNACION_CATEDRATICO)");
                return new AsignacionCatedraticoBasico();
        }
        System.out.println("NivelBasico->crearAsignacion(No reconocido = "+tipoAsignacion+")");
        return null;
    }
    @Override
    public AsignacionBuilder crearAsignacionBuilder() {
        System.out.println("NivelBasico->crearAsignacionBuilder()");
        return new AsignacionBasicoBuilder();
    }
}