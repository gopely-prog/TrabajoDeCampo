package interfacesGraficas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class VentanaLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnBorrar;
	private JButton btnLimpiar;
	private JButton btnIngresar;
	private JLabel lblLogo;
	
	// Variable para saber qué campo está activo
	private boolean campoPasswordActivo = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaLogin() {
		setTitle("POLLERIA EXCELENCIA - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null); // Centrar ventana
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// ========== PANEL IZQUIERDO - LOGO ==========
		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(255, 255, 255));
		panelLogo.setBounds(10, 11, 300, 439);
		contentPane.add(panelLogo);
		panelLogo.setLayout(null);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaLogin.class.getResource("/images/Sin título (2) (1).png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(10, 11, 280, 319);
		panelLogo.add(lblLogo);
		
		JLabel lblTitulo = new JLabel("POLLERIA EXCELENCIA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(10, 341, 280, 30);
		panelLogo.add(lblTitulo);
		
		JLabel lblSubtitulo = new JLabel("Sistema de Facturación");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSubtitulo.setBounds(10, 372, 280, 20);
		panelLogo.add(lblSubtitulo);
		
		JLabel lblVersion = new JLabel("Versión 1.0 - 2025");
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setFont(new Font("Arial", Font.ITALIC, 11));
		lblVersion.setForeground(Color.GRAY);
		lblVersion.setBounds(10, 403, 280, 20);
		panelLogo.add(lblVersion);
		
		// ========== PANEL DERECHO - CONTROL DE ACCESO ==========
		JPanel panelAcceso = new JPanel();
		panelAcceso.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
			new Color(255, 255, 255), new Color(160, 160, 160)), 
			"CONTROL DE ACCESO", TitledBorder.CENTER, TitledBorder.TOP, 
			new Font("Arial", Font.BOLD, 16), Color.BLACK));
		panelAcceso.setBackground(new Color(205, 232, 254));
		panelAcceso.setBounds(320, 11, 354, 439);
		contentPane.add(panelAcceso);
		panelAcceso.setLayout(null);
		
		// Contraseña
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblPassword.setBounds(20, 38, 100, 25);
		panelAcceso.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword.setBounds(20, 68, 314, 30);
		panelAcceso.add(txtPassword);
		
		// Listener para activar campo password
		txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				campoPasswordActivo = true;
			}
		});
		
		// ========== TECLADO NUMÉRICO ==========
		JPanel panelTeclado = new JPanel();
		panelTeclado.setBorder(new TitledBorder(null, "Teclado Numérico", 
			TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTeclado.setBounds(20, 121, 314, 259);
		panelAcceso.add(panelTeclado);
		panelTeclado.setLayout(null);
		
		// Botones numéricos en grid 4x3
		btn1 = new JButton("1");
		btn1.setFont(new Font("Arial", Font.BOLD, 18));
		btn1.setBounds(10, 25, 70, 50);
		btn1.addActionListener(this);
		panelTeclado.add(btn1);
		
		btn2 = new JButton("2");
		btn2.setFont(new Font("Arial", Font.BOLD, 18));
		btn2.setBounds(90, 25, 70, 50);
		btn2.addActionListener(this);
		panelTeclado.add(btn2);
		
		btn3 = new JButton("3");
		btn3.setFont(new Font("Arial", Font.BOLD, 18));
		btn3.setBounds(170, 25, 70, 50);
		btn3.addActionListener(this);
		panelTeclado.add(btn3);
		
		btn4 = new JButton("4");
		btn4.setFont(new Font("Arial", Font.BOLD, 18));
		btn4.setBounds(10, 85, 70, 50);
		btn4.addActionListener(this);
		panelTeclado.add(btn4);
		
		btn5 = new JButton("5");
		btn5.setFont(new Font("Arial", Font.BOLD, 18));
		btn5.setBounds(90, 85, 70, 50);
		btn5.addActionListener(this);
		panelTeclado.add(btn5);
		
		btn6 = new JButton("6");
		btn6.setFont(new Font("Arial", Font.BOLD, 18));
		btn6.setBounds(170, 85, 70, 50);
		btn6.addActionListener(this);
		panelTeclado.add(btn6);
		
		btn7 = new JButton("7");
		btn7.setFont(new Font("Arial", Font.BOLD, 18));
		btn7.setBounds(10, 145, 70, 50);
		btn7.addActionListener(this);
		panelTeclado.add(btn7);
		
		btn8 = new JButton("8");
		btn8.setFont(new Font("Arial", Font.BOLD, 18));
		btn8.setBounds(90, 145, 70, 50);
		btn8.addActionListener(this);
		panelTeclado.add(btn8);
		
		btn9 = new JButton("9");
		btn9.setFont(new Font("Arial", Font.BOLD, 18));
		btn9.setBounds(170, 145, 70, 50);
		btn9.addActionListener(this);
		panelTeclado.add(btn9);
		
		// Botón Borrar (←)
		btnBorrar = new JButton("←");
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 16));
		btnBorrar.setBackground(new Color(255, 200, 200));
		btnBorrar.setBounds(250, 25, 54, 50);
		btnBorrar.addActionListener(this);
		panelTeclado.add(btnBorrar);
		
		// Botón Limpiar (C)
		btnLimpiar = new JButton("C");
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 16));
		btnLimpiar.setBackground(new Color(255, 230, 200));
		btnLimpiar.setBounds(250, 85, 54, 50);
		btnLimpiar.addActionListener(this);
		panelTeclado.add(btnLimpiar);
		
		btn0 = new JButton("0");
		btn0.setBounds(91, 202, 70, 50);
		panelTeclado.add(btn0);
		btn0.setFont(new Font("Arial", Font.BOLD, 18));
		btn0.addActionListener(this);
		
		// ========== BOTÓN INGRESAR ==========
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 16));
		btnIngresar.setBackground(new Color(144, 238, 144));
		btnIngresar.setBounds(20, 390, 314, 40);
		btnIngresar.addActionListener(this);
		panelAcceso.add(btnIngresar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Botones numéricos
		if (e.getSource() == btn0) agregarNumero("0");
		if (e.getSource() == btn1) agregarNumero("1");
		if (e.getSource() == btn2) agregarNumero("2");
		if (e.getSource() == btn3) agregarNumero("3");
		if (e.getSource() == btn4) agregarNumero("4");
		if (e.getSource() == btn5) agregarNumero("5");
		if (e.getSource() == btn6) agregarNumero("6");
		if (e.getSource() == btn7) agregarNumero("7");
		if (e.getSource() == btn8) agregarNumero("8");
		if (e.getSource() == btn9) agregarNumero("9");
		
		// Botón Borrar (elimina último carácter)
		if (e.getSource() == btnBorrar) {
			if (campoPasswordActivo) {
				String actual = new String(txtPassword.getPassword());
				if (actual.length() > 0) {
					txtPassword.setText(actual.substring(0, actual.length() - 1));
				}
			} 
		}
		
		// Botón Limpiar (borra todo el campo activo)
		if (e.getSource() == btnLimpiar) {
			if (campoPasswordActivo) {
				txtPassword.setText("");
			}
		}
		
		// Botón Ingresar
		if (e.getSource() == btnIngresar) {
			do_btnIngresar_actionPerformed(e);
		}
	}
	
	/**
	 * Agrega un número a contra
	 */
	private void agregarNumero(String numero) {
		if (campoPasswordActivo) {
			String actual = new String(txtPassword.getPassword());
			txtPassword.setText(actual + numero);
		} 
	}
	
	/**
	 * Método para validar login
	 * Clave Administrador: 1234
	 * Clave Trabajador: 2025
	 */
	protected void do_btnIngresar_actionPerformed(ActionEvent e) {
		String password = new String(txtPassword.getPassword()).trim();
		
		// Validar campo vacío
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
				"Debe ingresar una contraseña", 
				"Campo vacío", JOptionPane.WARNING_MESSAGE);
			txtPassword.requestFocus();
			return;
		}
		
		String tipoUsuario = null;
		
		if (password.equals("1234")) {
			// ADMINISTRADOR
			tipoUsuario = "ADMINISTRADOR";
		} else if (password.equals("2025")) {
			// TRABAJADOR
			tipoUsuario = "TRABAJADOR";
		} else {
			// CONTRASEÑA INCORRECTA
			JOptionPane.showMessageDialog(this, 
				"Contraseña incorrecta\n\n" +
				"Claves válidas:\n" +
				"• Administrador: 1***\n" +
				"• Trabajador: 2***", 
				"Error de autenticación", JOptionPane.ERROR_MESSAGE);
			txtPassword.setText("");
			txtPassword.requestFocus();
			return;
		}
		
		// Abrir ventana principal con el rol correspondiente
		VentanaMenu menu = new VentanaMenu(tipoUsuario);
		menu.setVisible(true);
		
		// Cerrar ventana de login
		this.dispose();
	}
}