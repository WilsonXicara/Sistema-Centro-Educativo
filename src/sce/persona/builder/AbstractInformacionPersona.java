/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.builder;

import java.util.ArrayList;
import java.util.List;
import sce.persona.PersonaEntity;

/**
 *
 * @author Usuario
 */
public abstract class AbstractInformacionPersona {
    protected ArrayList<String> atributosAdicionales, valoresAdicionales;
    protected List<String> atributos;
    
    public abstract void setRegistroPersonaEntity(PersonaEntity registroPersona);
    public abstract boolean setValorAtributo(String atributo, String valor);
    public abstract String getValorAtributo(String atributo);
    public abstract PersonaEntity getPersonaEntity();
    
    public void setAtributosAdicionales(ArrayList<String> atributosAdicionales) {
        if (this.atributosAdicionales == null) {
            this.atributosAdicionales = atributosAdicionales;
            this.valoresAdicionales = new ArrayList<>();
            for (String atributo : atributosAdicionales) {
                this.valoresAdicionales.add("");
            }
        }
    }
    public ArrayList<String> getAtributos() { return new ArrayList<>(atributos); }
    public ArrayList<String> getAtributosAdicionales() { return (ArrayList<String>)(atributosAdicionales).clone(); }
    public ArrayList<String> getAtributosCompletos() {
        // Se clona 'atributosAdicionales' y se concatena al inicio 'atributos'
        ArrayList<String> copia = (ArrayList<String>)atributosAdicionales.clone();
        copia.addAll(0, atributos);
        return copia;
    }
    public ArrayList<String> getValoresAtributosAdicionales() { return (ArrayList<String>)valoresAdicionales.clone(); }
    public boolean contieneAtributo(String atributo) { return atributos.contains(atributo) || atributosAdicionales.contains(atributo); }
}