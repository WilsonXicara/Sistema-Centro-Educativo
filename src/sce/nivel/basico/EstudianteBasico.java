/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sce.principal.Estudiante;
import sce.principal.Persona;

/**
 *
 * @author Usuario
 */
public class EstudianteBasico implements Estudiante, Persona {
    // El objeto que tiene la conexión con la Base de datos
    private final Connection conexion;
    // Atributos propios de un Estudiante
    private int ID;
    private String CUI, nombres, apellidos, direccion;
    private char sexo;
    // Atributos propios de la Asignación del Estudiante
    private int IDCiclo, IDGrado;
    
    public EstudianteBasico(Connection conexion) {
        this.conexion = conexion;
    }
    public EstudianteBasico(Connection conexion, int ID) {
        // Éste constructor se llamaría cuando ya existe un registro para este estudiante
        this.conexion = conexion;
        this.ID = ID;
        // Realización de la consulta para obtener los atributos del Estudiante
        // DE ALGUNA FORMA, AQUÍ SE DEBE OBTENER EL ID DEL CICLO ESCOLAR Y EL GRADO AL QUE ESTÁ ASIGNADO
        try {
            //Statement sentencia = conexion.createStatement();
            ResultSet cConsulta = conexion.createStatement().executeQuery("SELECT CUI, nombres, apellidos, direccion, sexo FROM estudiante WHERE id = "+ID);
            cConsulta.next();
            CUI = cConsulta.getString("CUI");
            nombres = cConsulta.getString("nombres");
            apellidos = cConsulta.getString("apellidos");
            direccion = cConsulta.getString("direccion");
            sexo = cConsulta.getString("sexo").charAt(0);
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteBasico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Métodos para asignar valores a los atributos de un Estudiante
    public void setID(int ID) { this.ID = ID; }
    public void setCUI(String CUI) { this.CUI = CUI; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setSexo(char sexo) { this.sexo = sexo; }
    // Métodos para asignar valores a la Asignación del Estudiante
    public void setAtributosAsignacion(int idCiclo, int idGrado) {
        this.IDCiclo = idCiclo;
        this.IDGrado = idGrado;
    }
    // Métodos para obtener los valores de los atributos de un Estudiante
    public int getID() { return ID; }
    public String getCUI() { return CUI; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDireccion() { return direccion; }
    public char getSexo() { return sexo; }
    // Métodos para obtener los valores de la Asignación del Estudiante
    public int getIDCicloEscolar() { return IDCiclo; }
    public int getIDGrado() { return IDGrado; }

    @Override
    public void consultarCicloEscolar() {
        try {
            //Statement sentencia = conexion.createStatement();
            ResultSet cConsulta = conexion.createStatement().executeQuery("SELECT Anio FROM CicloEscolar WHERE id = "+IDCiclo);
            cConsulta.next();
            System.out.println("Año: "+cConsulta.getString("Anio"));
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteBasico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void consultarGrado() {
        try {
            //Statement sentencia = conexion.createStatement();
            ResultSet cConsulta = conexion.createStatement().executeQuery("SELECT Nombre, Seccion FROM Grado WHERE id = "+IDGrado);
            cConsulta.next();
            System.out.println("Grado: "+cConsulta.getString("Nombre")+" sección "+cConsulta.getString("Seccion"));
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteBasico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void consultarCursos() {
        try {
            //Statement sentencia = conexion.createStatement();
            // AQUÍ IRÁ EL SQL PARA CONSULTAR LOS CURSOS ASOCIADOS AL GRADO EN EL QUE ESTÁ ASIGNADO EL ESTUDIANTE
            ResultSet cConsulta = conexion.createStatement().executeQuery("");
            cConsulta.next();
            System.out.println("Consultando cursos del Estudiante");
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteBasico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
