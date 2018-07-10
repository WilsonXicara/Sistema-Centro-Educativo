/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import motor.Motor;
import java.net.URL;
import java.net.URLClassLoader;
import javax.persistence.EntityManagerFactory;
import sce.principal.GestorConexion;

/**
 *
 * @author Usuario
 */
public class Principal2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Motor.main(args);
        Carrera.main(args);
        GestorConexion.definirNombrePersistenceUnit("Sistema-Centro-EducativoPU");
        EntityManagerFactory emf = GestorConexion.crearEntityManagerFactory();
        System.out.println("emf: "+emf);
        Persona.listarAtributos(emf, "estudiante");
        System.out.println("emf: "+emf);
        System.out.println("ClassPath:");
            for (URL url : ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs()) {
                System.out.println("\t" + url.getFile());
            }*/
        //System.out.println("Estudiante.atributos = "+AtributoAdicionalEditor.obtenerListaAtributos(emf, "estudiante"));
    }
    
}
