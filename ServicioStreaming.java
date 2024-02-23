
/**
 * Interface que define los metodos que debe tener un servicio de streaming que quiere 
 * cobrar y recomendar a sus clientes.
 * Extiende la interfaz Sujeto para poder notificar a los clientes sobre los cambios en 
 * su estado.
 * Tiene metodos para generar un cobro, dar una recomendacion, añadir una recomendacion 
 * y cambiar el tipo de suscripcion de un cliente.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public interface ServicioStreaming extends Sujeto{

  /**
   * Metodo que genera un cobro al cliente segun el tipo de suscripcion contratado.
   * 
   * @param cliente El cliente al que se le hara el cobro.
   * @return El monto a cobrar al cliente en forma de cadena.
   */
  String cobro(Cliente suscriptor);

  /**
   * Metodo que devuelve una recomendacion de la lista de recomendaciones del servicio.
   * 
   * @return Una recomendacion en forma de cadena.
   */
  String getRecomendacion();

  /**
   * Metodo que añade una recomendacion a la lista de recomendaciones del servicio.
   * 
   * @param recomendacion La recomendacion que sera añadida.
   */
  void anadirRecomendacion(String recomendacion);


  /**
     * Metodo que cambia el tipo de suscripcion de un cliente a un servicio.
     * 
     * @param cliente El cliente que cambiara su tipo de suscripcion.
     * @param tipoDeSuscripcion El nuevo tipo de suscripcion que se quiere contratar.
     */
  void cambiarSuscripcion(Cliente cliente, String tipoDeSuscripcion);
  
}