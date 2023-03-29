import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        //fazendo a conexao http
        String url="https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco=URI.create(url);
        HttpClient client =HttpClient.newHttpClient();
        //buscando os dados da url
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse <String>response=client.send(request, BodyHandlers.ofString());
        String body = response.body();
        JsonParser parser= new JsonParser();
        List <Map<String, String>> listaDeFilmes=parser.parse(body);
        //criando diretorio caso não exista
        listaDeFilmes.set(0, null);
         System.out.println(listaDeFilmes.get(0));
        var diretorio=new File("saida/");
        diretorio.mkdir();
        for(Map<String,String> filme:listaDeFilmes){
            //criando as figurinhas
            String urlImagem=filme.get("image");
            String titulo=filme.get("title");
            double classifi=  Double.parseDouble(filme.get("imDbRating"));
            String textoFigurinha;
            InputStream imagemSelo;
            if(classifi>=9.0){
                textoFigurinha="BÃO DEMAIS DA CONTA SÔ";
                imagemSelo= new FileInputStream(new File("selo/aprovadop.jpeg"));
            }

            else if(classifi>=6.0){
                textoFigurinha="HMMM ANÁLISE";
                imagemSelo= new FileInputStream(new File("selo/analisep.jpeg"));
            }
            else{
                textoFigurinha="NEM PERCA SEU TEMPO!";
                imagemSelo= new FileInputStream(new File("selo/reprovadop.jpeg"));
            }

            InputStream inputStream= new URL(urlImagem).openStream();
            String nomeArquivo= titulo;
            //chcp 65001 digitar para aparecer os emojis
            var geradora =new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo,textoFigurinha,imagemSelo);
            //exibindo
            System.out.println(" \u001b[40m \u001b[1mTítulo:\u001b[31m"+filme.get("title")+"\u001b[m");
            System.out.println("\u001b[36m \u001b[40m URL da Imagem:\u001b[m"+"\u001b[1m"+filme.get("image")+"\u001b[m");
            int nEstrela=(int)classifi;
            System.out.print( " \u001b[44m\u001b[32m Rating:\u001b[m");
            
            for(int n=1;n<=nEstrela;n++){
                if(nEstrela>=9){
                    System.out.print("🤩");
                }
                else if(nEstrela>=6){
                    System.out.print("🌟");
                }
                else 
                    System.out.print("💩");
            
            
            
        }
            System.out.println("\n");
       }
      
       
       
 
    }
}
