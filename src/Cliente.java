
import java.io.IOException;
import java.util.LinkedList;
/**
 * Clase que simula un cliente que se suscribe a diferentes servicios de streaming y 
 * recibe notificaciones y cobros de los mismos.
 * Implementa la interfaz Observador para poder actualizar su estado segun los cambios 
 * de los servicios a los que esta suscrito.
 * Tiene atributos como el nombre, el saldo, la lista de servicios, la recomendacion y 
 * el registro.
 * Tiene metodos para agregar o eliminar suscripciones, cambiar el tipo de suscripcion, 
 * descontar el saldo, ver la recomendacion, generar el registro y actualizar el estado.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 * @version Febrero 2024
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
   * Metodo que suscribe al cliente a un servicio de streaming.
   * 
   * @param servicio El servicio al que se desea suscribir.
   * @param tipoDeSuscripcion El tipo de suscripcion que se desea contratar.
   */
  public void agregarSuscripcion(ServicioStreaming servicio, String tipoDeSuscripcion){

    servicio.registrar(this , tipoDeSuscripcion);

    servicios.add(servicio);
    
  }

  /**
   * Metodo que desuscribe al suscriptor de un servicio de streaming.
   * 
   * @param servicio El servicio del que se desea desuscribir.
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
   * Metodo que devuelve el saldo del cliente.
   * 
   * @return El saldo del cliente.
   */
  public double getSaldo(){

    double saldo = banco.getSaldo();

    return saldo;

  }

  /**
   * Metodo que simula una transaccion.
   * Descuenta el pago del saldo del cliente.
   * 
   * @param pago Cantidad de dinero que se descontara.
   */
  public void descontarSaldo(double pago){

    banco.hacerPago(pago);
  
  }

  /**
   * Metodo que añade una cadena al registro del cliente.
   * 
   * @param cadena La cadena que se añade al registro.
   */
  public void anadirRegistro(String cadena){

    registro.anadirRegistro(cadena);
  
  }

  /**
   * Metodo que genera el archivo txt con el registro del cliente.
   * 
   * @throws IOException Si ocurre un error al escribir el archivo.
   */
  public void generarRegistro(){

    try {

      registro.escribirTXT();

    } catch (IOException e) {

      System.out.println("Error: " + e);

    }
  }

  /**
   * Metodo que actualiza el estado del cliente segun el servicio que le notifica.
   * Asigna la recomendacion del servicio al atributo recomendacion y la muestra.
   * 
   * @param servicioActual El servicio que le notifica al cliente.
   */
  @Override
  public void actualizar(ServicioStreaming servicioActual){
 
    this.recomendacion = servicioActual.getRecomendacion();
    
    verRecomendacion();
  
  }

  /**
   * Metodo que muestra la recomendacion que el cliente recibe de un servicio.
   * Añade la recomendacion al registro del cliente.
   * 
   */
  public void verRecomendacion(){

    registro.anadirRegistro(this.recomendacion);
  
  }

  /**
   * Metodo que elimina todas las suscripciones del cliente.
   * Remueve al cliente de la lista de observadores de cada servicio.
   * Vacia la lista de servicios del cliente.
   */
  public void eliminarTodasLasSuscripciones() {

    for (ServicioStreaming servicioStreaming : servicios) {

      servicioStreaming.remover(this);
      
    }

    servicios = new LinkedList<ServicioStreaming>();
    
  }

  /**
   * Metodo que cambia el tipo de suscripcion del cliente a un servicio.
   * 
   * @param servicio El servicio al que se quiere cambiar el tipo de suscripcion.
   * @param tipoDeSuscripcion El nuevo tipo de suscripcion que se quiere contratar.
   */
  public void cambiarSuscripcion(ServicioStreaming servicio, String tipoDeSuscripcion) {

    servicio.cambiarSuscripcion(this, tipoDeSuscripcion);

  }

}
