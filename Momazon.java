import java.util.LinkedList;

public class Momazon implements ServicioStreaming{

  private String nombre;

  private LinkedList<Suscriptor> suscriptores;

  private LinkedList<String> recomendaciones;

  private CobroMomazon cobro;

  public void registrar(Suscriptor s){

  }

  public void remover(Suscriptor s){

  }

  public void notificar(){

  }

  public String cobro(Cobro c){

    return "";
  }
}
