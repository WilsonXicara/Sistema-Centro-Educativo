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
public interface CicloEscolarCommand {
    /**
     * Marca el CicloEscolarEntity como activo y lo deja listo para cuaquier operación sobre el mismo (especialmente las asignaciones)ñ
     * @param cicloEscolar
     */
    public void abrirCiclo(sce.principal.entity.CicloEscolarEntity cicloEscolar);
    /**
     * Cierra el CicloEscolarEntity. Con ello, ya no se pueden realizar asignaciones.
     * @param cicloEscolar
     */
    public void cerrarCiclo(sce.principal.entity.CicloEscolarEntity cicloEscolar);
    /**
     * Muestra el listado de Grados asignados al CicloEscolarEntity.
     * @param cicloEscolar
     */
    public void consultarGrados(sce.principal.entity.CicloEscolarEntity cicloEscolar);
    /**
     * Muestra el calendario del CicloEscolarEntity.
     * @param cicloEscolar
     */
    public void consultarCalendario(sce.principal.entity.CicloEscolarEntity cicloEscolar);
}
