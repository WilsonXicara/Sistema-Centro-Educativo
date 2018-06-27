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
@Entity(name = PensumEntity.tableName)
@Table(name = PensumEntity.tableName)
public class PensumEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "pensum";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo, descripcion;

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public void setId(Long id) { this.id = id; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PensumEntity)) {
            return false;
        }
        PensumEntity other = (PensumEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.PensumEntity{" + "id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof PensumEntity) {
            PensumEntity aux = (PensumEntity)object;
            this.id = aux.id;
            this.codigo = aux.codigo;
            this.descripcion = aux.descripcion;
        }
    }
}