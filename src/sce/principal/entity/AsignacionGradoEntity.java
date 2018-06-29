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
import javax.persistence.NamedQuery;
import javax.persistence.Table;;

/**
 *
 * @author Usuario
 */
@Entity(name = AsignacionGradoEntity.tableName)
@NamedQuery(name="AsignacionGrado.buscarPorCarrera", query="SELECT ag FROM "+AsignacionGradoEntity.tableName+" AS ag WHERE ag.asignacion_carrera_id = :idAsigCarrera")
@Table(name = AsignacionGradoEntity.tableName)
public class AsignacionGradoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_grado";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_carrera_id, grado_id;

    public Long getId() { return id; }
    public Long getAsignacion_carrera_id() { return asignacion_carrera_id; }
    public Long getGrado_id() { return grado_id; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_carrera_id(Long asignacion_carrera_id) { this.asignacion_carrera_id = asignacion_carrera_id; }
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
        if (!(object instanceof AsignacionGradoEntity)) {
            return false;
        }
        AsignacionGradoEntity other = (AsignacionGradoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.AsignacionGradoEntity{" + "id=" + id + ", asignacion_carrera_id=" + asignacion_carrera_id + ", grado_id=" + grado_id + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionGradoEntity) {
            AsignacionGradoEntity aux = (AsignacionGradoEntity)object;
            this.id = aux.id;
            this.asignacion_carrera_id = aux.asignacion_carrera_id;
            this.grado_id = aux.grado_id;
        }
    }
}