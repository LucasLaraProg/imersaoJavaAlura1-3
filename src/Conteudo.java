public class Conteudo {
    
    private final String titulo; 
    private final String urlImagem ;
    private final Double classificacao;
    public String getTitulo() {
        return titulo;
    }
    public String getUrlImagem() {
        return urlImagem;
    }
    public Double getClassificacao(){
        return classificacao;
    }
    public Conteudo(String titulo, String urlImagem,Double classificacao) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
        this.classificacao=classificacao;
    }
    



}
