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
	private JLabel lblIntentos;
	// Variables para control de intentos
	private int intentosRestantes = 3;
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
		setBounds(100, 100, 700, 530);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(255, 255, 255));
		panelLogo.setBounds(10, 11, 300, 469);
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
		
		JLabel lblSeguridad = new JLabel("🔒 Sistema de Acceso Seguro");
		lblSeguridad.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeguridad.setFont(new Font("Arial", Font.BOLD, 12));
		lblSeguridad.setForeground(new Color(0, 100, 0));
		lblSeguridad.setBounds(10, 433, 280, 20);
		panelLogo.add(lblSeguridad);
		
		JPanel panelAcceso = new JPanel();
		panelAcceso.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
			new Color(255, 255, 255), new Color(160, 160, 160)), 
			"CONTROL DE ACCESO", TitledBorder.CENTER, TitledBorder.TOP, 
			new Font("Arial", Font.BOLD, 16), Color.BLACK));
		panelAcceso.setBackground(new Color(205, 232, 254));
		panelAcceso.setBounds(320, 11, 354, 469);
		contentPane.add(panelAcceso);
		panelAcceso.setLayout(null);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblPassword.setBounds(20, 38, 100, 25);
		panelAcceso.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword.setBounds(20, 68, 314, 30);
		panelAcceso.add(txtPassword);
		
		lblIntentos = new JLabel("Intentos restantes: 3");
		lblIntentos.setFont(new Font("Arial", Font.BOLD, 12));
		lblIntentos.setForeground(new Color(0, 100, 0));
		lblIntentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntentos.setBounds(20, 103, 314, 20);
		panelAcceso.add(lblIntentos);
		
		txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				campoPasswordActivo = true;
			}
		});
		
		JPanel panelTeclado = new JPanel();
		panelTeclado.setBorder(new TitledBorder(null, "Teclado Numérico", 
			TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTeclado.setBounds(20, 133, 314, 259);
		panelAcceso.add(panelTeclado);
		panelTeclado.setLayout(null);
		
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
		
		btnBorrar = new JButton("←");
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 16));
		btnBorrar.setBackground(new Color(255, 200, 200));
		btnBorrar.setBounds(250, 25, 54, 50);
		btnBorrar.addActionListener(this);
		panelTeclado.add(btnBorrar);
		
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
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 16));
		btnIngresar.setBackground(new Color(144, 238, 144));
		btnIngresar.setBounds(20, 402, 314, 40);
		btnIngresar.addActionListener(this);
		panelAcceso.add(btnIngresar);
	}
	// Agrega un número al campo de contraseña activo
	public void actionPerformed(ActionEvent e) {
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
		
		if (e.getSource() == btnBorrar) {
			if (campoPasswordActivo) {
				String actual = new String(txtPassword.getPassword());
				if (actual.length() > 0) {
					txtPassword.setText(actual.substring(0, actual.length() - 1));
				}
			}
		}
		
		if (e.getSource() == btnLimpiar) {
			if (campoPasswordActivo) {
				txtPassword.setText("");
			}
		}
		
		if (e.getSource() == btnIngresar) {
			do_btnIngresar_actionPerformed(e);
		}
	}
	
	private void agregarNumero(String numero) {
		if (campoPasswordActivo) {
			String actual = new String(txtPassword.getPassword());
			txtPassword.setText(actual + numero);
		}
	}
	// Actualiza el label de intentos con color
	private void actualizarLabelIntentos() {
		lblIntentos.setText("Intentos restantes: " + intentosRestantes);
		
		if (intentosRestantes == 3) {
			lblIntentos.setForeground(new Color(0, 100, 0)); 
		} else if (intentosRestantes == 2) {
			lblIntentos.setForeground(new Color(255, 140, 0)); 
		} else if (intentosRestantes == 1) {
			lblIntentos.setForeground(new Color(255, 0, 0)); 
		}
	}
	// Bloquea todos los controles y cierra la aplicación
	private void bloquearSistema() {
		btn0.setEnabled(false);
		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn4.setEnabled(false);
		btn5.setEnabled(false);
		btn6.setEnabled(false);
		btn7.setEnabled(false);
		btn8.setEnabled(false);
		btn9.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnLimpiar.setEnabled(false);
		btnIngresar.setEnabled(false);
		txtPassword.setEnabled(false);
		
		btn0.setBackground(Color.LIGHT_GRAY);
		btn1.setBackground(Color.LIGHT_GRAY);
		btn2.setBackground(Color.LIGHT_GRAY);
		btn3.setBackground(Color.LIGHT_GRAY);
		btn4.setBackground(Color.LIGHT_GRAY);
		btn5.setBackground(Color.LIGHT_GRAY);
		btn6.setBackground(Color.LIGHT_GRAY);
		btn7.setBackground(Color.LIGHT_GRAY);
		btn8.setBackground(Color.LIGHT_GRAY);
		btn9.setBackground(Color.LIGHT_GRAY);
		btnBorrar.setBackground(Color.LIGHT_GRAY);
		btnLimpiar.setBackground(Color.LIGHT_GRAY);
		btnIngresar.setBackground(Color.LIGHT_GRAY);
		
		JOptionPane.showMessageDialog(this,
			"🔒 ACCESO BLOQUEADO\n\n" +
			"Se han agotado los 3 intentos de acceso.\n\n" +
			"El sistema se cerrará por seguridad.\n\n" +
			"Contacte al administrador del sistema si necesita acceso.",
			"Sistema Bloqueado",
			JOptionPane.ERROR_MESSAGE);
		
		System.out.println("Sistema bloqueado por múltiples intentos fallidos");
		System.exit(0);
	}
	// Valida las credenciales y abre el menú correspondiente al rol
	protected void do_btnIngresar_actionPerformed(ActionEvent e) {
		String password = new String(txtPassword.getPassword()).trim();
		
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
				"⚠️ Debe ingresar una contraseña", 
				"Campo vacío", JOptionPane.WARNING_MESSAGE);
			txtPassword.requestFocus();
			return;
		}
		
		String tipoUsuario = null;
		
		if (password.equals("1234")) {
			
			tipoUsuario = "ADMINISTRADOR";
			
		} else if (password.equals("2025")) {
			
			tipoUsuario = "TRABAJADOR";
			
		} else {
			intentosRestantes--;
			actualizarLabelIntentos();
			
			if (intentosRestantes > 0) {
				String mensajeIntentos = intentosRestantes == 1 ? 
					"\n\nÚLTIMO INTENTO\nEl sistema se bloqueará si falla nuevamente." :
					"\n\nIntentos restantes: " + intentosRestantes;
				
				JOptionPane.showMessageDialog(this, 
					"CONTRASEÑA INCORRECTA\n\n" +
					"La clave ingresada no está asignada a ningún empleado.\n" +
					"Verifique e intente nuevamente." +
					mensajeIntentos,
					"Acceso Denegado", 
					JOptionPane.ERROR_MESSAGE);
				
				txtPassword.setText("");
				txtPassword.requestFocus();
				
			} else {
				bloquearSistema();
			}
			
			return;
		}
		
		System.out.println("Acceso autorizado: " + tipoUsuario);
		
		JOptionPane.showMessageDialog(this,
			"✅ ACCESO AUTORIZADO\n\n" +
			"Bienvenido al sistema\n" +
			"Rol: " + tipoUsuario,
			"Acceso Exitoso",
			JOptionPane.INFORMATION_MESSAGE);
		
		VentanaMenu menu = new VentanaMenu(tipoUsuario);
		menu.setVisible(true);
		
		this.dispose();
	}
}