import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Simulacion {
  
  public static void main(String[] args) throws IOException {

    /**
     * Se inicializan los servicios.
     */

    Memeflix memeflix = new Memeflix();
    Momazon momazon = new Momazon();
    Spootify spootify = new Spootify();
    Thisney thisney = new Thisney();
    HVO hvo = new HVO();

    /**
     * Se colocan los tipos de suscripciones como constantes.
     */

    final String MEMEFLIX_UN_DISPOSITIVO = "Sucripcion de Memeflix para un dispositivo";
    final String MEMEFLIX_DOS_DISPOSITIVOS = "Sucripcion de Memeflix para dos dispositivos";
    final String MEMEFLIX_CUATRO_DISPOSITIVOS = "Sucripcion de Memeflix para cuatro dispositivos";

    /**
     * Se inicializan los suscriptores con los datos dados:
     * 
     * Alicia inicia la simulacion con 15000.
     * Bob inicia la simulacion con 2400.
     * Cesar inicia la simulacion con 5000.
     * Diego inicia la simulacion con 9000.
     * Erika inicia la simulacion con 10000.
     * Fausto inicia la simulacion con 5000.
     */
    
    Cliente alicia = new Cliente("Alicia", 15000);
    Cliente bob = new Cliente("Bob", 2400);
    Cliente cesar = new Cliente("Cesar", 5000);
    Cliente erika = new Cliente("Erika", 10000);
    Cliente fausto = new Cliente("Fausto", 5000);

    /**
     *  Primer mes.
     * 
     * Alicia se suscribe a todos los servicios.
     * 
     */

    memeflix.registrar(alicia,MEMEFLIX_CUATRO_DISPOSITIVOS);
    memeflix.registrar(alicia,MEMEFLIX_CUATRO_DISPOSITIVOS);
    memeflix.registrar(bob, MEMEFLIX_CUATRO_DISPOSITIVOS);
    

    /**
     * Se generan los registros de los clientes.
     */

    alicia.generarRegistro();
    bob.generarRegistro();
  }

}
