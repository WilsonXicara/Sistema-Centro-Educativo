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
import sce.principal.ElementoEducativo;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = CicloEscolarEntity.tableName)
public class CicloEscolarEntity implements Serializable, ElementoEducativo {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "ciclo_escolar";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ciclo_escolar;
    private Boolean listo=false, cerrado=false;

    public Long getId() {
        return id;
    }

    public String getCiclo_escolar() {
        return ciclo_escolar;
    }

    public Boolean isListo() {
        return listo;
    }

    public Boolean isCerrado() {
        return cerrado;
    }

    @Override
    public boolean yaExiste() {
        return id != null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCiclo_escolar(String ciclo_escolar) {
        this.ciclo_escolar = ciclo_escolar;
    }

    public void setListo(Boolean listo) {
        this.listo = listo;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
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
        if (!(object instanceof CicloEscolarEntity)) {
            return false;
        }
        CicloEscolarEntity other = (CicloEscolarEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "orm.CicloEscolarEntity[ id=" + id + ", año="+ciclo_escolar+" ]";
    }
    
}
