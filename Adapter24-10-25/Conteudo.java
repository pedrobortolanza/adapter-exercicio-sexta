public class Conteudo {
    String titulo;
    String descricao;

    public Conteudo(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTitulo() {
        return titulo;
    }
}