
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
   * Metodo que generara un cobro al suscriptor.
   * 
   * @param c cobro que se hara.
   * @return estado del cobro.
   */
  String cobro(Cobro c);

  String getRecomendacion();

  String getNombre();
  
}