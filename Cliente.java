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

  private double saldo;

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

    this.saldo = saldo;
    
    this.registro = new Registro(nombre);
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
   * @param servicio
   */
  public void eliminarSuscripcion(ServicioStreaming servicio){

    servicio.remover(this);
  
  }

  public String getNombre(){
  
    return this.nombre;
  
  }

  public double getSaldo(){

    return this.saldo;

  }

  /**
   * Metodo que simula una transaccion.
   * 
   * @param pago Cantidad de dinero que se descontara.
   */
  public void descontarSaldo(double pago){

    this.saldo -= pago;
  
  }

  public void anadirRegistro(String cadena){

    registro.anadirRegistro(cadena);
  
  }

  public void generarRegistro(){
    try {
      registro.escribirTXT();
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
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
