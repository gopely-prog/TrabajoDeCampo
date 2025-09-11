package interfacesGraficas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.ArregloComida;
import clases.Comida;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaComida extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAgregar;
	private JButton btnModifica;
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VentanaComida() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 427, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(0, 0, 80, 23);
		panel.add(btnAgregar);
		
		btnModifica = new JButton("Modificar");
		btnModifica.setBounds(90, 0, 92, 23);
		panel.add(btnModifica);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(210, 0, 92, 23);
		panel.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(325, 0, 92, 23);
		panel.add(btnEliminar);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(0, 31, 80, 23);
		panel.add(btnListar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 76, 417, 453);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Descripción"); 
		modelo.addColumn("Stock");
		modelo.addColumn("Precio Unitario");
		table.setModel(modelo);
		mostrarProductos();
	}
	ArregloComida ac= new ArregloComida();
	private JButton btnListar;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnListar) {
			do_btnListar_actionPerformed(e);
		}
		if (e.getSource() == btnAgregar) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		VentanaAgregarComida ventana = new VentanaAgregarComida();
		ventana.setVisible(true);
	}
	private void mostrarProductos() {
		ac.Listar(table);
	}
	protected void do_btnListar_actionPerformed(ActionEvent e) {
		ac.Listar(table);
	}
}
