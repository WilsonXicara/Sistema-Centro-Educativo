/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.estudiante;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.persona.estudiante.orm.EstudianteEntity;
import sce.persona.estudiante.orm.EstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class InformacionEstudianteBuscador {
    private static boolean validarConexion(EntityManagerFactory emf) {
        if (emf == null) {
            return false;
        }
        return emf.isOpen();
    }
    public static InformacionBasicaEstudiante buscarEstudiante(EntityManagerFactory emf, Long idEstudiante)
            throws ExcepcionParametrosIncompletos {
        if (idEstudiante == null) {
            throw new ExcepcionParametrosIncompletos("El ID del Estudiante no puede ser nulo");
        } if (!validarConexion(emf)) {
            throw new ExcepcionParametrosIncompletos("No se ha proporcionado una conexión válida o ya ha sido cerrada");
        }
        EstudianteJpaController controller = new EstudianteJpaController(emf);
        EstudianteEntity estudiante = controller.findEstudianteEntity(idEstudiante);
        return new InformacionBasicaEstudiante(estudiante);
    }
    public static ArrayList<InformacionBasicaEstudiante> buscarEstudiantesAnulados(EntityManagerFactory emf) {
        ArrayList<InformacionBasicaEstudiante> listaEstudiantes = new ArrayList<>();
        EstudianteJpaController controller = new EstudianteJpaController(emf);
        List<EstudianteEntity> encontrados = controller.buscarAnulados();
        for (EstudianteEntity encontrado : encontrados) {
            listaEstudiantes.add(new InformacionBasicaEstudiante(encontrado));
        }
        return listaEstudiantes;
    }
    public static ArrayList<InformacionBasicaEstudiante> buscarEstudiantesSinAsignacion(EntityManagerFactory emf) {
        ArrayList<InformacionBasicaEstudiante> listaEstudiantes = new ArrayList<>();
        EstudianteJpaController controller = new EstudianteJpaController(emf);
        List<EstudianteEntity> encontrados = controller.buscarNoAsignados();
        for (EstudianteEntity encontrado : encontrados) {
            listaEstudiantes.add(new InformacionBasicaEstudiante(encontrado));
        }
        return listaEstudiantes;
    }
}