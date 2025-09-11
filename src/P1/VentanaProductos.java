package P1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaProductos extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnModifica;
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnListar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProductos frame = new VentanaProductos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaProductos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 427, 25);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(0, 0, 80, 23);
		panel.add(btnNewButton);
		
		btnModifica = new JButton("Modificar");
		btnModifica.setBounds(90, 0, 92, 23);
		panel.add(btnModifica);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(210, 0, 92, 23);
		panel.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(325, 0, 92, 23);
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

		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(10, 42, 80, 23);
		contentPane.add(btnListar);
		mostrarProductos();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		VentanaAgregarProducto ventana = new VentanaAgregarProducto();
		ventana.setVisible(true);
	}
	private void mostrarProductos() {
		List<Producto> productos = ConexionBD.obtenerProductos();
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0); 
		for (var p : productos){
			modelo.addRow(new Object[]{p.getCodigo(),p.getDescripcion(),p.getStock(),p.getpUnitario()});
		}
	}
	
}
