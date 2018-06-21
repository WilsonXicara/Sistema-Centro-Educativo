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
     * @param curso
     */
    public void consultarCatedratico(sce.principal.entity.CursoEntity curso);
    /**
     * Muestra a los Estudiantes asignados al CursoCommand actual.
     * @param curso
     */
    public void consultarParticipantes(sce.principal.entity.CursoEntity curso);
    /**
     * Muestra la forma en que están distribuidas las notas del CursoCommand actual.
     * @param curso
     */
    public void consultarDistribucionNotas(sce.principal.entity.CursoEntity curso);
    /**
     * Muestra la distrución actual de notas del curso y posibilita la edición de la misma.
     * @param curso
     */
    public void crearDistribucionNotas(sce.principal.entity.CursoEntity curso);
    public void modificarNotas(sce.principal.entity.CursoEntity curso);
}
