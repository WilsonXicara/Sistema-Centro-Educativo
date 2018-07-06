/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.ciclo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.excepciones.PreexistingEntityException;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.AbstractRegistroElementoAsignaturaSave;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarEntity;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarJpaController;

/**
 *
 * @author juan_
 */
public class RegistroCicloSave extends AbstractRegistroElementoAsignaturaSave {

    @Override
    public void guardarElementosAsignatura(EntityManagerFactory emf) throws PreexistingEntityException, ExcepcionTipoNoSoportado {
        for (AbstractElementoAsignatura elemento : elementosAsignatura) {
            if (elemento instanceof RegistroCiclo) {
                RegistroCiclo registroCiclo = (RegistroCiclo)elemento;
                CicloEscolarEntity cicloEscolar = (CicloEscolarEntity)registroCiclo.getElementoAsignaturaEntity();
                CicloEscolarJpaController controller = new CicloEscolarJpaController(emf);
                if (cicloEscolar.getId() == null) {
                    CicloEscolarEntity existente = controller.buscarPorAnio(cicloEscolar.getCiclo_escolar());
                    if (existente != null) {
                        throw new PreexistingEntityException("El ciclo escolar '"+cicloEscolar.getCiclo_escolar()+"' ya existe");
                    }
                    controller.create(cicloEscolar);  
                } else {
                    try {
                        controller.edit(cicloEscolar);    
                    } catch (Exception ex) {
                        Logger.getLogger(RegistroCicloSave.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                // Si este guardador en específico no soporta otro tipo de RegistroElementoAsignaturaCommand
                throw new ExcepcionTipoNoSoportado("La clase '"+this.getClass().getName()+"' no puede guardar un registro de la clase '"+elemento.getClass().getName()+"'");
            }
        }
        // Limpiar el ArrayList para evitar que se intenten guardar los registros nuevamente
        this.elementosAsignatura.clear();
    }
    
}
