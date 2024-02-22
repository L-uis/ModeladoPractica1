
/**
 * Interface que nos permite crear servicios de streaming.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface ServicioStreaming extends Sujeto{

  /**
   * Metodo que genera un cobro al suscriptor.
   * 
   * @param suscriptor El suscriptor al que se le hara el cobro.
   */
  String cobro(Cliente suscriptor);

  /**
   * Metodo que da una recomendacion de la lista de recomendaciones del servicio.
   * 
   * @return Una recomendacion en forma de cadena.
   */
  String getRecomendacion();

  /**
   * Metodo que añade una recomendacion al servicio.
   * 
   * @param recomendacion La recomendacion que sera anadida.
   */
  void anadirRecomendacion(String recomendacion);


  /**
   * Metodo que cambia el tipo de suscripcion de un cliente.
   * 
   * @param cliente El cliente que cambiara su tipo de suscripcion.
   * @param tipoDeSuscripcion El tipo de suscripcion nuevo.
   */
  void cambiarSuscripcion(Cliente cliente, String tipoDeSuscripcion);
  
}