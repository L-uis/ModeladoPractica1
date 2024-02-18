
public interface ServicioStreaming extends Sujeto{
  void registrar(Suscriptor s);
  void remover(Suscriptor s);
  void notificar();
  String cobro(Cobro c);
}