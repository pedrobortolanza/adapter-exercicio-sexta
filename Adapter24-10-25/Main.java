public class Main {
    public static void main(String[] args) {
        Conteudo conteudo = new Conteudo("Teste supremo!", "Realizando um teste do padrão Adapter.");

        FactoryMidiaSocial Twitter = new TwitterAdapterFactory();
        AdapterRedes adapter = Twitter.criarAdaptador();
        GerenciadorMidiaSocial gerenciador = new GerenciadorMidiaSocial(adapter);
        gerenciador.autenticar();
        gerenciador.publicar(conteudo);

        FactoryMidiaSocial Instagram = new InstagramAdapterFactory();
        AdapterRedes adapter2 = Instagram.criarAdaptador();
        gerenciador.alterarAdaptador(adapter2);
        gerenciador.autenticar();
        gerenciador.publicar(conteudo);
    }
}
