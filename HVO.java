import java.io.IOException;
import java.util.LinkedList;

public class HVO implements ServicioStreaming{
  
  private String nombre;

  private LinkedList<Cliente> suscriptores;

  private LinkedList<String> recomendaciones;

  private CobroHVO cobro;

  @Override
  public void registrar(Cliente s, String tipoDeSuscripcion) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registrar'");
  }
  
  public void remover(Cliente s){

  }

  public void notificar(){

  }

  public void cobro(Cliente suscriptor){

  
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
