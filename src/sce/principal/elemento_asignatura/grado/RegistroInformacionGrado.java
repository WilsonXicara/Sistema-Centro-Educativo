/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.grado;

/**
 *
 * @author juan_
 */
public class RegistroInformacionGrado {
    private Long idGrado, prerrequisito_id, asignacion_id;
    private String grado, seccion;
    private int capacidad;

    public Long getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Long idGrado) {
        this.idGrado = idGrado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Long getPrerrequisito_id() {
        return prerrequisito_id;
    }

    public void setPrerrequisito_id(Long prerrequisito_id) {
        this.prerrequisito_id = prerrequisito_id;
    }

    public Long getAsignacion_id() {
        return asignacion_id;
    }

    public void setAsignacion_id(Long asignacion_id) {
        this.asignacion_id = asignacion_id;
    }
}
