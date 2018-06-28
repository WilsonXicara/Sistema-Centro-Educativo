/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity(name = AsignacionCarreraEntity.tableName)
@Table(name = AsignacionCarreraEntity.tableName)
public class AsignacionCarreraEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_carrera";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ciclo_escolar_id, carrera_id;
    private Boolean anulado = false;
    private String razon_anulacion;

    public Long getId() { return id; }
    public Long getCiclo_escolar_id() { return ciclo_escolar_id; }
    public Long getCarrera_id() { return carrera_id; }
    public Boolean getAnulado() { return anulado; }
    public String getRazon_anulacion() { return razon_anulacion; }
    public void setId(Long id) { this.id = id; }
    public void setCiclo_escolar_id(Long ciclo_escolar_id) { this.ciclo_escolar_id = ciclo_escolar_id; }
    public void setCarrera_id(Long carrera_id) { this.carrera_id = carrera_id; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }
    public void setRazon_anulacion(String razon_anulacion) { this.razon_anulacion = razon_anulacion; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionCarreraEntity)) {
            return false;
        }
        AsignacionCarreraEntity other = (AsignacionCarreraEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.AsignacionCarreraEntity{" + "id=" + id + ", ciclo_escolar_id=" + ciclo_escolar_id + ", carrera_id=" + carrera_id + ", anulado=" + anulado + ", razon_anulacion=" + razon_anulacion + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionCarreraEntity) {
            AsignacionCarreraEntity aux = (AsignacionCarreraEntity)object;
            this.id = aux.id;
            this.ciclo_escolar_id = aux.ciclo_escolar_id;
            this.carrera_id = aux.carrera_id;
            this.anulado = aux.anulado;
            this.razon_anulacion = aux.razon_anulacion;
        }
    }
}