/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.estudiante;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ConsultaNotaCurso {
    private final Float notaEsperadaCurso;
    private final ArrayList<ConsultaNotaActividad> listaNotaActividades;
    
    public ConsultaNotaCurso(Float notaEsperadaCurso) {
        this.notaEsperadaCurso = notaEsperadaCurso;
        this.listaNotaActividades = new ArrayList<>();
    }

    public Float getNotaEsperadaCurso() { return notaEsperadaCurso; }
    public ConsultaNotaActividad getNotaActividad(int index) {
        if (index<0 || index>=listaNotaActividades.size()) {
            return null;
        }
        return listaNotaActividades.get(index);
    }
    public void addNotaActividad(ConsultaNotaActividad notaActividad) { this.listaNotaActividades.add(notaActividad); }
    public void setNotaObtenida(int index, Float notaObtenida) {
        if (index<0 || index>=listaNotaActividades.size()) {
            return;
        }
        listaNotaActividades.get(index).setNotaObtenida(notaObtenida);
    }
    
    public static class ConsultaNotaActividad {
        private String grupoActividad, nombreActividad;
        private Float notaEsperada, notaObtenida;
        
        public ConsultaNotaActividad() {}

        public String getGrupoActividad() { return grupoActividad; }
        public String getNombreActividad() { return nombreActividad; }
        public Float getNotaEsperada() { return notaEsperada; }
        public Float getNotaObtenida() { return notaObtenida; }        
        public void setGrupoActividad(String grupoActividad) { this.grupoActividad = grupoActividad; }
        public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }
        public void setNotaEsperada(Float notaEsperada) { this.notaEsperada = notaEsperada; }
        public void setNotaObtenida(Float notaObtenida) { this.notaObtenida = notaObtenida; }
    }
}