/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.estudiante;

import java.util.Arrays;
import sce.persona.PersonaEntity;
import sce.persona.builder.AbstractInformacionPersona;
import sce.persona.estudiante.orm.EstudianteEntity;

/**
 *
 * @author Usuario
 */
public class InformacionEstudiante extends AbstractInformacionPersona {
    private EstudianteEntity registroEstudiante;
    
    @Override
    public void setRegistroPersonaEntity(PersonaEntity registroPersona) {
        this.registroEstudiante = (EstudianteEntity)registroPersona;
        this.atributos = Arrays.asList(registroEstudiante.getAtributos());
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
            return this.registroEstudiante.setValorAtributo(atributo, valor);
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
            return this.registroEstudiante.getValorAtributo(atributo);
        }
    }
    @Override
    public PersonaEntity getPersonaEntity() { return registroEstudiante; }
}