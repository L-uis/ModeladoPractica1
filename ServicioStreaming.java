
/**
 * Interface que define los métodos que debe tener un servicio de streaming que quiere 
 * cobrar y recomendar a sus clientes.
 * Extiende la interfaz Sujeto para poder notificar a los clientes sobre los cambios en 
 * su estado.
 * Tiene métodos para generar un cobro, dar una recomendación, añadir una recomendación 
 * y cambiar el tipo de suscripción de un cliente.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public interface ServicioStreaming extends Sujeto{

  /**
   * Metodo que genera un cobro al cliente según el tipo de suscripción contratado.
   * 
   * @param cliente El cliente al que se le hara el cobro.
   * @return El monto a cobrar al cliente en forma de cadena.
   */
  String cobro(Cliente suscriptor);

  /**
   * Metodo que devuelve una recomendación de la lista de recomendaciones del servicio.
   * 
   * @return Una recomendación en forma de cadena.
   */
  String getRecomendacion();

  /**
   * Metodo que añade una recomendación a la lista de recomendaciones del servicio.
   * 
   * @param recomendacion La recomendación que sera añadida.
   */
  void anadirRecomendacion(String recomendacion);


  /**
     * Metodo que cambia el tipo de suscripción de un cliente a un servicio.
     * 
     * @param cliente El cliente que cambiara su tipo de suscripción.
     * @param tipoDeSuscripcion El nuevo tipo de suscripción que se quiere contratar.
     */
  void cambiarSuscripcion(Cliente cliente, String tipoDeSuscripcion);
  
}