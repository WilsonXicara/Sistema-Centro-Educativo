/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.ciclo;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarEntity;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarJpaController;

/**
 *
 * @author juan_
 */
public class ConsultorRegistroCiclo {
    public static Boolean existeCicloEscolar(Long idCicloEscolar, EntityManagerFactory emf){
        CicloEscolarEntity existenteCiclo = new CicloEscolarJpaController(emf).findCicloEscolarEntity(idCicloEscolar);
        return existenteCiclo != null;
    }
    
    public static Boolean esVigenteCicloEscolar(Long idCicloEscolar, EntityManagerFactory emf){
        if (existeCicloEscolar(idCicloEscolar, emf)){
            return true;
        }
        CicloEscolarEntity existenteCiclo = new CicloEscolarJpaController(emf).findCicloEscolarEntity(idCicloEscolar);
        return existenteCiclo.isListo() && existenteCiclo.isCerrado() != true;
    }
    
}
