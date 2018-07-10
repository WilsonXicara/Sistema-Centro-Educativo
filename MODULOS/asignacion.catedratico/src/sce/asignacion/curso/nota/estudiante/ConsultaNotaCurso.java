/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.estudiante;

import java.util.ArrayList;
import sce.asignacion.curso.nota.orm.ActividadEntity;

/**
 * Clase que obtendrá las
 * @Sugerencia: Renombrar la clase a EstudianteConsultaNotasCurso
 * @author Usuario
 */
public class ConsultaNotaCurso {
    private final Float notaEsperadaCurso;
    private final ArrayList<ConsultaNotaActividadEstudiante> listaNotaActividades;
    
    public ConsultaNotaCurso(Float notaEsperadaCurso) {
        this.notaEsperadaCurso = notaEsperadaCurso;
        this.listaNotaActividades = new ArrayList<>();
    }

    public Float getNotaEsperadaCurso() { return notaEsperadaCurso; }
    public ConsultaNotaActividadEstudiante getNotaActividad(int index) {
        if (index<0 || index>=listaNotaActividades.size()) {
            return null;
        }
        return listaNotaActividades.get(index);
    }
    public void addNotaActividad(ConsultaNotaActividadEstudiante notaActividad) { this.listaNotaActividades.add(notaActividad); }
    public boolean setNotaObtenida(int index, Float notaObtenida) {
        if (index<0 || index>=listaNotaActividades.size()) {
            return false;
        }
        listaNotaActividades.get(index).setNotaObtenida(notaObtenida);
        return true;
    }
    /**
     * Registro no editable para mostrar la Nota de una Actividad de un Estudiante.
     */
    public static class ConsultaNotaActividadEstudiante {
        private final String grupoActividad, nombreActividad;
        private final Float notaEsperada;
        private Float notaObtenida=0f;
        
        public ConsultaNotaActividadEstudiante(ActividadEntity actividad) {
            this.grupoActividad = actividad.getGrupo_actividad();
            this.nombreActividad = actividad.getActividad();
            this.notaEsperada = actividad.getEsperado();
        }

        public String getGrupoActividad() { return grupoActividad; }
        public String getNombreActividad() { return nombreActividad; }
        public Float getNotaEsperada() { return notaEsperada; }
        public Float getNotaObtenida() { return notaObtenida; }
        public void setNotaObtenida(Float notaObtenida) { this.notaObtenida = notaObtenida; }
    }
}