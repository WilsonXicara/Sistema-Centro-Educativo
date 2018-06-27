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
@Entity(name = AtributosAdicionalesEntity.tableName)
@Table(name = AtributosAdicionalesEntity.tableName)
public class AtributosAdicionalesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "atributos_adicionales";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nombre_tabla, nombre_atributo;

    public Long getId() { return id; }
    public String getNombre_tabla() { return nombre_tabla; }
    public String getNombre_atributo() { return nombre_atributo; }
    public void setId(Long id) { this.id = id; }
    public void setNombre_tabla(String nombre_tabla) { this.nombre_tabla = nombre_tabla; }
    public void setNombre_atributo(String nombre_atributo) { this.nombre_atributo = nombre_atributo; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtributosAdicionalesEntity)) {
            return false;
        }
        AtributosAdicionalesEntity other = (AtributosAdicionalesEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.AtributosAdicionalesEntity{" + "id=" + id + ", nombre_tabla=" + nombre_tabla + ", nombre_atributo=" + nombre_atributo + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AtributosAdicionalesEntity) {
            AtributosAdicionalesEntity aux = (AtributosAdicionalesEntity)object;
            this.id = aux.id;
            this.nombre_tabla = aux.nombre_atributo;
            this.nombre_atributo = aux.nombre_atributo;
        }
    }
}