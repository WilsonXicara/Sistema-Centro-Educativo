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
import sce.principal.CicloEscolar;
import sce.principal.ElementoEducativo;

/**
 *
 * @author Usuario
 */
public class CicloEscolarBasico implements ElementoEducativo, CicloEscolar {
    private final Connection conexion;
    private int ID;
    private boolean estaListo, estaAbierto;
    
    public CicloEscolarBasico(Connection conexion) {
        this.conexion = conexion;
    }
    public CicloEscolarBasico(Connection conexion, int ID) {
        this.conexion = conexion;
        this.ID = ID;
        // Realización de la consulta para obtener los atributos del Catedrático
        // DE ALGUNA FORMA, AQUÍ SE DEBE OBTENER EL ESTADO DEL CICLO ESCOLAR AL QUE SE HACE REFERENCIA
        try {
            //Statement sentencia = conexion.createStatement();
            ResultSet cConsulta = conexion.createStatement().executeQuery("SELECT * FROM CicloEscolar WHERE id = "+ID);
            cConsulta.next();
        } catch (SQLException ex) {
            Logger.getLogger(CicloEscolarBasico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void abrirCiclo() {
        // ACCIÓN CORRESPONDIENTE PARA ABRIR EL CICLO ESCOLAR
    }

    @Override
    public void cerrarCiclo() {
        // ACCIÓN CORRESPONDIENTE PARA CERRAR EL CICLO ESCOLAR
    }

    @Override
    public void consultarGrados() {
        // ACCIÓN CORRESPONDIENTE PARA CONSULTAR GRADOS DEL CICLO ESCOLAR
    }

    @Override
    public void consultarCalendario() {
        // ACCIÓN CORRESPONDIENTE PARA CONSULTAR CALENDARIO DEL CICLO ESCOLAR
    }
    
}
