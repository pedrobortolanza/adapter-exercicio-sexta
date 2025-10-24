public class InstagramAdapter implements AdapterRedes {
    @Override
    public void autenticar() {
        System.out.println("\nAutenticando...");
    }

    @Override
    public void publicar(Conteudo conteudo) {
        System.out.println("Publicando no Instagram: " + conteudo.getTitulo() + " - " + conteudo.getDescricao());
    }
    
}
