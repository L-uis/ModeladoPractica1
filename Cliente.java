
import java.io.IOException;
import java.util.LinkedList;

/**
 * Clase que simula un cliente.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class Cliente implements Observador{

  private String nombre;

  private Banco banco;

  private LinkedList<ServicioStreaming> servicios;

  private String recomendacion;

  private Registro registro;

  /**
   * Constructor de la clase suscriptor.
   * 
   * @param nombre El nombre del suscriptor.
   * @param saldo El saldo del suscriptor.
   */
  public Cliente(String nombre, double saldo){

    this.nombre = nombre;

    this.servicios = new LinkedList<ServicioStreaming>();
    
    this.registro = new Registro(nombre);

    this.banco = new Banco(this, saldo);

  }

  /**
   * Metodo que suscribe al suscriptor a un servicio de streaming.
   * 
   * @param servicio El servicio al que se desea suscribir.
   */
  public void agregarSuscripcion(ServicioStreaming servicio, String tipoDeSuscripcion){

    servicio.registrar(this , tipoDeSuscripcion);

    servicios.add(servicio);
    
  }

  /**
   * Metodo que desuscribe al suscriptor de un servicio de streaming.
   * 
   * @param servicio El servicio al que el cliente se desuscrivira.
   */
  public void eliminarSuscripcion(ServicioStreaming servicio){

    servicio.remover(this);

    servicios.remove(servicio);
  
  }

  /**
   * Metodo que devuelve el nombre del cliente.
   * 
   * @return El nombre del cliente.
   */
  public String getNombre(){
  
    return this.nombre;
  
  }

  /**
   * Metodo que devuelve el saldo de la cuenta de banco del cliente.
   * 
   * @return El saldo de la cuenta.
   */
  public double getSaldo(){

    double saldo = banco.getSaldo();

    return saldo;

  }

  /**
   * Metodo que simula una transaccion.
   * 
   * @param pago Cantidad de dinero que se descontara.
   */
  public void descontarSaldo(double pago){

    banco.hacerPago(pago);
  
  }

  /**
   * Metodo que anade una cadena al registro del cliente.
   * 
   * @param cadena La caedna que sera agregada.
   */
  public void anadirRegistro(String cadena){

    registro.anadirRegistro(cadena);
  
  }

  /**
   * Metodo que genera un archivo TXT con el nopmbre del cliente,
   * este archivo contiene el registro de todas las acciones del
   * cliente con respecto a los servicios de streaming.
   * 
   */
  public void generarRegistro(){

    try {

      registro.escribirTXT();

    } catch (IOException e) {

      System.out.println("Error: " + e);

    }

  }

  /**
   * Metodo que desuscrive al cliente de todas sus suscripciones activas.
   * 
   */
  public void eliminarTodasLasSuscripciones() {

    for (ServicioStreaming servicioStreaming : servicios) {

      servicioStreaming.remover(this);
      
    }

    servicios = new LinkedList<ServicioStreaming>();
    
  }

  /**
   * Metodo que cambia el tipo de suscripcion del cliente en un servicio de streaming.
   * 
   * @param servicio Servicio donde se canbiara el tipo de suscripcion.
   * @param tipoDeSuscripcion Cadena con el nuevo tipo de suscripcion del cliente.
   */
  public void cambiarSuscripcion(ServicioStreaming servicio, String tipoDeSuscripcion) {

    servicio.cambiarSuscripcion(this, tipoDeSuscripcion);

  }

  @Override
  public void actualizar(ServicioStreaming servicioActual) throws IOException {
 
    this.recomendacion = servicioActual.getRecomendacion();
    
    verRecomendacion();
  
  }

  public void verRecomendacion() throws IOException{

    registro.anadirRegistro(this.recomendacion);
  
  }

}
