/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico.orm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity(name = AsignacionCatedraticoEntity.tableName)
@NamedQuery(name ="AsignacionCatedratico.buscarPorCarrera", query ="SELECT ac FROM "+AsignacionCatedraticoEntity.tableName+" AS ac WHERE ac.asignacion_carrera_id = :idCarrera")
@Table(name = AsignacionCatedraticoEntity.tableName)
public class AsignacionCatedraticoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_catedratico";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_carrera_id, catedratico_id;
    private Boolean anulado = false;
    private String razon_anulacion;
    private Long asignacion_anterior_id=0l;

    public Long getId() { return id; }
    public Long getAsignacion_carrera_id() { return asignacion_carrera_id; }
    public Long getCatedratico_id() { return catedratico_id; }
    public Boolean getAnulado() { return anulado; }
    public String getRazon_anulacion() { return razon_anulacion; }
    public Long getAsignacion_anterior_id() { return asignacion_anterior_id; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_carrera_id(Long asignacion_carrera_id) { this.asignacion_carrera_id = asignacion_carrera_id; }
    public void setCatedratico_id(Long catedratico_id) { this.catedratico_id = catedratico_id; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }
    public void setRazon_anulacion(String razon_anulacion) { this.razon_anulacion = razon_anulacion; }
    public void setAsignacion_anterior_id(Long asignacion_anterior_id) { this.asignacion_anterior_id = asignacion_anterior_id; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionCatedraticoEntity)) {
            return false;
        }
        AsignacionCatedraticoEntity other = (AsignacionCatedraticoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.AsignacionCatedraticoEntity{" + "id=" + id + ", asignacion_carrera_id=" + asignacion_carrera_id + ", catedratico_id=" + catedratico_id + ", anulado=" + anulado + ", razon_anulacion=" + razon_anulacion + ", asignacion_anterior_id=" + asignacion_anterior_id + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionCatedraticoEntity) {
            AsignacionCatedraticoEntity aux = (AsignacionCatedraticoEntity)object;
            this.id = aux.id;
            this.asignacion_carrera_id = aux.asignacion_carrera_id;
            this.catedratico_id = aux.catedratico_id;
            this.anulado = aux.anulado;
            this.razon_anulacion = aux.razon_anulacion;
            this.asignacion_anterior_id = aux.asignacion_anterior_id;
        }
    }
}