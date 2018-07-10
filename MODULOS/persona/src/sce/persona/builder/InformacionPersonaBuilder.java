/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.builder;

/**
 *
 * @author Usuario
 */
public interface InformacionPersonaBuilder {
    public void nuevoRegistroPersona();
    public void nuevoRegistroPersona(Long idPersona);
    public void obtenerAtributosAdicionales();
    public void obtenerValoresAdicionales();
    public AbstractInformacionPersona getInformacionPersona();
}