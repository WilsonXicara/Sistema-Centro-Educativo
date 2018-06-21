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
import sce.principal.Asignacion;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = AsignacionGradoEntity.tableName)
public class AsignacionGradoEntity implements Serializable, Asignacion {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_grado";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ciclo_escolar_id, grado_id;

    public Long getId() {
        return id;
    }

    public Long getCiclo_escolar_id() {
        return ciclo_escolar_id;
    }

    public Long getGrado_id() {
        return grado_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCiclo_escolar_id(Long ciclo_escolar_id) {
        this.ciclo_escolar_id = ciclo_escolar_id;
    }

    public void setGrado_id(Long grado_id) {
        this.grado_id = grado_id;
    }

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
        return "sce.principal.entity.Asignacion_Grado[ id=" + id + " ]";
    }

    @Override
    public boolean yaExiste() {
        return id != null;
    }
    
}
