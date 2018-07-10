/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/*import sce.persona.catedratico.InformacionCatedraticoBuilder;
import sce.persona.catedratico.InformacionCatedratico;
import sce.persona.estudiante.InformacionEstudianteBuilder;
import sce.persona.estudiante.InformacionEstudiante;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Persistence;
import sce.persona.builder.AbstractInformacionPersona;
import sce.persona.builder.InformacionPersonaBuilder;
import sce.persona.builder.InformacionPersonaDirector;*/

/**
 *
 * @author Usuario
 */
public class DirectorPrincipal {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        crear_registro_persona();
    }
    public void concatenar_array() {
        ArrayList<String> array1 = new ArrayList<>();
        array1.add("A1");
        array1.add("A2");
        array1.add("A3");
        List<String> array2 = Arrays.asList(new String[]{"B1","B2","B3"});
        ArrayList<String> array3 = (ArrayList<String>)array1.clone();
        System.out.println("concatenar?: "+array3.addAll(0,array2));
        System.out.println("array1 = "+array1);
        System.out.println("array3 = "+array3);
    }
    public static void crear_registro_persona() {
        InformacionPersonaBuilder builder;
        InformacionPersonaDirector administrador = new InformacionPersonaDirector();
        // Construyendo un Estudiante
        builder = new InformacionEstudianteBuilder(Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU"));
        administrador.setInformacionPersonBuilder(builder);
        administrador.setIdPersona(1l);
        administrador.construirInformacionPersona();
        InformacionEstudiante estudiante = (InformacionEstudiante)administrador.getInformacionPersona();
        AbstractInformacionPersona abstractP = administrador.getInformacionPersona();
        estudiante.setAtributosAdicionales(new ArrayList<>());
        System.out.println("hola=mundo -> "+estudiante.getValorAtributo("para_super"));
        System.out.println("est = "+estudiante.getClass().getName());
        System.out.println("abs = "+abstractP.getClass().getName());
        // Construyendo un Catedrático
        builder = new InformacionCatedraticoBuilder(null);
        administrador.setInformacionPersonBuilder(builder);
        administrador.setIdPersona(2l);
        administrador.construirInformacionPersona();
        InformacionCatedratico catedratico = (InformacionCatedratico)administrador.getInformacionPersona();
    }*/
}