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
import sce.principal.RegistroEntity;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = NotasEstudianteEntity.tableName)
public class NotasEstudianteEntity implements Serializable, RegistroEntity {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "notas_estudiante";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long estudiante_id, curso_id, nota_distribucion_notas_id;
    private Float nota_obtenida;

    public Long getId() {
        return id;
    }

    public Long getEstudiante_id() {
        return estudiante_id;
    }

    public Long getCurso_id() {
        return curso_id;
    }

    public Long getNota_distribucion_notas_id() {
        return nota_distribucion_notas_id;
    }

    public Float getNota_obtenida() {
        return nota_obtenida;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstudiante_id(Long estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public void setCurso_id(Long curso_id) {
        this.curso_id = curso_id;
    }

    public void setNota_distribucion_notas_id(Long nota_distribucion_notas_id) {
        this.nota_distribucion_notas_id = nota_distribucion_notas_id;
    }

    public void setNota_obtenida(Float nota_obtenida) {
        this.nota_obtenida = nota_obtenida;
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
        if (!(object instanceof NotasEstudianteEntity)) {
            return false;
        }
        NotasEstudianteEntity other = (NotasEstudianteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.NotasEstudianteEntity[ id=" + id + " ]";
    }

    @Override
    public boolean yaExiste() {
        return id != null;
    }
    
}
