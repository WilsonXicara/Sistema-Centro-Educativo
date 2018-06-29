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
@Entity(name = AsignacionCatedraticoCursosEntity.tableName)
@Table(name = AsignacionCatedraticoCursosEntity.tableName)
public class AsignacionCatedraticoCursosEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_catedratico_cursos";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_catedratico_id, asignacion_curso_id, asignacion_catedratico_grados_id;

    public Long getId() { return id; }
    public Long getAsignacion_catedratico_id() { return asignacion_catedratico_id; }
    public Long getAsignacion_curso_id() { return asignacion_curso_id; }
    public Long getAsignacion_catedratico_grados_id() { return asignacion_catedratico_grados_id; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_catedratico_id(Long asignacion_catedratico_id) { this.asignacion_catedratico_id = asignacion_catedratico_id; }
    public void setAsignacion_curso_id(Long asignacion_curso_id) { this.asignacion_curso_id = asignacion_curso_id; }
    public void setAsignacion_catedratico_grados_id(Long asignacion_catedratico_grados_id) { this.asignacion_catedratico_grados_id = asignacion_catedratico_grados_id; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionCatedraticoCursosEntity)) {
            return false;
        }
        AsignacionCatedraticoCursosEntity other = (AsignacionCatedraticoCursosEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.AsignacionCatedraticoCursosEntity{" + "id=" + id + ", asignacion_catedratico_id=" + asignacion_catedratico_id + ", curso_id=" + asignacion_curso_id + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionCatedraticoCursosEntity) {
            AsignacionCatedraticoCursosEntity aux = (AsignacionCatedraticoCursosEntity)object;
            this.id = aux.id;
            this.asignacion_catedratico_id = aux.asignacion_catedratico_id;
            this.asignacion_curso_id = aux.asignacion_curso_id;
        }
    }
}