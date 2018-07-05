/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
public class EMF {
    private static EntityManagerFactory emf;
    public static EntityManagerFactory crearEntityManagerFactory(String persistenceUnitName) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        }
        return emf;
    }
}
