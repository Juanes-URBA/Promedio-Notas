package gui;

import javax.swing.*;
import java.awt.event.*;

public class VentanaMenu extends JFrame implements ActionListener {

	private JButton btnRegistrar;
	private JButton btnLista;

	public VentanaMenu(){

		setTitle("MENÚ PRINCIPAL");
		setSize(400,300);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel bienvenida = new JLabel("BIENVENIDO AL SISTEMA CONTROL DE NOTAS");
		bienvenida.setBounds(40,40,350,30);
		add(bienvenida);

		btnRegistrar = new JButton("Registrar Estudiante");
		btnRegistrar.setBounds(100,100,200,30);
		btnRegistrar.addActionListener(this);
		add(btnRegistrar);

		btnLista = new JButton("Consultar Lista");
		btnLista.setBounds(100,150,200,30);
		btnLista.addActionListener(this);
		add(btnLista);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnRegistrar){
			VentanaPrincipal v = new VentanaPrincipal();
			v.setVisible(true);
		}

		if(e.getSource()==btnLista){
			VentanaPrincipal v = new VentanaPrincipal();
			v.consultarLista();
		}
	}
}