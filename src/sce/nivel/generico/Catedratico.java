/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.generico;

import javax.persistence.EntityManagerFactory;
import sce.principal.command.CatedraticoCommand;
import sce.principal.entity.CatedraticoEntity;
import sce.principal.ormjpa.CicloEscolarJpaController;

/**
 *
 * @author Usuario
 */
public class Catedratico implements CatedraticoCommand {
    private EntityManagerFactory emf;
    private CatedraticoEntity catedratico;

    @Override
    public void consultarCicloEscolar() {
        new CicloEscolarJpaController(emf).findCicloEscolarEntity(0l);
    }

    @Override
    public void consultarGrados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarCursos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
