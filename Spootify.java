import java.io.IOException;
import java.util.LinkedList;

public class Spootify implements ServicioStreaming{
  
  private String nombre;

  private LinkedList<Suscriptor> suscriptores;

  private LinkedList<String> recomendaciones;

  private CobroSpootify cobro;

  @Override
  public void registrar(Suscriptor s, String tipoDeSuscripcion) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registrar'");
  }

  public void remover(Suscriptor s){

  }

  public void notificar(){

  }

  @Override
  public void cobro(Suscriptor suscriptor) {

    String tipoSuscripcion = suscriptor.getTipoDeSuscripcionHVO();
    
    if (tipoSuscripcion.equals("Sucripcion gratis de Spootify")) {

      cobro = new SpootifyGratis();

    }
    if (tipoSuscripcion.equals("Sucripcion premium de Spootify")) {
      
      cobro = new SpootifyPremium();

    }

    String estadoDelCobro = cobro.cobro(suscriptor);
    String rechazado = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

    if (estadoDelCobro.equals(rechazado)) {

      try {

        Escritor.escribirTXT(estadoDelCobro);

      } catch (IOException e) {

        System.out.println("Error: " + e);

      }

      this.remover(suscriptor);

    } else {

      try {

        Escritor.escribirTXT(estadoDelCobro);

      } catch (IOException e) {

        System.out.println("Error: " + e);

      }
      
    }
  }

  @Override
  public String getRecomendacion() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecomendacion'");
  }

  @Override
  public String getNombre() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
  }
}
