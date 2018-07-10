/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.carrera;

/**
 *
 * @author juan_
 */
public class RegistroInformacionCarrera {
    private Long idCarrera, pensum_id;
    private String nombre, descripcion;

    public RegistroInformacionCarrera() {
    }
    
    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Long getPensum_id() {
        return pensum_id;
    }

    public void setPensum_id(Long pensum_id) {
        this.pensum_id = pensum_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
