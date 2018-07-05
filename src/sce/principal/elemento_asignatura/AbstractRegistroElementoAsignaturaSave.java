/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.excepciones.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public abstract class AbstractRegistroElementoAsignaturaSave {
    protected ArrayList<AbstractElementoAsignatura> elementosAsignatura;
    
    public AbstractRegistroElementoAsignaturaSave() {
        this.elementosAsignatura = new ArrayList<>();
    }
    public void addElementoAsignatura(AbstractElementoAsignatura elemento) { this.elementosAsignatura.add(elemento); }
    public AbstractElementoAsignatura getElementoAsignatura(int index) {
        if (index<0 || index>=elementosAsignatura.size()) {
            return null;
        }
        return elementosAsignatura.get(index);
    }
    public boolean eliminarElementoAsignatura(int index) {
        if (index<0 || index>=elementosAsignatura.size()) {
            return false;
        }
        elementosAsignatura.remove(index);
        return true;
    }
    public abstract void guardarElementosAsignatura(EntityManagerFactory emf) throws PreexistingEntityException, ExcepcionTipoNoSoportado;
}
