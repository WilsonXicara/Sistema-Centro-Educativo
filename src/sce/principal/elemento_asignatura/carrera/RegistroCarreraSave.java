/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.carrera;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.excepciones.PreexistingEntityException;
import sce.principal.elemento_asignatura.carrera.orm.CarreraJpaController;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.AbstractRegistroElementoAsignaturaSave;
import sce.principal.elemento_asignatura.carrera.orm.CarreraEntity;

/**
 *
 * @author juan_
 */
public class RegistroCarreraSave extends AbstractRegistroElementoAsignaturaSave {

    @Override
    public void guardarElementosAsignatura(EntityManagerFactory emf) throws PreexistingEntityException, ExcepcionTipoNoSoportado {
        for (AbstractElementoAsignatura elemento : elementosAsignatura) {
            if (elemento instanceof RegistroCarrera) {
                RegistroCarrera registroCarrera = (RegistroCarrera)elemento;
                CarreraEntity carrera = (CarreraEntity)registroCarrera.getElementoAsignaturaEntity();
                CarreraJpaController controller = new CarreraJpaController(emf);
                if (carrera.getId() == null) {
                    CarreraEntity existente = controller.buscarPorNombre(carrera.getNombre());
                    if (existente != null) {
                        throw new PreexistingEntityException("La carrera '"+carrera.getNombre()+"' ya existe");
                    }
                    controller.create(carrera);  
                } else {
                    try {
                        controller.edit(carrera);     
                    } catch (Exception ex) {
                        Logger.getLogger(RegistroCarreraSave.class.getName()).log(Level.SEVERE, null, ex);
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
