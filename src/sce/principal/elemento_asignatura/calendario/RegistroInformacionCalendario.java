/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.calendario;

/**
 *
 * @author juan_
 */
public class RegistroInformacionCalendario {
    private Long idCalendario, asignacion_carrera_id;
    private int mes, dia_inicio, dia_fin;
    private String actividad;

    public Long getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(Long idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Long getAsignacion_carrera_id() {
        return asignacion_carrera_id;
    }

    public void setAsignacion_carrera_id(Long asignacion_carrera_id) {
        this.asignacion_carrera_id = asignacion_carrera_id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia_inicio() {
        return dia_inicio;
    }

    public void setDia_inicio(int dia_inicio) {
        this.dia_inicio = dia_inicio;
    }

    public int getDia_fin() {
        return dia_fin;
    }

    public void setDia_fin(int dia_fin) {
        this.dia_fin = dia_fin;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
   
    
}
