/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

//import sce.nivel.basico.NivelBasico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static Connection conexion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        crearConexion();
        
        NivelEducativo director = new NivelEducativo(conexion);
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
    public static void crearConexion() {
        try {
            String driver="com.mysql.jdbc.Driver", url="jdbc:mysql://";
            Class.forName(driver);//Se utiliza el driver de conexion
            conexion=DriverManager.getConnection(url+"localhost/<nombre_bd>","<usuario>","<contraseña>");//Se conecta con la base de datos
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
