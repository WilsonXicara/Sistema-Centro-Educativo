/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

//import sce.nivel.basico.NivelBasico;
import sce.nivel.NivelEducativo;
import sce.principal.Asignacion;
import sce.principal.AsignacionBuilder;
import sce.principal.CentroEducativo;
import sce.principal.ElementoEducativo;
import sce.principal.Persona;

/**
 *
 * @author Usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NivelEducativo director = new NivelEducativo();
        Asignacion asig = director.crearAsignacion(CentroEducativo.TIPO_ASIGNACION_ESTUDIANTE);
        ElementoEducativo ciclo = director.crearElementoEducativo(CentroEducativo.TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR);
        ElementoEducativo grado = director.crearElementoEducativo(CentroEducativo.TIPO_ELEMENTO_EDUCATIVO_GRADO);
        AsignacionBuilder builder = director.crearAsignacionBuilder();
        Persona est = director.crearPersona(CentroEducativo.TIPO_PERSONA_ESTUDIANTE);
        
        builder.setAsignacion(asig, CentroEducativo.TIPO_ASIGNACION_ESTUDIANTE);
        builder.setElementoPrincipal(ciclo);
        builder.setElementoSecundario(grado);
        builder.setElementoTerciario(est);
        
        asig = builder.getAsignacion();
        asig.crearAsignacion();
    }
}
