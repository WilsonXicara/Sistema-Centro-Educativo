/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

/**
 *
 * @author Usuario
 */
public interface EstudianteCommand {
    public void consultarGrado();
    /**
     * Muestra el listado de Cursos al que ha sido asgnado el EstudianteCommand actual.
     */
    public void consultarCursos();
}
