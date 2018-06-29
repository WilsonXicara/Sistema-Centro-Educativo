/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.text.Normalizer;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sce.persona.AtributoAdicionalEditor;
import sce.persona.educativo.PersonaEstudianteCreador;
import sce.principal.entity.AsignacionCarreraEntity;
import sce.principal.entity.CarreraEntity;
import sce.principal.entity.CicloEscolarEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.DetallePensumEntity;
import sce.principal.entity.PensumEntity;
import sce.principal.ormjpa.AsignacionCarreraJpaController;
import sce.principal.ormjpa.CarreraJpaController;
import sce.principal.ormjpa.CicloEscolarJpaController;
import sce.principal.ormjpa.CursoJpaController;
import sce.principal.ormjpa.DetallePensumJpaController;
import sce.principal.ormjpa.PensumJpaController;

/**
 *
 * @author Usuario
 */
public class Principal {
    public static final int CANTIDAD_ESTUDIANTES = 5;
    //private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        creacion_de_estudiante();
    }
    public static void registrar_asignacion_carrera(EntityManagerFactory emf) {
        CicloEscolarJpaController controllerCiclo = new CicloEscolarJpaController(emf);
        System.out.println("ciclo 2018 = "+controllerCiclo.buscarCicloNoListo());
        CicloEscolarEntity ciclo2018 = new CicloEscolarEntity();
        ciclo2018.setCiclo_escolar("2018");
        new CicloEscolarJpaController(emf).create(ciclo2018);
        System.out.println("ciclo registrado = "+ciclo2018);
        String[] nombreCursos = new String[]{"Mate","Fisica","Quimica","Deportes"};
        CursoEntity[] cursos = new CursoEntity[nombreCursos.length];
        CursoJpaController controller = new CursoJpaController(emf);
        for(int i=0; i<nombreCursos.length; i++) {
            cursos[i] = new CursoEntity();
            cursos[i].setCurso(nombreCursos[i]);
            controller.create(cursos[i]);
            System.out.println("curso registrado = "+cursos[i]);
        }
        PensumEntity pensum = new PensumEntity();
        pensum.setDescripcion("pensum de primaria");
        new PensumJpaController(emf).create(pensum);
        System.out.println("pensum registrado = "+pensum);
        DetallePensumJpaController controllerPensum = new DetallePensumJpaController(emf);
        DetallePensumEntity[] detallePensum = new DetallePensumEntity[nombreCursos.length];
        for(int i=0; i<nombreCursos.length; i++) {
            detallePensum[i] = new DetallePensumEntity();
            detallePensum[i].setPensum_id(pensum.getId());
            detallePensum[i].setCurso_id(cursos[i].getId());
            controllerPensum.create(detallePensum[i]);
            System.out.println("pensum registrado = "+detallePensum[i]);
        }
        CarreraEntity carrera1 = new CarreraEntity();
        carrera1.setNombre("Primaria");
        carrera1.setPensum_id(pensum.getId());
        CarreraEntity carrera2 = new CarreraEntity();
        carrera2.setNombre("Básico");
        carrera2.setPensum_id(pensum.getId());
        CarreraJpaController controllerCarrera = new CarreraJpaController(emf);
        controllerCarrera.create(carrera1);
        System.out.println("carrera registrada = "+carrera1);
        controllerCarrera.create(carrera2);
        System.out.println("carrera registrada = "+carrera2);
        // Asignación carrera
        AsignacionCarreraJpaController controllerAsigC = new AsignacionCarreraJpaController(emf);
        AsignacionCarreraEntity asigC1 = new AsignacionCarreraEntity();
        asigC1.setCiclo_escolar_id(ciclo2018.getId());
        asigC1.setCarrera_id(carrera1.getId());
        AsignacionCarreraEntity asigC2 = new AsignacionCarreraEntity();
        asigC2.setCiclo_escolar_id(ciclo2018.getId());
        asigC2.setCarrera_id(carrera2.getId());
        controllerAsigC.create(asigC1);
        System.out.println("carrera asignada = "+asigC1);
        controllerAsigC.create(asigC2);
        System.out.println("carrera asignada = "+asigC2);
    }
    public static void creacion_de_estudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        AtributoAdicionalEditor atributos = new AtributoAdicionalEditor(emf, "estudiante");
        atributos.agregarAtributo("edad");
        atributos.agregarAtributo("fecha de nacimiento");
        atributos.guardarAtributos(emf);
        PersonaEstudianteCreador estudiante = new PersonaEstudianteCreador(emf, 1l);
        ArrayList<String> atributosExtra = estudiante.getAtributosAdicionales();
        for (String atributo : atributosExtra) {
            estudiante.setValorAdicional(atributo, atributo);
        }
        estudiante.getInformacionBasica().setCui("7080314796102");
        System.out.println("valores = "+estudiante.getValoresAdicionales());
        System.out.println("entity = "+estudiante.getInformacionBasica());
        estudiante.crearPersona();
    }
    public static void normalizacion_cadenas() {
        String texto = "Número de, teléfonoñ 192";
        System.out.println("original = '"+texto+"'");
        String sinEspacio = texto.replaceAll("\\s", "_");
        System.out.println("sin espacios = '"+sinEspacio+"'");
        String minuscula = sinEspacio.toLowerCase();
        System.out.println("en minúscula = '"+minuscula+"'");
        String sinExtranio = Normalizer.normalize(minuscula,Normalizer.Form.NFKD).replaceAll("[^a-zA-Z0-9_]", "");
        System.out.println("sin extraños = '"+sinExtranio+"'");
    }
    public static void crear_atributos_adicionales() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        AtributoAdicionalEditor creador = new AtributoAdicionalEditor(emf, "encargado");
        System.out.println("telefono: "+creador.agregarAtributo("telefono"));
        System.out.println("fecha_nacimiento: "+creador.agregarAtributo("fecha_nacimiento"));
        System.out.println("edad: "+creador.agregarAtributo("edad"));
        System.out.println("telefono: "+creador.agregarAtributo("telefono"));
        System.out.println("etnia: "+creador.agregarAtributo("etnia"));
        System.out.println("sexo: "+creador.agregarAtributo("sexo"));
        //System.out.println("-telefono: "+creador.eliminarAtributo("telefono"));
        creador.guardarAtributos(emf);
        System.out.println("LISTAS");
        System.out.println("estudiante = "+AtributoAdicionalEditor.obtenerListaAtributos(emf, "estudiante"));
        System.out.println("encargado = "+AtributoAdicionalEditor.obtenerListaAtributos(emf, "encargado"));
        System.out.println("catedratico = "+AtributoAdicionalEditor.obtenerListaAtributos(emf, "catedratico"));
    }
}