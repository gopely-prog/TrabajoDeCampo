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
import java.awt.Color;

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
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 446, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(0, 0, 80, 54);
		panel.add(btnAgregar);
		
		btnModifica = new JButton("Modificar");
		btnModifica.setBounds(108, 0, 92, 54);
		panel.add(btnModifica);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(230, 0, 92, 54);
		panel.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(344, 0, 92, 54);
		panel.add(btnEliminar);
		
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
	ArregloComida ac = ArregloComida.getInstancia();
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		VentanaAgregarComida ventana = new VentanaAgregarComida();
	    ventana.addWindowListener(new java.awt.event.WindowAdapter() {
	        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	            mostrarProductos(); 
	        }
	    });
	    
	    ventana.setVisible(true);
	}
	public void mostrarProductos() {
		ac.Listar(table);
	}
}
