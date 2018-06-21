/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.command;

import sce.principal.entity.GradoEntity;

/**
 *
 * @author Usuario
 */
public interface GradoCommand {
    /**
     * Muestra el listado de Cursos asignados al GradoCommand actual.
     * @param grado
     */
    public void consultarCursos(GradoEntity grado);
    /**
     * Muestra el Catedratico asignado al GradoCommand actual.
     * @param grado
     */
    public void consultarCatedratico(GradoEntity grado);
    /**
     * Muestra a los Estudiantes asignados al GradoCommand actual.
     * @param grado
     */
    public void consultarEstudiantes(GradoEntity grado);
}
