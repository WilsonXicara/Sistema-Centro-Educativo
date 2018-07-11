/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.gui;

import sce.gui.paneles.PanelCicloEscolar;
import sce.gui.paneles.PanelAsignacionCarrera;
import sce.gui.paneles.PanelAsignacionCurso;
import sce.gui.paneles.PanelAnulacionAsigEstudiante;
import sce.gui.paneles.PanelCatedratico;
import sce.gui.paneles.PanelCurso;
import sce.gui.paneles.PanelAnulacionCatedratico;
import sce.gui.paneles.PanelAsignacionEstudiante;
import sce.gui.paneles.PanelAnulacionEstudiante;
import sce.gui.paneles.PanelEstudiante;
import sce.gui.paneles.PanelGrado;
import sce.gui.paneles.PanelAnulacionAsigGrado;
import sce.gui.paneles.PanelAnulacionAsigCatedratico;
import sce.gui.paneles.PanelAsignacionGrado;
import sce.gui.paneles.PanelCarrera;
import sce.gui.paneles.PanelAnulacionAsigCarrera;
import sce.gui.paneles.PanelCalendarioEscolar;
import sce.gui.paneles.PanelAsignacionCatedratico;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class MenuPrincipal extends JMenuBar {
    private ArrayList<ContenidoMenuItem> listaMenus;
    public static String CARPETA_PLUGINS = "lib/";
    private VentanaPrincipal padre;
    private JPanel panel_principal;
    public MenuPrincipal() {
        super();
        this.listaMenus = new ArrayList<>();
    }
    private void cargarPanel(JPanel panel) {
        panel_principal.removeAll();
        panel_principal.setLayout(new BorderLayout());
        panel_principal.add(panel, BorderLayout.CENTER);
        padre.pack();
    }
    public void generarItemsPorDefecto(VentanaPrincipal padre, JPanel panel_principal) {
        this.padre = padre;
        this.panel_principal = panel_principal;
        // Generación del menú 'Personas'
        ContenidoMenuItem menuPersona = new ContenidoMenuItem("Personas");
        menuPersona.addInformacionItem("Nuevo estudiante", "asignacion.estudiante.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelEstudiante());
            }
        });
        menuPersona.addInformacionItem("Nuevo catedratico", "asignacion.catedratico.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelCatedratico());
            }
        });
        this.listaMenus.add(menuPersona);
        // Generación del menú 'Elementos asignatura'
        ContenidoMenuItem menuElementoAsignatura = new ContenidoMenuItem("Elementos asignatura");
        menuElementoAsignatura.addInformacionItem("Nuevo ciclo escolar", "motor.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelCicloEscolar());
            }
        });
        menuElementoAsignatura.addInformacionItem("Nueva carrera", "carrera.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelCarrera());
            }
        });
        menuElementoAsignatura.addInformacionItem("Nuevo grado", "motor.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelGrado());
            }
        });
        menuElementoAsignatura.addInformacionItem("Nuevo curso", "motor.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelCurso());
            }
        });
        menuElementoAsignatura.addInformacionItem("Nuevo calendario", "elemento.calendario.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelCalendarioEscolar());
            }
        });
        this.listaMenus.add(menuElementoAsignatura);
        // Generación del menú 'Asignaciones'
        ContenidoMenuItem menuAsignaciones = new ContenidoMenuItem("Asignaciones");
        menuAsignaciones.addInformacionItem("Asignación de carrera", "asignacion.carrera.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAsignacionCarrera());
            }
        });
        menuAsignaciones.addInformacionItem("Asignación de grado", "motor.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAsignacionGrado());
            }
        });
        menuAsignaciones.addInformacionItem("Asignación de curso", "motor.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAsignacionCurso());
            }
        });
        menuAsignaciones.addInformacionItem("Asignación de catedráticos", "asignacion.catedratico.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAsignacionCatedratico());
            }
        });
        menuAsignaciones.addInformacionItem("Asignación de estudiantes", "asignacion.estudiante.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAsignacionEstudiante());
            }
        });
        this.listaMenus.add(menuAsignaciones);
        // Generación del menú 'Asignaciones'
        ContenidoMenuItem menuAnulaciones = new ContenidoMenuItem("Anulaciones");
        menuAnulaciones.addInformacionItem("Anulación de Asignación de carrera", "asignacion.carrera.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAnulacionAsigCarrera());
            }
        });
        menuAnulaciones.addInformacionItem("Anulación de Asignación de grado", "asignacion.carrera.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAnulacionAsigGrado());
            }
        });
        menuAnulaciones.addInformacionItem("Anulación de Asignación de curso", "asignacion.curso.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new JPanel());
            }
        });
        menuAnulaciones.addInformacionItem("Anulación de catedráticos", "asignacion.catedratico.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAnulacionCatedratico());
            }
        });
        menuAnulaciones.addInformacionItem("Anulación de Asignación de catedráticos", "asignacion.catedratico.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAnulacionAsigCatedratico());
            }
        });
        menuAnulaciones.addInformacionItem("Anulación de estudiantes", "asignacion.estudiante.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAnulacionEstudiante());
            }
        });
        menuAnulaciones.addInformacionItem("Anulación de Asignación de estudiantes", "asignacion.estudiante.jar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanel(new PanelAnulacionAsigEstudiante());
            }
        });
        this.listaMenus.add(menuAnulaciones);
    }
    public void iniciarMenu() {
        this.removeAll();
        // Obtención de todos los archivos JAR agregados en la carpeta lib/
        File carpetaLibs = new File(CARPETA_PLUGINS);
        if (!carpetaLibs.exists()) {
            System.err.println("ERROR: No existe el directorio de los plugins. El sistema no podrá iniciarse");
            System.exit(0);
        }
        File[] archivos = carpetaLibs.listFiles();
        ArrayList<String> nombreArchivos = new ArrayList<>();
        for (File archivo : archivos) {
            nombreArchivos.add(archivo.getName());
        }
        // Construcción del JMenuBar:
        int cantidad, index;
        String jarRequerido;
        for (ContenidoMenuItem contenidoMenu : listaMenus) {
            JMenu menu = new JMenu(contenidoMenu.nombreMenu);
            ArrayList<String> listaItems = contenidoMenu.getListaItems();
            ArrayList<String> listaJars = contenidoMenu.getListaJars();
            ArrayList<ActionListener> listaAcciones = contenidoMenu.getListaAcciones();
            cantidad = listaItems.size();
            for(index=0; index<cantidad; index++) {
                jarRequerido = listaJars.get(index);
                if (nombreArchivos.contains(jarRequerido)) {
                    // Se agrega el Item
                    JMenuItem item = new JMenuItem(listaItems.get(index));
                    item.addActionListener(listaAcciones.get(index));
                    menu.add(item);
                }
            }
            if (menu.getItemCount() > 0) {
                this.add(menu);
            }
        }
        if (this.getComponentCount() == 0) {
            JOptionPane.showMessageDialog(padre, "No se detectó ningún módulo agregado", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        this.revalidate();
        this.repaint();
    }
    
    public static class ContenidoMenuItem {
        private String nombreMenu;
        private ArrayList<String> listaItems, listaJars;
        private ArrayList<ActionListener> listaAcciones;
        public ContenidoMenuItem(String nombreMenu) {
            this.nombreMenu = nombreMenu;
            this.listaItems = new ArrayList<>();
            this.listaJars = new ArrayList<>();
            this.listaAcciones = new ArrayList<>();
        }
        public void addInformacionItem(String nombreItem, String nombreJar, ActionListener accion) {
            this.listaItems.add(nombreItem);
            this.listaJars.add(nombreJar);
            this.listaAcciones.add(accion);
        }
        public ArrayList<String> getListaItems() { return this.listaItems; }
        public ArrayList<String> getListaJars() { return this.listaJars; }
        public ArrayList<ActionListener> getListaAcciones() { return this.listaAcciones; }
    }
}
