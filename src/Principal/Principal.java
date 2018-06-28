/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sce.asignacion.curso.AsignacionCursoCreador;
import sce.asignacion.curso.AsignacionCursoBuscador;
import sce.principal.entity.AsignacionCarreraEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.DistribucionNotasEntity;

/**
 *
 * @author Usuario
 */
public class Principal {
    public static final int CANTIDAD_ESTUDIANTES = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JsonObject toJson1 = new JsonObject();
        toJson1.addProperty("name", "Juan");
        toJson1.addProperty("age", 22);
        toJson1.addProperty("birthday", new Date().toString());
        JsonObject toJson2 = new JsonObject();
        toJson2.addProperty("nombres", "Wilson");
        toJson2.addProperty("apellidos", "Xicará");
        toJson1.add("información", toJson2);
        System.out.println("object = "+toJson1.toString());
        
        JsonParser parser = new JsonParser();
        JsonElement elementObject = parser.parse("{'name':'Juan','age':22,'birthday':'Wed Feb 26 20:39:53 CET 2014'}");
        
        /*elementObject.getAsJsonObject().
        JsonArray arreglo = elementObject.getAsJsonArray();
        Iterator<JsonElement> iterador = arreglo.iterator();
        while(iterador.hasNext()) {
            //iterador.next().
        }
        String name = elementObject.getAsJsonObject().get("name").getAsString();*/
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        
        //AsignacionCarrera
        CicloEscolarEntity cicloEscolar = new CicloEscolarEntity(); //Se crea un ciclo escolar
        CarreraEntity carrera = new CarreraEntity();    //Se crea una carrera
        AsignacionCarrera asignacionC = new AsignacionCarrera(emf);
        asignacionC.setCarrera(carrera);
        asignacionC.setCicloEscolar(cicloEscolar);
        asignacionC.crearAsignacion(); //Se crea una nueva asignación
        
        //AsignacionCatedratico
        CatedraticoEntity catedratico = new CatedraticoEntity();
        AsignacionCatedratico asignacionCat = new AsignacionCatedratico(emf);
        AsignacionCarreraEntity ace = new AsignacionCarreraEntity();
        asignacionCat.setAce(ace);
        asignacionCat.setCatedratico(catedratico);
        asignacionCat.crearAsignacion();
        
        //Asignacion de cursos a un catedrático
        AsignacionCatedraticoEntity asigCat = new AsignacionCatedraticoEntity();
        AsignacionCursoEntity asigCursos = new AsignacionCursoEntity();
        AsignacionCatedraticoCursos asignacionCursos = new AsignacionCatedraticoCursos(emf);
        asignacionCursos.setAsigCatE(asigCat);
        asignacionCursos.setAsigCursoE(asigCursos);
        asignacionCursos.crearAsignacion();
        
        //Asignacion de grados a una carrera 
        AsignacionCarreraEntity nuevaCarrera = new AsignacionCarreraEntity();
        GradoEntity grado = new GradoEntity();
        AsignacionGrado asigGrado = new AsignacionGrado(emf);
        asigGrado.setCarrera(nuevaCarrera);
        asigGrado.setGrado(grado);
        asigGrado.crearAsignacion();*/
    }
    public static void asignacion_estudiantes_a_grados(String cicloEscolarAcual) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        AsignacionCarreraEntity carrera = new AsignacionCarreraEntity();
        AsignacionCursoCreador asignacionCurso = new AsignacionCursoCreador(emf, carrera);
        //asignacionCurso.setGrado(new GradoEntity());
        CursoEntity curso = new CursoEntity();
        DistribucionNotasEntity distribucion = new DistribucionNotasEntity();
        asignacionCurso.addCurso(curso, distribucion);
        asignacionCurso.crearAsignacion();
        
        AsignacionCursoBuscador buscador = new AsignacionCursoBuscador(emf);
        buscador.obtenerCursoAsignados(0, 0l);
    }
}
