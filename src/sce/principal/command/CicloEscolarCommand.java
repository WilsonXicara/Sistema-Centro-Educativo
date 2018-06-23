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
     */
    public void abrirCiclo();
    /**
     * Cierra el CicloEscolarEntity. Con ello, ya no se pueden realizar asignaciones.
     */
    public void cerrarCiclo();
    /**
     * Muestra el listado de Grados asignados al CicloEscolarEntity.
     */
    public void consultarGrados();
    /**
     * Muestra el calendario del CicloEscolarEntity.
     */
    public void consultarCalendario();
}
