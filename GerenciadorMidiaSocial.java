public class GerenciadorMidiaSocial {
    private AdapterRedes adaptador;

    public GerenciadorMidiaSocial(AdapterRedes adaptador) {
        this.adaptador = adaptador;
    }

    public void autenticar() {
        adaptador.autenticar();
    }

    public void publicar(Conteudo conteudo) {
        adaptador.publicar(conteudo);
     }

     public void alterarAdaptador(AdapterRedes novoAdaptador) {
         this.adaptador = novoAdaptador;
     }
}
