package clases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestorBoletas {
    // Información de la tienda
    private static final String NOMBRE_TIENDA = "POLLERIA EXCELENCIA";
    private static final String DIRECCION_TIENDA = "Av. Principal 123, Lima - Perú";
    private static final String TELEFONO_TIENDA = "+51 999 888 777";
    private static final String SUNAT_URL = "WWW.SUNAT.GOB.PE";
    
    // Ruta donde se guardarán los archivos
    private static final String RUTA_DOCUMENTOS = System.getProperty("user.home") + File.separator + "Documents" +
    File.separator + "Polleria_Excelencia_Ventas";
    
    private static void crearCarpetaDocumentos() {
        File carpeta = new File(RUTA_DOCUMENTOS);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
            System.out.println("✓ Carpeta creada: " + RUTA_DOCUMENTOS);
        }
    }
    public static String GenerarBoleta(int numeroBoleta, String cliente, ArrayList<DetalleVenta> productos, double subTotal, double igvMonto, double total) {
        try {
            crearCarpetaDocumentos();
            
            // Crear nombre del archivo
            String nombreArchivo = "BOLETA_" + String.format("%06d", numeroBoleta) + "_" + 
                                  ObtenerFechaFormato() + ".txt";
            String rutaCompleta = RUTA_DOCUMENTOS + File.separator + nombreArchivo;
            
            // Crear el escritor de archivo
            FileWriter fw = new FileWriter(rutaCompleta);
            
            // Encabezado
            fw.write("===============================================\n");
            fw.write("                POLLERIA EXCELENCIA\n");
            fw.write("===============================================\n");
            fw.write("           BOLETA DE VENTA ELECTRONICA\n");
            fw.write("===============================================\n\n");
            
            // Información de la tienda
            fw.write("Dirección: " + DIRECCION_TIENDA + "\n");
            fw.write("Teléfono: " + TELEFONO_TIENDA + "\n\n");
            
            // Datos del comprobante
            fw.write("-----------------------------------------------\n");
            fw.write("Nº BOLETA: " + String.format("%06d", numeroBoleta) + "\n");
            fw.write("Fecha: " + ObtenerFechaCompleta() + "\n");
            fw.write("Cliente: " + cliente + "\n");
            fw.write("-----------------------------------------------\n\n");
            
            // Encabezado de tabla
            fw.write("CANT.   PRODUCTO                   P.UNI.      TOTAL\n");
            fw.write("-----------------------------------------------\n");
            
            // Listar productos
            for (DetalleVenta dv : productos) {
                double subtotalProducto = dv.cantidad * dv.precioUnitario;
                fw.write(String.format("%-6d  %-28s S/. %-8.2f S/. %.2f\n", 
                    dv.cantidad, 
                    dv.descripcion, 
                    dv.precioUnitario, 
                    subtotalProducto));
            }
            
            fw.write("-----------------------------------------------\n\n");
            
            // Totales
            fw.write(String.format("SUBTOTAL                              S/. %.2f\n", subTotal));
            fw.write(String.format("IGV (18%%)                             S/. %.2f\n", igvMonto));
            fw.write("-----------------------------------------------\n");
            fw.write(String.format("TOTAL A PAGAR                         S/. %.2f\n", total));
            fw.write("===============================================\n\n");
            
            // Pie de página
            fw.write("Gracias por su compra!\n");
            fw.write("Vuelva pronto a POLLERIA EXCELENCIA\n\n");
            fw.write("Esta es una presentación impresa de la\n");
            fw.write("boleta electrónica generada por el sistema.\n");
            fw.write("Puede verificarla en: " + SUNAT_URL + "\n\n");
            fw.write("Generado: " + ObtenerFechaCompleta() + "\n");
            
            // Cerrar archivo
            fw.close();
            
            System.out.println("✓ Boleta guardada en: " + rutaCompleta);
            return rutaCompleta;
            
        } catch (IOException e) {
            System.out.println("✗ Error al generar boleta: " + e.getMessage());
            return null;
        }
    }
    public static String GenerarFactura(int numeroFactura, String rucCliente, String razonSocial, String domicilio,ArrayList<DetalleVenta> productos, double subTotal, double igvMonto, double total) {
        try {
            crearCarpetaDocumentos();
            
            // Crear nombre del archivo
            String nombreArchivo = "FACTURA_" + String.format("%08d", numeroFactura) + "_" + 
                                  ObtenerFechaFormato() + ".txt";
            String rutaCompleta = RUTA_DOCUMENTOS + File.separator + nombreArchivo;
            
            // Crear el escritor de archivo
            FileWriter fw = new FileWriter(rutaCompleta);
            
            // Encabezado
            fw.write("===============================================\n");
            fw.write("                POLLERIA EXCELENCIA\n");
            fw.write("===============================================\n");
            fw.write("             FACTURA ELECTRONICA\n");
            fw.write("===============================================\n\n");
            
            // Datos del EMISOR (tienda)
            fw.write("EMISOR:\n");
            fw.write("Empresa: " + NOMBRE_TIENDA + "\n");
            fw.write("Dirección: " + DIRECCION_TIENDA + "\n");
            fw.write("Teléfono: " + TELEFONO_TIENDA + "\n\n");
            
            // Datos del CLIENTE (receptor)
            fw.write("CLIENTE (RECEPTOR):\n");
            fw.write("RUC: " + rucCliente + "\n");
            fw.write("Razón Social: " + razonSocial + "\n");
            fw.write("Domicilio: " + domicilio + "\n\n");
            
            // Datos del comprobante
            fw.write("-----------------------------------------------\n");
            fw.write("Nº FACTURA: " + String.format("%08d", numeroFactura) + "\n");
            fw.write("Fecha de Emisión: " + ObtenerFechaCompleta() + "\n");
            fw.write("Moneda: SOLES (S/.)\n");
            fw.write("-----------------------------------------------\n\n");
            
            // Encabezado de tabla
            fw.write("CANT.   PRODUCTO                   P.UNI.      TOTAL\n");
            fw.write("-----------------------------------------------\n");
            
            // Listar productos
            for (DetalleVenta dv : productos) {
                double subtotalProducto = dv.cantidad * dv.precioUnitario;
                fw.write(String.format("%-6d  %-28s S/. %-8.2f S/. %.2f\n", 
                    dv.cantidad, 
                    dv.descripcion, 
                    dv.precioUnitario, 
                    subtotalProducto));
            }
            
            fw.write("-----------------------------------------------\n\n");
            
            // Totales
            fw.write(String.format("SUBTOTAL (Gravada)                    S/. %.2f\n", subTotal));
            fw.write(String.format("IGV (18%%)                             S/. %.2f\n", igvMonto));
            fw.write("-----------------------------------------------\n");
            fw.write(String.format("TOTAL A PAGAR                         S/. %.2f\n", total));
            fw.write("===============================================\n\n");
            
            // Pie de página
            fw.write("Condiciones de pago: Al contado\n");
            fw.write("Gracias por confiar en POLLERIA EXCELENCIA!\n\n");
            fw.write("Esta es una presentación impresa de la\n");
            fw.write("factura electrónica generada por el sistema.\n");
            fw.write("Puede verificarla en: " + SUNAT_URL + "\n\n");
            fw.write("Generado: " + ObtenerFechaCompleta() + "\n");
            
            // Cerrar archivo
            fw.close();
            
            System.out.println("✓ Factura guardada en: " + rutaCompleta);
            return rutaCompleta;
            
        } catch (IOException e) {
            System.out.println("✗ Error al generar factura: " + e.getMessage());
            return null;
        }
    }
    public static void abrirArchivo(String rutaArchivo) {
        try {
            if (rutaArchivo == null || rutaArchivo.isEmpty()) {
                System.out.println("✗ Ruta de archivo inválida");
                return;
            }
            
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                System.out.println("✗ El archivo no existe: " + rutaArchivo);
                return;
            }
            
            // Detectar el sistema operativo
            String osName = System.getProperty("os.name").toLowerCase();
            
            if (osName.contains("win")) {
                // Windows
                Runtime.getRuntime().exec("notepad " + rutaArchivo);
            } else if (osName.contains("mac")) {
                // macOS
                Runtime.getRuntime().exec(new String[]{"open", rutaArchivo});
            } else if (osName.contains("nix") || osName.contains("nux")) {
                // Linux
                Runtime.getRuntime().exec(new String[]{"xdg-open", rutaArchivo});
            }
            
            System.out.println("✓ Archivo abierto: " + rutaArchivo);
            
        } catch (IOException e) {
            System.out.println("✗ Error al abrir archivo: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene la fecha actual en formato: dd/MM/yyyy HH:mm:ss
     */
    private static String ObtenerFechaCompleta() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ahora.format(formato);
    }
    
    /**
     * Obtiene la fecha actual en formato: ddMMyyyyHHmmss (para nombres de archivo)
     */
    private static String ObtenerFechaFormato() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        return ahora.format(formato);
    }
    
    public static String obtenerRutaDocumentos() {
        return RUTA_DOCUMENTOS;
    }
    
    public static class DetalleVenta {
        public int cantidad;
        public String descripcion;
        public double precioUnitario;
        
        public DetalleVenta(int cantidad, String descripcion, double precioUnitario) {
            this.cantidad = cantidad;
            this.descripcion = descripcion;
            this.precioUnitario = precioUnitario;
        }
    }
}