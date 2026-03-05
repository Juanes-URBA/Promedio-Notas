package gui;

import logica.ModeloDatos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import logica.Procesos;
import entidades.Estudiantes;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private JTextField txtDocumento;
	private JTextField txtMateria;
	private JTextField txtNombre;
	private JTextField txtNota1;
	private JTextField txtNota2;
	private JTextField txtNota3;

	private JButton btnCalcular;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnConsultaIndividual;
	private JButton btnLista;
	private JButton btnVolver;

	private JLabel lblResultado;

	Procesos misProcesos;
	ModeloDatos miModeloDatos;

	public VentanaPrincipal(){

		misProcesos = new Procesos();
		miModeloDatos = new ModeloDatos();

		setTitle("Registrar Estudiantes");
		setSize(900,600);
		setLayout(null);
		setLocationRelativeTo(null);

		iniciarComponentes();
	}

	private void iniciarComponentes(){

		JLabel lblTitulo = new JLabel("SISTEMA CONTROL DE NOTAS");
		lblTitulo.setFont(new Font("verdana", Font.BOLD,25));
		lblTitulo.setBounds(150,20,600,40);
		add(lblTitulo);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(50,100,100,20);
		add(lblDocumento);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(150,100,150,20);
		add(txtDocumento);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(50,140,100,20);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(150,140,150,20);
		add(txtNombre);

		JLabel lblMateria = new JLabel("Materia:");
		lblMateria.setBounds(50,180,100,20);
		add(lblMateria);

		txtMateria = new JTextField();
		txtMateria.setBounds(150,180,150,20);
		add(txtMateria);

		JLabel lblNota1 = new JLabel("Nota 1:");
		lblNota1.setBounds(50,220,100,20);
		add(lblNota1);

		txtNota1 = new JTextField();
		txtNota1.setBounds(150,220,100,20);
		add(txtNota1);

		JLabel lblNota2 = new JLabel("Nota 2:");
		lblNota2.setBounds(300,220,100,20);
		add(lblNota2);

		txtNota2 = new JTextField();
		txtNota2.setBounds(370,220,100,20);
		add(txtNota2);

		JLabel lblNota3 = new JLabel("Nota 3:");
		lblNota3.setBounds(520,220,100,20);
		add(lblNota3);

		txtNota3 = new JTextField();
		txtNota3.setBounds(590,220,100,20);
		add(txtNota3);

		lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(50,260,700,30);
		add(lblResultado);

		btnCalcular = new JButton("Calcular y Registrar");
		btnCalcular.setBounds(50,320,170,30);
		btnCalcular.addActionListener(this);
		add(btnCalcular);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(230,320,120,30);
		btnLimpiar.addActionListener(this);
		add(btnLimpiar);

		btnConsultaIndividual = new JButton("Consultar");
		btnConsultaIndividual.setBounds(360,320,120,30);
		btnConsultaIndividual.addActionListener(this);
		add(btnConsultaIndividual);

		btnLista = new JButton("Lista");
		btnLista.setBounds(490,320,120,30);
		btnLista.addActionListener(this);
		add(btnLista);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(620,320,120,30);
		btnEliminar.addActionListener(this);
		add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(750,320,120,30);
		btnActualizar.addActionListener(this);
		add(btnActualizar);

		btnVolver = new JButton("Volver al menú");
		btnVolver.setBounds(350,400,200,30);
		btnVolver.addActionListener(this);
		add(btnVolver);
	}

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==btnCalcular) calcular();
		if(e.getSource()==btnLimpiar) limpiar();
		if(e.getSource()==btnConsultaIndividual) consultaIndividual();
		if(e.getSource()==btnLista) consultarLista();
		if(e.getSource()==btnEliminar) eliminarEstudiante();
		if(e.getSource()==btnActualizar) actualizarEstudiante();
		if(e.getSource()==btnVolver){
			dispose();
			new VentanaMenu().setVisible(true);
		}
	}

	private void calcular(){

		Estudiantes est = new Estudiantes();

		est.setDocumento(txtDocumento.getText());
		est.setNombre(txtNombre.getText());
		est.setMateria(txtMateria.getText());
		est.setNota1(Double.parseDouble(txtNota1.getText()));
		est.setNota2(Double.parseDouble(txtNota2.getText()));
		est.setNota3(Double.parseDouble(txtNota3.getText()));

		double promedio = misProcesos.calcularPromedio(est);
		est.setPromedio(promedio);

		if(promedio!=-1){

			String registro = miModeloDatos.registrarEstudiante(est);

			if(registro.equals("ok")){
				lblResultado.setText("Promedio: "+promedio);
				lblResultado.setForeground(Color.BLUE);
			}else{
				JOptionPane.showMessageDialog(this, registro);
			}
		}else{
			lblResultado.setText("Notas inválidas (0 a 5)");
			lblResultado.setForeground(Color.RED);
		}
	}

	private void limpiar(){
		txtDocumento.setText("");
		txtNombre.setText("");
		txtMateria.setText("");
		txtNota1.setText("");
		txtNota2.setText("");
		txtNota3.setText("");
		lblResultado.setText("Resultado:");
	}

	public void consultarLista(){
		VentanaLista ventana = new VentanaLista(this,miModeloDatos.obtenerLista());
		ventana.setVisible(true);
	}

	private void consultaIndividual(){

		String documento = JOptionPane.showInputDialog("Ingrese documento:");

		Estudiantes est = miModeloDatos.consultaEstudiante(documento);

		if(est!=null){
			txtDocumento.setText(est.getDocumento());
			txtNombre.setText(est.getNombre());
			txtMateria.setText(est.getMateria());
			txtNota1.setText(est.getNota1()+"");
			txtNota2.setText(est.getNota2()+"");
			txtNota3.setText(est.getNota3()+"");
			lblResultado.setText("Promedio: "+est.getPromedio());
		}else{
			JOptionPane.showMessageDialog(this,"No existe");
		}
	}

	private void eliminarEstudiante(){

		String documento = JOptionPane.showInputDialog("Documento a eliminar:");
		String mensaje = miModeloDatos.eliminarEstudiante(documento);
		JOptionPane.showMessageDialog(this, mensaje);
	}

	private void actualizarEstudiante(){

		Estudiantes est = new Estudiantes();

		est.setDocumento(txtDocumento.getText());
		est.setNombre(txtNombre.getText());
		est.setMateria(txtMateria.getText());
		est.setNota1(Double.parseDouble(txtNota1.getText()));
		est.setNota2(Double.parseDouble(txtNota2.getText()));
		est.setNota3(Double.parseDouble(txtNota3.getText()));

		double promedio = misProcesos.calcularPromedio(est);
		est.setPromedio(promedio);

		String mensaje = miModeloDatos.actualizarEstudiante(est);
		JOptionPane.showMessageDialog(this, mensaje);
	}
}