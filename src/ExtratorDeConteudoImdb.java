import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoImdb implements ExtratorDeConteudo {
    public List<Conteudo>extraiConteudos(String json){
        //extrair somente os dados q interessam
        var parser = new JsonParser();
        List<Map<String,String>> listaDeAtributos=parser.parse(json);
        List<Conteudo> conteudos=new ArrayList<>();
        //popular a lista
        for (Map<String,String>atributos : listaDeAtributos) {
            String titulo=atributos.get("title");
            String urlImagem=atributos.get("image");
            double classifi=  Double.parseDouble(atributos.get("imDbRating"));
            Conteudo conteudo = new Conteudo(titulo, urlImagem,classifi) ;
            conteudos.add(conteudo);           
        }
        return conteudos;
    }
}
