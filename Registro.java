import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que permite mantener un registro y escribirlo en un archivo txt.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class Registro {

  private String archivo;
  
  private StringBuilder stringBuilder;


  public Registro(String nombre){

    archivo = nombre+".txt";
    
    stringBuilder = new StringBuilder();

  }

  public void anadirRegistro(String cadena){

    stringBuilder.append(cadena + "\n");

  }

  /**
   * Metodo que escribe una cadena en el archivo txt.
   * 
   * @param cadena Cadena que se escribe en el archivo.
   * @throws IOException
   */
  public void escribirTXT() throws IOException{


    FileWriter archivo = null;
    PrintWriter escritor = null;

    try {

      archivo = new FileWriter(this.archivo);
      escritor = new PrintWriter(archivo);

      escritor.println(stringBuilder.toString());

    } catch (Exception e) {
    
      System.out.println("Error: " + e);
    
    } finally {

      archivo.close();
    
    }

  }

}
