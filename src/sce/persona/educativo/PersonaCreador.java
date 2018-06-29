/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.educativo;

import javax.persistence.EntityManagerFactory;
import sce.principal.command.PersonaCommand;

/**
 *
 * @author Usuario
 */
public class PersonaCreador implements PersonaCommand {
    private final EntityManagerFactory emf;
    
    public PersonaCreador(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void crearPersona() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
