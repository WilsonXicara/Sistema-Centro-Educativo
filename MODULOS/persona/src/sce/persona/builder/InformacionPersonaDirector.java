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
public class InformacionPersonaDirector {
    private InformacionPersonaBuilder builderPersona;
    private Long idPersona;
    
    public void setInformacionPersonBuilder(InformacionPersonaBuilder builder) {
        this.builderPersona = builder;
    }
    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }
    public void construirInformacionPersona() {
        if (idPersona == null) {
            builderPersona.nuevoRegistroPersona();
        } else {
            builderPersona.nuevoRegistroPersona(idPersona);
        }
        builderPersona.obtenerAtributosAdicionales();
        builderPersona.obtenerValoresAdicionales();
    }
    public  AbstractInformacionPersona getInformacionPersona() { return builderPersona.getInformacionPersona(); }
}
