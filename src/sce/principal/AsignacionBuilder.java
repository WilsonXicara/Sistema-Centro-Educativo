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
public interface AsignacionBuilder {
    /**
     * Método para insertar un elemento principal a la asignación correspondiente.
     * @param principal que es un CicloEscolar o un Grado (para una Asignación de tipo AsignacionCurso)
     */
    public void setElementoPrincipal(ElementoAsignatura principal);
    /**
     * Método para insertar un elemento secundario a la asignación correspondiente.
     * @param secundario que es un Grado o un Curso
     */
    public void setElementoSecundario(ElementoAsignatura secundario);
    /**
     * Método para insertar un elemento terciario a la asignación correspondiente.
     * @param terciario que es un Estudiante o un Catedratico
     */
    public void setElementoTerciario(Persona terciario);
    /**
     * Método para agregar la AsignacionCommand que se ha de construir a partir desde esta clase.
     * @param asignacion cualquiera de las Asignaciones existentes
     * @param tipoAsignacion cualquiera de los valores de CentroEducativo.TIPO_ASIGNACION_*
     */
    public void setAsignacion(Asignacion asignacion, int tipoAsignacion);
    /**
     * Permite obtener el objeto AsignacionCommand ya construido.
     * @return el asignador
     */
    public Asignacion getAsignacion();
}
