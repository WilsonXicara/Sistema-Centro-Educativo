/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.command;

import sce.principal.entity.EstudianteEntity;

/**
 *
 * @author Usuario
 */
public interface EstudianteCommand {
    /**
     * Muestra el CicloEscolar al que ha sido asignado el EstudianteCommand actual.
     * @param estudiante
     */
    public void consultarCicloEscolar(EstudianteEntity estudiante);
    /**
     * Muestra el Grado al que ha sido asignado el EstudianteCommand actual.
     * @param estudiante
     */
    public void consultarGrado(EstudianteEntity estudiante);
    /**
     * Muestra el listado de Cursos al que ha sido asgnado el EstudianteCommand actual.
     * @param estudiante
     */
    public void consultarCursos(EstudianteEntity estudiante);
}
