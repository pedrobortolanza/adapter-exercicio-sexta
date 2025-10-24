public class TwitterAdapter implements AdapterRedes {
    @Override
    public void autenticar() {
        System.out.println("\nAutenticando...");
    }

    @Override
    public void publicar(Conteudo conteudo) {
        System.out.println("Publicando no Twitter: " + conteudo.getTitulo() + " - " + conteudo.getDescricao());
    }
    
}
