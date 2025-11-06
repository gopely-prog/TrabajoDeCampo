package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionBD{

    private static final String URL = "jdbc:mysql://localhost:3306/polleria_excelencia";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "1234"; // ⚠️ CAMBIA ESTO
    
    private static Connection conexion = null;
    
    public static Connection getConexion() {
        try {
        	
            if (conexion == null || conexion.isClosed()) {
            	
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
                
            }
            
        } catch (ClassNotFoundException e) {
            String mensajeError = "Error crítico: No se encontró el driver de MySQL.\n"
                                + "Asegúrate de tener la librería (JAR) de MySQL agregada al proyecto.\n\n"
                                + "El programa se cerrará.";
            JOptionPane.showMessageDialog(null, mensajeError, "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
            System.exit(1); 
            
        } catch (SQLException e) {
        	String mensajeError = "Error al conectar con la base de datos.\n\n"
                    + "Verifica que:\n"
                    + "  - El servicio de MySQL esté corriendo.\n"
                    + "  - El usuario (" + USUARIO + ") y la contraseña sean correctos.\n"
                    + "  - La base de datos 'polleria_excelencia' exista.\n\n"
                    + "El programa se cerrará.";
        	JOptionPane.showMessageDialog(null, mensajeError, "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        	System.exit(1);
        }
        
        return conexion;
    }
    
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✓ Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("✗ Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
    
    /**
     * Método para probar la conexión
     */
    public static void main(String[] args) {
        System.out.println("=== PROBANDO CONEXIÓN A LA BASE DE DATOS ===\n");
        
        Connection conn = getConexion();
        
        if (conn != null) {
            System.out.println("\n¡CONEXIÓN EXITOSA! ✓");
            System.out.println("Conectado a: " + URL);
            cerrarConexion();
        } else {
            System.out.println("\n¡CONEXIÓN FALLIDA! ✗");
        }
    }
}