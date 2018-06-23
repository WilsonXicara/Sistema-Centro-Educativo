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
@Table(name = AsignacionCatedraticoEntity.tableName)
public class AsignacionCatedraticoEntity implements Serializable, Asignacion {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_catedratico";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ciclo_escolar_id, catedratico_id;

    public Long getId() {
        return id;
    }

    public Long getCiclo_escolar_id() {
        return ciclo_escolar_id;
    }

    public Long getCatedratico_id() {
        return catedratico_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCiclo_escolar_id(Long ciclo_escolar_id) {
        this.ciclo_escolar_id = ciclo_escolar_id;
    }

    public void setCatedratico_id(Long catedratico_id) {
        this.catedratico_id = catedratico_id;
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
        return "sce.principal.entity.Asignacion_Catedratico[ id=" + id + " ]";
    }
    
}
