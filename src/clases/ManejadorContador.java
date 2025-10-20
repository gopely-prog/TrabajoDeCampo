package clases;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManejadorContador {
    
    private static final String RUTA_DOCUMENTOS = System.getProperty("user.home") + 
                                                   File.separator + "Documents" + 
                                                   File.separator + "Polleria_Excelencia_Ventas";
    private static final String ARCHIVO_CONTADOR = RUTA_DOCUMENTOS + File.separator + "contador.txt";
    
    private static int contadorActual = 1;

    public static void inicializarContador() {
        crearCarpetaDocumentos();
        
        File archivoContador = new File(ARCHIVO_CONTADOR);
        
        if (archivoContador.exists()) {
            // Leer el contador del archivo
            try (FileReader fr = new FileReader(archivoContador)) {
                StringBuilder sb = new StringBuilder();
                int c;
                while ((c = fr.read()) != -1) {
                    sb.append((char) c);
                }
                
                String contenido = sb.toString().trim();
                if (!contenido.isEmpty()) {
                    contadorActual = Integer.parseInt(contenido);
                    System.out.println("✓ Contador cargado: " + contadorActual);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("✗ Error al leer contador: " + e.getMessage());
                contadorActual = 1;
            }
        } else {
            // Crear archivo nuevo con contador en 1
            guardarContador(1);
            System.out.println("✓ Archivo contador creado");
        }
    }
    
    /**
     * Obtiene el siguiente número secuencial y lo guarda
     */
    public static int obtenerSiguienteNumero() {
        int numeroActual = contadorActual;
        contadorActual++;
        guardarContador(contadorActual);
        return numeroActual;
    }
    
    /**
     * Guarda el contador en el archivo
     */
    private static void guardarContador(int numero) {
        try (FileWriter fw = new FileWriter(ARCHIVO_CONTADOR)) {
            fw.write(String.valueOf(numero));
            System.out.println("✓ Contador guardado: " + numero);
        } catch (IOException e) {
            System.out.println("✗ Error al guardar contador: " + e.getMessage());
        }
    }
    
    /**
     * Crea la carpeta de documentos si no existe
     */
    private static void crearCarpetaDocumentos() {
        File carpeta = new File(RUTA_DOCUMENTOS);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
            System.out.println("✓ Carpeta creada: " + RUTA_DOCUMENTOS);
        }
    }
    
    public static int obtenerContadorActual() {
        return contadorActual;
    }
    
    public static void reiniciarContador() {
        contadorActual = 1;
        guardarContador(1);
        System.out.println("✓ Contador reiniciado a 1");
    }
}