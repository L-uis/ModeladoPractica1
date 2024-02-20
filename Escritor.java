import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que permite escribir en un archivo txt.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class Escritor {

  private static final String ARCHIVO = "Simulacion.txt";

  /**
   * Metodo que escribe una cadena en el archivo txt.
   * 
   * @param cadena Cadena que se escribe en el archivo.
   * @throws IOException
   */
  public static void escribirTXT(String cadena) throws IOException{

    FileWriter archivo = null;
    PrintWriter escritor = null;

    try {

      archivo = new FileWriter(ARCHIVO);
      escritor = new PrintWriter(archivo);

      escritor.println(cadena);

    } catch (Exception e) {
    
      System.out.println("Error: " + e);
    
    } finally {

      archivo.close();
    
    }


  }
}
