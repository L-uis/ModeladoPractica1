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


  /**
   * Contructor de la clase registro, este crea un nombre de un archivo punto TXT
   * con la cadena nombre.
   * 
   * @param nombre Nombre que tendra el archivo.
   */
  public Registro(String nombre){

    archivo = nombre+".txt";
    
    stringBuilder = new StringBuilder();

  }

  /**
   * Metodo que añade una cadena a la variable stringBuilder.
   * 
   * @param cadena Cadena que sera agregada a stringBuilder.
   */
  public void anadirRegistro(String cadena){

    stringBuilder.append(cadena + "\n");

  }

  /**
   * Metodo que escribe todo lo que hay en la variable stringBuilder
   * en el archivo TXT que llevara el nombre de la cadena archivo.
   * 
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
