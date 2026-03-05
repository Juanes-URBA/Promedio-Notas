package gui;

import entidades.Estudiantes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

public class VentanaLista extends JDialog {

    public VentanaLista(JFrame parent, Collection<Estudiantes> lista) {

        super(parent, "Lista de Estudiantes", true);

        setSize(700,400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Documento");
        modelo.addColumn("Nombre");
        modelo.addColumn("Materia");
        modelo.addColumn("Nota1");
        modelo.addColumn("Nota2");
        modelo.addColumn("Nota3");
        modelo.addColumn("Promedio");

        for (Estudiantes est : lista) {
            modelo.addRow(new Object[]{
                    est.getDocumento(),
                    est.getNombre(),
                    est.getMateria(),
                    est.getNota1(),
                    est.getNota2(),
                    est.getNota3(),
                    est.getPromedio()
            });
        }

        JTable tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}