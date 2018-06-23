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
import sce.principal.Persona;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = CatedraticoEntity.tableName)
public class CatedraticoEntity implements Serializable, Persona {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "catedratico";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres, apellidos, direccion;
    private Long asignacion_id;

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public Long getAsignacion_id() {
        return asignacion_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAsignacion_id(Long asignacion_id) {
        this.asignacion_id = asignacion_id;
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
        if (!(object instanceof CatedraticoEntity)) {
            return false;
        }
        CatedraticoEntity other = (CatedraticoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "orm.CatedraticoEntity[ id=" + id + " ]";
    }
    
}
