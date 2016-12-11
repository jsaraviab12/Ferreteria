/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus
 */
public class Helper {

    public static void mensaje(Component ventana, String mensaje, int tipo) {
        switch (tipo) {
            case 1:
                JOptionPane.showMessageDialog(ventana, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(ventana, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                break;

        }
    }

    public static void limpiadoTabla(JTable tabla1) {
        int nf, nc;
        nc = tabla1.getColumnCount();
        nf = tabla1.getRowCount();
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                tabla1.setValueAt("", i, j);
            }
        }
    }

    public static void porDefectoTabla(JTable tabla1) {
        DefaultTableModel tm;
        tm = (DefaultTableModel) tabla1.getModel();
        tm.setColumnCount(0);
        tm.setRowCount(0);
    }

    public static void habilitarBotones(JButton[] botones) {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(true);

        }
    }

    public static void deshabilitarBotones(JButton[] botones) {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);

        }
    }

    public static int[][] pasoDeDatos(JTable tabla1) {
        int nf, nc;
        nc = tabla1.getColumnCount();
        nf = tabla1.getRowCount();

        int m[][] = new int[nf][nc];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {

                m[i][j] = (int) tabla1.getValueAt(i, j);
            }

        }
        return m;

    }

    public static ArrayList traerDatos(String ruta) {
        FileInputStream archivo;
        ObjectInputStream entrada;
        ArrayList personas = new ArrayList();
        Object p;

        try {
            archivo = new FileInputStream(ruta);
            entrada = new ObjectInputStream(archivo);
            while ((p = entrada.readObject()) != null) {
                personas.add(p);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return personas;
    }

    public static void volcado(ObjectOutputStream salida, ArrayList Productos) {
        for (int i = 0; i < Productos.size(); i++) {
            try {
                salida.writeObject(Productos.get(i));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public static void llenarTabla(JTable tabla, String ruta) {
        DefaultTableModel tm;
        int nf;
        ArrayList<Productos> productos = traerDatos(ruta);
        tm = (DefaultTableModel) tabla.getModel();
        limpiadoTabla(tabla);
        nf = productos.size();
        tm.setRowCount(nf);
        for (int i = 0; i < nf; i++) {
            tabla.setValueAt(i + 1, i, 0);
            tabla.setValueAt(productos.get(i).getNombre(), i, 0);
            tabla.setValueAt(productos.get(i).getReferencia(), i, 1);
            tabla.setValueAt(productos.get(i).getCantidad(), i, 2);
            tabla.setValueAt(productos.get(i).getPrecio(), i, 3);
        }
    }
    public static ArrayList<Productos> modificarProducto(String ruta, String nombre, String referencia, String cantidad, String precio) {
        ArrayList<Productos> productos = traerDatos(ruta);
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getReferencia().equals(referencia)) {
                productos.get(i).setNombre(nombre);
                productos.get(i).setPrecio(precio);
               productos.get(i).setCantidad(cantidad);

                return productos;
            }

        }
        return null;
    }
     public static boolean buscarProductoReferencia(String referencia, String ruta) {
        ArrayList<Productos> productos = traerDatos(ruta);
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getReferencia().equals(referencia)) {
                return true;
            }
        }
        return false;
    }

    public static Productos traerPersonaCedula(String referencia, String ruta) {
        ArrayList<Productos> productos = traerDatos(ruta);
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getReferencia().equals(referencia)) {
                return productos.get(i);
            }

        }
        return null;
    }
    
       
    
    

}
