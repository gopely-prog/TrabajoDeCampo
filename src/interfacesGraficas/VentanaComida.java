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
		
		btnModifica.setBounds(132, 11, 92, 23);
		contentPane.add(btnModifica);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(364, 11, 92, 23);
		contentPane.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(248, 9, 92, 26);
		contentPane.add(btnEliminar);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(370, 75, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(370, 397, 92, 23);
		contentPane.add(btnListar);
		
		txtModificar = new JTextField();
		txtModificar.setColumns(10);
		txtModificar.setBounds(370, 152, 86, 20);
		contentPane.add(txtModificar);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(374, 56, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblModifiar = new JLabel("Modificar:");
		lblModifiar.setLabelFor(this);
		lblModifiar.setBounds(370, 134, 62, 14);
		contentPane.add(lblModifiar);
		btnAgregar.addActionListener(this);
	}
	ArregloComida ac = ArregloComida.getInstancia();
	private JTextField txtCodigo;
	private JButton btnListar;
	private JTextField txtModificar;
	public void actionPerformed(ActionEvent e) {
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
	
	int LeerModifciar() {
		return Integer.parseInt(txtModificar.getText());
	}
	public void mostrarProductos() {
		ac.Listar(table);
	}
	protected void do_btnListar_actionPerformed(ActionEvent e) {
			mostrarProductos();
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		if(ac.Buscar(LeerCodigo())!=null) {
			ac.Eliminar(ac.Buscar(LeerCodigo()));
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		}else JOptionPane.showMessageDialog(this, "El código a eliminar no existe");
	}
	{
	btnModifica = new JButton("Modificar");
	btnModifica.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ac.Modificar(LeerCodigo(),LeerModifciar());
		}
	});
}
}