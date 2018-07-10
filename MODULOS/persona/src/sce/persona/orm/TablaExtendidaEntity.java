/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.orm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity(name = TablaExtendidaEntity.tableName)
@NamedQueries({
    @NamedQuery(name="TablaExtendida.buscarTablaExtendida", query="SELECT te FROM "+TablaExtendidaEntity.tableName+" AS te WHERE te.nombre_tabla = :nombreTabla")
})
@Table(name = TablaExtendidaEntity.tableName)
public class TablaExtendidaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "tabla_extendida";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre_tabla;

    public Long getId() { return id; }
    public String getNombre_tabla() { return nombre_tabla; }
    public void setId(Long id) { this.id = id; }
    public void setNombre_tabla(String nombre_tabla) { this.nombre_tabla = nombre_tabla; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof String && nombre_tabla.equals((String)object)) {
            return true;
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaExtendidaEntity)) {
            return false;
        }
        TablaExtendidaEntity other = (TablaExtendidaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.NombreTablaEntity{" + "id=" + id + ", nombre_tabla=" + nombre_tabla + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof TablaExtendidaEntity) {
            TablaExtendidaEntity aux = (TablaExtendidaEntity)object;
            this.id = aux.id;
            this.nombre_tabla = aux.nombre_tabla;
        }
    }
}