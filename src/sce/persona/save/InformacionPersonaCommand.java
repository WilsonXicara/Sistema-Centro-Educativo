/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.save;

import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.persona.builder.AbstractInformacionPersona;
import sce.excepciones.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public interface InformacionPersonaCommand {
    public void guardarInformacionPersona(AbstractInformacionPersona informacionPersona) throws PreexistingEntityException, ExcepcionTipoNoSoportado;
}
