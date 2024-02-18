import java.util.LinkedList;

public class Spootify implements ServicioStreaming{
  
  private String nombre;

  private LinkedList<Suscriptor> suscriptores;

  private LinkedList<String> recomendaciones;

  private CobroMemeflix cobro;

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
