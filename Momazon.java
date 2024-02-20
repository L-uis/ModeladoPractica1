import java.io.IOException;
import java.util.LinkedList;

public class Momazon implements ServicioStreaming{

  private String nombre;

  private LinkedList<Suscriptor> suscriptores;

  private LinkedList<String> recomendaciones;

  private CobroMomazon cobro;

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
    
    if (tipoSuscripcion.equals("Sucripcion normal de Momazon Prime Video")) {

      cobro = new MomazonNormal();

    }
    if (tipoSuscripcion.equals("Sucripcion premium de Momazon Prime Video")) {
      
      cobro = new MomazonPremium();

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
