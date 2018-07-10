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
@Entity(name = AtributosAdicionalesEntity.tableName)
@NamedQueries({
    @NamedQuery(
            name="AtributoAdicional.buscarAtributosParaTabla",
            query="SELECT aa FROM "+AtributosAdicionalesEntity.tableName+" AS aa WHERE aa.tabla_extendida_id = :idTablaExtendida ORDER BY aa.nombre_atributo")
})
@Table(name = AtributosAdicionalesEntity.tableName)
public class AtributosAdicionalesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "atributos_adicionales";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tabla_extendida_id;
    private String nombre_atributo;

    public Long getId() { return id; }
    public Long getTabla_extendida_id() { return tabla_extendida_id; }
    public String getNombre_atributo() { return nombre_atributo; }
    public void setId(Long id) { this.id = id; }
    public void setTabla_extendida_id(Long tabla_extendida_id) { this.tabla_extendida_id = tabla_extendida_id; }
    public void setNombre_atributo(String nombre_atributo) { this.nombre_atributo = nombre_atributo; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof String) {
            String aux = (String)object;
            return nombre_atributo.equals(aux);
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtributosAdicionalesEntity)) {
            return false;
        }
        AtributosAdicionalesEntity other = (AtributosAdicionalesEntity) object;
        return nombre_atributo.equals(other.nombre_atributo);
    }
    @Override
    public String toString() {
        return "sce.principal.entity.AtributosAdicionalesEntity{" + "id=" + id + ", tabla_extendida_id=" + tabla_extendida_id + ", nombre_atributo=" + nombre_atributo + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AtributosAdicionalesEntity) {
            AtributosAdicionalesEntity aux = (AtributosAdicionalesEntity)object;
            this.id = aux.id;
            this.tabla_extendida_id = aux.tabla_extendida_id;
            this.nombre_atributo = aux.nombre_atributo;
        }
    }
}