import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que permite mantener un registro y escribirlo en un archivo txt.
 * Tiene atributos para el nombre del archivo, el contenido del registro 
 * y metodos para añadir y escribir el registro.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public class Registro {

  private String archivo;
  
  private StringBuilder stringBuilder;

  /**
   * Constructor de la clase Registro que recibe el nombre del archivo txt.
   * Inicializa los atributos con el nombre dado y un StringBuilder vacio.
   * 
   * @param nombre El nombre del archivo txt sin la extension.
   */
  public Registro(String nombre){

    archivo = nombre+".txt";
    
    stringBuilder = new StringBuilder();

  }

  /**
   * Metodo que añade una cadena al registro.
   * Usa el metodo append del StringBuilder para añadir la cadena y un 
   * salto de linea al final.
   * 
   * @param cadena La cadena que se quiere añadir al registro.
   */
  public void anadirRegistro(String cadena){

    stringBuilder.append(cadena + "\n");

  }

  /**
   * Metodo que escribe el contenido del registro en el archivo txt.
   * Usa las clases FileWriter y PrintWriter para crear el archivo y 
   * escribir el registro.
   * Captura y maneja las posibles excepciones que se puedan producir.
   * Cierra el archivo al finalizar.
   * 
   * @throws IOException Si ocurre algun error de entrada o salida al 
   * crear o escribir el archivo.
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
