/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera;

import sce.asignacion.carrera.orm.CarreraEntity;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionCarrera implements AsignacionCommand {
    
    private final EntityManagerFactory emf;
    private CicloEscolarEntity cicloEscolar;
    private CarreraEntity  carrera;

    public AsignacionCarrera(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setCicloEscolar(CicloEscolarEntity cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public void setCarrera(CarreraEntity carrera) {
        this.carrera = carrera;
    }
    
    
    
    @Override
    public void crearAsignacion() {
        AsignacionCarreraJpaController asignador = new AsignacionCarreraJpaController(emf);
        AsignacionCarreraEntity nuevaAsignacion = new AsignacionCarreraEntity();
        nuevaAsignacion.setCiclo_escolar_id(cicloEscolar.getId());
        nuevaAsignacion.setCarrera_id(carrera.getId());
        asignador.create(nuevaAsignacion);
    }
    
}
