/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal;

/**
 *
 * @author Usuario
 */
public interface ElementoEducativoCreator {
    public static final int TIPO_PERSONA_ESTUDIANTE = 10;
    public static final int TIPO_PERSONA_CATEDRATICO = 11;
    public static final int TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR = 20;
    public static final int TIPO_ELEMENTO_EDUCATIVO_GRADO = 21;
    public static final int TIPO_ELEMENTO_EDUCATIVO_CURSO = 22;
    public static final int TIPO_ASIGNACION_ESTUDIANTE = 30;
    public static final int TIPO_ASIGNACION_CATEDRATICO = 31;
    public static final int TIPO_ASIGNACION_GRADO = 32;
    public static final int TIPO_ASIGNACION_CURSO = 33;

    public Persona obtenerPersona(int tipoPersona);
    /**
     * Crea una nueva instancia de tipo Persona.
     * @param tipoPersona cualquiera de los valores de ElementoEducativoCreator.TIPO_PERSONA_*
     * @param ID
     * @return una nueva Persona
     */
    public Persona obtenerPersona(int tipoPersona, Long ID);
    /**
     * Crea una nueva instancia de tipo ElementoAsignatura
     * @param tipoElemento cualquiera de los valores de ElementoEducativoCreator.TIPO_ELEMENTO_EDUCATIVO_*
     * @param ID
     * @return un nuevo ElementoAsignatura
     */
    public ElementoAsignatura obtenerElementoAsignatura(int tipoElemento, Long ID);
    public ElementoAsignatura obtenerElementoAsignatura(int tipoElemento);
    /**
     * Crea una nueva instancia de tipo AsignacionCommand.
     * @param tipoAsignacion cualquiera de los valores de ElementoEducativoCreator.TIPO_ASIGNACION_*
     * @param ID
     * @return una nueva AsignacionCommand
     */
    public Asignacion obtenerAsignacion(int tipoAsignacion, Long ID);
    public Asignacion obtenerAsignacion(int tipoAsignacion);
    /**
     * Crea una nueva instancia para construir una AsignacionCommand.
     * @return un objeto que se utilizará para construir una instancia de AsignacionCommand
     */
    public AsignacionBuilder obtenerAsignacionBuilder();
}
