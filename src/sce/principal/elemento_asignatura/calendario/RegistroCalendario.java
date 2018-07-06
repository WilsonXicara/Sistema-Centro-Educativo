/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.calendario;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.ElementoAsignaturaEntity;
import sce.principal.elemento_asignatura.calendario.orm.CalendarioCicloEscolarEntity;
import sce.principal.elemento_asignatura.calendario.orm.CalendarioCicloEscolarJpaController;

/**
 *
 * @author juan_
 */
public class RegistroCalendario extends AbstractElementoAsignatura {
    private final RegistroInformacionCalendario registroCalendario;
    
    public RegistroCalendario() {
        this.registroCalendario = new RegistroInformacionCalendario();
        this.registroCalendario.setIdCalendario(idElementoAsignatura = null);
    }
    public RegistroCalendario(Long idCalendario, EntityManagerFactory emf) {
        CalendarioCicloEscolarEntity auxCalendario = new CalendarioCicloEscolarJpaController(emf).findCalendarioCicloEscolarEntity(idCalendario);
        this.registroCalendario = new RegistroInformacionCalendario();
        if (auxCalendario != null) {
            this.registroCalendario.setIdCalendario(this.idElementoAsignatura = idCalendario);
            this.registroCalendario.setAsignacion_carrera_id(auxCalendario.getAsignacion_carrera_id());
            this.registroCalendario.setMes(auxCalendario.getMes());
            this.registroCalendario.setDia_inicio(auxCalendario.getDia_inicio());
            this.registroCalendario.setDia_fin(auxCalendario.getDia_fin());
            this.registroCalendario.setActividad(auxCalendario.getActividad());
        } else {
            this.registroCalendario.setIdCalendario(this.idElementoAsignatura = null);
        }
    }
    
    public Long getAsignacionCarreraID(){
        return this.registroCalendario.getAsignacion_carrera_id();
    }
    public int getMes(){
        return this.registroCalendario.getMes();
    }
    public int getDiaInicio(){
        return this.registroCalendario.getDia_inicio();
    }
    public int getDiaFin(){
        return this.registroCalendario.getDia_fin();
    }
    public String getActividad(){
        return this.registroCalendario.getActividad();
    }
    
    public void setAsignacionCarreraID(Long idAsignacionCarrera){
        this.registroCalendario.setAsignacion_carrera_id(idAsignacionCarrera);
    }
    public void setMes(int mes){
        this.registroCalendario.setMes(mes);
    }
    public void setDiaInicio(int diaInicio){
        this.registroCalendario.setDia_inicio(diaInicio);
    }
    public void setDiaFin(int diaFin){
        this.registroCalendario.setDia_fin(diaFin);
    }
    public void setActividad(String actividad){
        this.registroCalendario.setActividad(actividad);
    }
 
    
    @Override
    public ElementoAsignaturaEntity getElementoAsignaturaEntity() {
        CalendarioCicloEscolarEntity calendario = new CalendarioCicloEscolarEntity(registroCalendario.getAsignacion_carrera_id(), 
                registroCalendario.getMes(), registroCalendario.getDia_inicio(), registroCalendario.getDia_fin(), registroCalendario.getActividad());
        calendario.setId(idElementoAsignatura);
        return calendario;
    }
    
}
