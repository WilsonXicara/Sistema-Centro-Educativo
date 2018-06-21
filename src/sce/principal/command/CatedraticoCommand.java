/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.command;

import sce.principal.entity.CatedraticoEntity;

/**
 *
 * @author Usuario
 */
public interface CatedraticoCommand {
    public void consultarCicloEscolar(CatedraticoEntity estudiante);

    public void consultarGrados(CatedraticoEntity estudiante);

    public void consultarCursos(CatedraticoEntity estudiante);
}
