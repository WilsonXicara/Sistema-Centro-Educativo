/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.educativo;

import sce.persona.PersonaEntity;
import sce.persona.PersonaEntityAbstractFactory;
import javax.persistence.EntityManagerFactory;
import sce.principal.entity.CatedraticoEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.ormjpa.CatedraticoJpaController;
import sce.principal.ormjpa.EstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class PersonaEducativoEntityFactory implements PersonaEntityAbstractFactory {
    private final EntityManagerFactory emf;
    
    public PersonaEducativoEntityFactory(EntityManagerFactory emf) { this.emf = emf; }

    @Override
    public PersonaEntity obtenerPersonaEntity(int tipo) {
        switch(tipo) {
            case ESTUDIANTE:    return new EstudianteEntity();
            case CATEDRATICO:   return new CatedraticoEntity();
        }
        return null;
    }

    @Override
    public PersonaEntity obtenerPersonaEntity(int tipo, Long id) {
        switch(tipo) {
            case ESTUDIANTE:
                EstudianteEntity estudiante = new EstudianteJpaController(emf).findEstudianteEntity(id);
                if (estudiante != null) {
                    return estudiante;
                }
                return new EstudianteEntity();
            case CATEDRATICO:
                CatedraticoEntity catedratico = new CatedraticoJpaController(emf).findCatedraticoEntity(id);
                if (catedratico != null) {
                    return catedratico;
                }
                return new CatedraticoEntity();
        }
        return null;
    }
}