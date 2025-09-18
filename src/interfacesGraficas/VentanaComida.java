package interfacesGraficas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.ArregloComida;
import clases.Comida;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class VentanaComida extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
		setBounds(100, 100, 481, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 328, 386);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Descripción"); 
		modelo.addColumn("Stock");
		modelo.addColumn("Precio Unitario");
		table.setModel(modelo);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(20, 11, 92, 23);
		contentPane.add(btnAgregar);
		

		btnModifica = new JButton("Modificar");
		btnModifica.addActionListener(this);
		btnModifica.setBounds(132, 11, 92, 23);
		contentPane.add(btnModifica);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(364, 11, 92, 23);
		contentPane.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(248, 9, 92, 26);
		contentPane.add(btnEliminar);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(370, 130, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(370, 397, 92, 23);
		contentPane.add(btnListar);
		

		lblNewLabel = new JLabel("Ingresa código a");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(358, 59, 111, 41);
		contentPane.add(lblNewLabel);
		
		lblModificarBuscar = new JLabel("Eliminar o Buscar:");
		lblModificarBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarBuscar.setBounds(358, 79, 111, 41);
		contentPane.add(lblModificarBuscar);
		btnAgregar.addActionListener(this);
	}
	ArregloComida ac = ArregloComida.getInstancia();
	private JTextField txtCodigo;
	private JButton btnListar;
	private JLabel lblNewLabel;
	private JLabel lblModificarBuscar;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModifica) {
			do_btnModifica_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnListar) {
			do_btnListar_actionPerformed(e);
		}
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
	
	int LeerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
		
	}
	public void mostrarProductos() {
		ac.Listar(table);
	}
	
	protected void do_btnListar_actionPerformed(ActionEvent e) {		
			int size = ac.Tamaño();	
			if (size == 0) {
				JOptionPane.showMessageDialog(this, "La lista esta vacía.");
			}
			else {
				mostrarProductos();
				JOptionPane.showMessageDialog(this, "Lista actualizada.");
			}
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
			if(ac.Buscar(LeerCodigo())!=null) {
				ac.Eliminar(ac.Buscar(LeerCodigo()));
				txtCodigo.setText("");
				txtCodigo.requestFocus();
				JOptionPane.showMessageDialog(this, "Código eliminado con éxito");
				ac.Listar(table);
			}
			else JOptionPane.showMessageDialog(this, "El código a eliminar no existe");
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(this, "Inserta el código correcto a eliminar.");
		}		
	}
	
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		try {
			Comida enc = ac.Buscar(LeerCodigo());	
			if (enc != null) {
				JOptionPane.showMessageDialog(this, "Código: "+ LeerCodigo()+ "\nDescripción: " + enc.getDescripcion() + "\nPrecio Unitario: " + enc.getpUnitario() +"\nStock: " + enc.getStock());
			}
			else {
				JOptionPane.showMessageDialog(this, "El plato no existe.");
			}
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(this, "Inserta el código correcto a buscar.");
		}
	}
	
	protected void do_btnModifica_actionPerformed(ActionEvent e) {	
		try {
			VentanaModificar ventana = new VentanaModificar();
		    ventana.addWindowListener(new java.awt.event.WindowAdapter() {
		        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
		            mostrarProductos();
		        }
		    });	    
		    ventana.setVisible(true);
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(this, "Inserta un código primero.");
		}
	}
}