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
@Entity(name = AsignacionCatedraticoGradosEntity.tableName)
@Table(name = AsignacionCatedraticoGradosEntity.tableName)
public class AsignacionCatedraticoGradosEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_catedratico_grados";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_catedratico_id, grado_id;

    public Long getId() { return id; }
    public Long getAsignacion_catedratico_id() { return asignacion_catedratico_id; }
    public Long getGrado_id() { return grado_id; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_catedratico_id(Long asignacion_catedratico_id) { this.asignacion_catedratico_id = asignacion_catedratico_id; }
    public void setGrado_id(Long grado_id) { this.grado_id = grado_id; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionCatedraticoGradosEntity)) {
            return false;
        }
        AsignacionCatedraticoGradosEntity other = (AsignacionCatedraticoGradosEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "AsignacionCatedraticoGradosEntity{" + "id=" + id + ", asignacion_catedratico_id=" + asignacion_catedratico_id + ", grado_id=" + grado_id + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionCatedraticoGradosEntity) {
            AsignacionCatedraticoGradosEntity aux = (AsignacionCatedraticoGradosEntity)object;
            this.id = aux.id;
            this.asignacion_catedratico_id = aux.asignacion_catedratico_id;
            this.grado_id = aux.grado_id;
        }
    }
}