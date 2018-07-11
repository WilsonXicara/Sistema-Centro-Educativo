/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.gui;

import sce.gui.paneles.PanelCicloEscolar;
import sce.gui.paneles.PanelAsignacionCarrera;
import sce.gui.paneles.PanelAsignacionCurso;
import sce.gui.paneles.PanelCatedratico;
import sce.gui.paneles.PanelCurso;
import sce.gui.paneles.PanelAsignacionEstudiante;
import sce.gui.paneles.PanelEstudiante;
import sce.gui.paneles.PanelGrado;
import sce.gui.paneles.PanelAnulaciones;
import sce.gui.paneles.PanelAsignacionGrado;
import sce.gui.paneles.PanelCarrera;
import sce.gui.paneles.PanelCalendarioEscolar;
import sce.gui.paneles.PanelAsignacionCatedratico;
import java.awt.BorderLayout;
import sce.principal.GestorConexion;

/**
 *
 * @author juan_
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    private final MenuPrincipal miMenu;
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() { 
        initComponents();
        this.setLocationRelativeTo(null);
        GestorConexion.definirNombrePersistenceUnit("Sistema-Centro-EducativoPU");
        miMenu = (MenuPrincipal)jMenuBar1;
        miMenu.generarItemsPorDefecto(this, panel_principal);
        miMenu.iniciarMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_principal = new javax.swing.JPanel();
        jMenuBar1 = new MenuPrincipal();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ROXI");
        setResizable(false);

        panel_principal.setBackground(new java.awt.Color(102, 102, 102));
        panel_principal.setPreferredSize(new java.awt.Dimension(683, 526));
        panel_principal.setRequestFocusEnabled(false);
        panel_principal.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panel_principalLayout = new javax.swing.GroupLayout(panel_principal);
        panel_principal.setLayout(panel_principalLayout);
        panel_principalLayout.setHorizontalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
        );
        panel_principalLayout.setVerticalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        String temaVentana = "Windows";
        if (args.length > 0) {
            temaVentana = args[0];
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (temaVentana.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel panel_principal;
    // End of variables declaration//GEN-END:variables
}