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
    public void consultarCatedratico();
    public void consultarParticipantes();
    public void consultarDistribucionNotas();
    public void crearDistribucionNotas();
    public void modificarNotas();
}
