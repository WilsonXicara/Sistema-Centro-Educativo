/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import sce.excepciones.ExcepcionEntityAnulado;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.excepciones.PreexistingEntityException;
import sce.persona.builder.InformacionPersonaBuilder;
import sce.persona.builder.InformacionPersonaDirector;
import sce.persona.catedratico.InformacionCatedratico;
import sce.persona.catedratico.InformacionCatedraticoBuilder;
import sce.persona.catedratico.InformacionCatedraticoSave;
import sce.persona.estudiante.InformacionEstudiante;
import sce.persona.estudiante.InformacionEstudianteBuilder;
import sce.persona.estudiante.InformacionEstudianteSave;
import sce.principal.GestorConexion;
import sce.principal.elemento_asignatura.ciclo.RegistroCiclo;

/**
 *
 * @author Usuario
 */
public class Principal1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorConexion.definirNombrePersistenceUnit("Sistema-Centro-EducativoPU");
        EntityManagerFactory emf = GestorConexion.crearEntityManagerFactory();
        InformacionPersonaDirector directorP = new InformacionPersonaDirector();
        // INFORMACIÓN DE ESTUDIANTES
        InformacionPersonaBuilder builder = new InformacionEstudianteBuilder(emf);
        directorP.setInformacionPersonBuilder(builder);
        directorP.setIdPersona(1l);
        directorP.construirInformacionPersona();
        InformacionEstudiante estudiante = (InformacionEstudiante) directorP.getInformacionPersona();
        System.out.println("********** ESTUDIANTE:");
        System.out.println("\\-- atributos: "+estudiante.getAtributosCompletos());
        System.out.println("    \\-> fecha='2018-01-01': "+estudiante.getValorAtributo("fecha"));
        System.out.println("    \\<- fecha='2018-01-01': "+estudiante.setValorAtributo("fecha", "2018-01-01"));
        InformacionEstudianteSave guardarEst = new InformacionEstudianteSave(emf);
        try {
            guardarEst.guardarInformacionPersona(estudiante);
        } catch (ExcepcionEntityAnulado | PreexistingEntityException | ExcepcionTipoNoSoportado ex) {
            Logger.getLogger(Principal1.class.getName()).log(Level.SEVERE, null, ex);
        }
        // INFORMACIÓN DE CATEDRÁTICOS
        builder = new InformacionCatedraticoBuilder(emf);
        directorP.setInformacionPersonBuilder(builder);
        directorP.setIdPersona(1l);
        directorP.construirInformacionPersona();
        InformacionCatedratico catedratico = (InformacionCatedratico) directorP.getInformacionPersona();
        System.out.println("********** CATEDRATICO:");
        System.out.println("\\-- atributos: "+catedratico.getAtributosCompletos());
        System.out.println("    \\-> 'dpi': "+catedratico.getValorAtributo("dpi"));
        System.out.println("    \\<- 'dpi': "+catedratico.setValorAtributo("dpi", "1234567890123"));
        InformacionCatedraticoSave guardarCat = new InformacionCatedraticoSave(emf);
        try {
            guardarCat.guardarInformacionPersona(catedratico);
        } catch (PreexistingEntityException | ExcepcionTipoNoSoportado ex) {
            Logger.getLogger(Principal1.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ClassPath:");
            for (URL url : ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs()) {
                System.out.println("\t" + url.getFile());
            }
    }
    public static void otro_metodo() {
        GestorConexion.definirNombrePersistenceUnit("Sistema-Centro-EducativoPU");
        EntityManagerFactory emf = GestorConexion.crearEntityManagerFactory();
        System.out.println("->emf: "+emf);
        Set<EntityType<?>> entidades =  emf.getMetamodel().getEntities();
        for (EntityType<?> entidad : entidades) {
            System.out.println("entidad: "+entidad);
        }
        System.out.println("->emf: "+emf.getMetamodel().getEntities());
        RegistroCiclo ciclo = new RegistroCiclo(1l, emf);
        System.out.println("->ciclo: "+ciclo.toString());
    }
}
