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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sce.nivel.NivelEducativo;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.entity.CicloEscolarEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.ormjpa.EstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class Principal {
    //private static Connection conexion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // CREAR ESTUDIANTES
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        NivelEducativo director = new NivelEducativo(emf);
        EstudianteEntity estudiante = (EstudianteEntity)director.obtenerPersona(NivelEducativo.TIPO_PERSONA_ESTUDIANTE);
        System.out.println("Estudiante = "+estudiante.yaExiste());
        // Llenado de estudiante
        estudiante.setCui("9234567890123");
        estudiante.setNombres("Óscar");
        estudiante.setApellidos("Juárez");
        estudiante.setDireccion("Xela");
        director.crearPersona(estudiante);
        System.out.println("EXITO");
        /*AsignacionCatedraticoBasico asignadorCat = (AsignacionCatedraticoBasico) director.obtenerAsignacion(0);
        asignadorCat.crearAsignacion(asignadorCat);
        director.crearAsignacion(asignadorCat);*/
        // Ciclo escolar
        CicloEscolarEntity ciclo = (CicloEscolarEntity) director.obtenerElementoEducativo(NivelEducativo.TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR, 2l);
        System.out.println("ciclo="+ciclo);
        // CursoEntity
        CursoEntity curso = (CursoEntity)director.obtenerElementoEducativo(NivelEducativo.TIPO_ELEMENTO_EDUCATIVO_CURSO);
        curso.setCurso("Matemáticas");
        curso.setDescripcion("Algo");
        director.crearElementoEducativo(curso);
        // Asignación curos
        AsignacionCursoEntity asignacionC = (AsignacionCursoEntity)director.obtenerAsignacion(NivelEducativo.TIPO_ASIGNACION_CURSO);
        asignacionC.setCiclo_escolar_id(ciclo.getId());
        asignacionC.setCurso_id(curso.getId());
        director.crearAsignacion(asignacionC);
        EstudianteJpaController est = new EstudianteJpaController(emf);
        List<EstudianteEntity> lista = est.buscarPorAsignacionId(0l);
        for (EstudianteEntity estudiante1 : lista) {
            System.out.println(estudiante1);
        }
    }
    
    public static void otro() {
        /*Conexion conector = new Conexion(crearConexion());
        
        NivelEducativo director = new NivelEducativo(conector);
        Asignacion asig = director.obtenerAsignacion(CentroEducativo.TIPO_ASIGNACION_ESTUDIANTE);
        ElementoEducativo ciclo = director.crearElementoEducativo(CentroEducativo.TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR,0);
        ElementoEducativo grado = director.crearElementoEducativo(CentroEducativo.TIPO_ELEMENTO_EDUCATIVO_GRADO,0);
        AsignacionBuilder builder = director.crearAsignacionBuilder();
        Persona est = director.obtenerPersona(CentroEducativo.TIPO_PERSONA_ESTUDIANTE,0);
        
        builder.setAsignacion(asig, CentroEducativo.TIPO_ASIGNACION_ESTUDIANTE);
        builder.setElementoPrincipal(ciclo);
        builder.setElementoSecundario(grado);
        builder.setElementoTerciario(est);
        
        asig = builder.getAsignacion();
        asig.obtenerAsignacion(conector);*/
    }
    public static Connection crearConexion() {
        try {
            String driver="com.mysql.jdbc.Driver", url="jdbc:mysql://";
            Class.forName(driver);//Se utiliza el driver de conexion
            return DriverManager.getConnection(url+"localhost/<nombre_bd>","<usuario>","<contraseña>");//Se conecta con la base de datos
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
