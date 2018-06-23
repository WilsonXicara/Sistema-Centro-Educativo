/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.command;

/**
 *
 * @author Usuario
 */
public interface CursoCommand {
    /**
     * Muestra el Catedratico responsable del CursoCommand actual.
     */
    public void consultarCatedratico();
    /**
     * Muestra a los Estudiantes asignados al CursoCommand actual.
     */
    public void consultarParticipantes();
    /**
     * Muestra la forma en que están distribuidas las notas del CursoCommand actual.
     */
    public void consultarDistribucionNotas();
    /**
     * Muestra la distrución actual de notas del curso y posibilita la edición de la misma.
     */
    public void crearDistribucionNotas();
    public void modificarNotas();
}
