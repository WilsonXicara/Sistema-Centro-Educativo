/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.command;

import sce.principal.Asignacion;

/**
 *
 * @author Usuario
 */
public interface AsignacionCommand {
    /**
     * Ejecuta la acción correspondiente a la asignación, según el tipo de la misma.
     * @param asignador
     */
    public void crearAsignacion(Asignacion asignador);
}
