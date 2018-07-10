/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico;

import java.util.Arrays;
import sce.persona.PersonaEntity;
import sce.persona.builder.AbstractInformacionPersona;
import sce.persona.catedratico.orm.CatedraticoEntity;

/**
 *
 * @author Usuario
 */
public class InformacionCatedratico extends AbstractInformacionPersona {
    private CatedraticoEntity registroCatedratico;

    @Override
    public void setRegistroPersonaEntity(PersonaEntity registroPersona) {
        this.registroCatedratico = (CatedraticoEntity)registroPersona;
        this.atributos = Arrays.asList(registroCatedratico.getAtributos());
    }
    @Override
    public boolean setValorAtributo(String atributo, String valor) {
        // Se comprueba en dónde se encuentra dicho atributo
        int index = atributos.indexOf(atributo);
        if (index == -1) {
            // No está en EstudianteEntity. Se comprueba si está en atributosAdicionales
            index = atributosAdicionales.indexOf(atributo);
            if (index == -1) {
                // No existe el atributo
                return false;
            } else {
                // Es un atributo de atributosAdicionales
                valoresAdicionales.set(index, valor.replace("\"", ""));
                return true;
            }
        } else {
            // Es un atributo de EstudianteEntity
            return this.registroCatedratico.setValorAtributo(atributo, valor);
        }
    }
    @Override
    public String getValorAtributo(String atributo) {
        // Se comprueba en dónde se encuentra dicho atributo
        int index = atributos.indexOf(atributo);
        if (index == -1) {
            // No está en EstudianteEntity. Se comprueba si está en atributosAdicionales
            index = atributosAdicionales.indexOf(atributo);
            if (index == -1) {
                // No existe el atributo
                return null;
            } else {
                // Es un atributo de atributosAdicionales
                return valoresAdicionales.get(index);
            }
        } else {
            // Es un atributo de EstudianteEntity
            return this.registroCatedratico.getValorAtributo(atributo);
        }
    }
    @Override
    public PersonaEntity getPersonaEntity() { return registroCatedratico; }
}