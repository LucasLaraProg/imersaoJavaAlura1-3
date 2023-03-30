import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
public class App {
    //chcp 65001 digitar para aparecer os emojis
    public static void main(String[] args) throws Exception {
        //fazendo a conexao http
        String url="https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var http= new ClienteHttp();
        String json = http.buscaDados(url);
        //criando diretorio caso não exista
        var diretorio=new File("saida/");
        diretorio.mkdir();
        //exibindo e manipulando dados
        ExtratorDeConteudo extrator= new ExtratorDeConteudoImdb();
        List<Conteudo>conteudos= extrator.extraiConteudos(json);
        for(int i=0;i<conteudos.size();i++){
            Conteudo conteudo=conteudos.get(i);
            //criando as figurinhas
            InputStream urlImage= new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo= conteudo.getTitulo();
            double classifi= conteudo.getClassificacao();
            String textoFigurinha;
            InputStream imagemSelo;
            int nEstrela=(int)classifi;
            String tipoestrela;
            if(classifi>=9.0){
                tipoestrela="❤";
                textoFigurinha="BÃO DEMAIS DA CONTA SÔ";
                imagemSelo= new FileInputStream(new File("selo/aprovadop.jpeg"));
            }

            else if(classifi>=6.0){
                tipoestrela="★";
                textoFigurinha="HMMM ANÁLISE";
                imagemSelo= new FileInputStream(new File("selo/analisep.jpeg"));
            }
            else{
                tipoestrela="☹";
                textoFigurinha="NEM PERCA SEU TEMPO!";
                imagemSelo= new FileInputStream(new File("selo/reprovadop.jpeg"));
            }

            
            //chcp 65001 digitar para aparecer os emojis
           
            //exibindo
            System.out.println(" \u001b[40m \u001b[1mTítulo:\u001b[31m"+conteudo.getTitulo()+"\u001b[m");
            System.out.println("\u001b[36m \u001b[40m URL da Imagem:\u001b[m"+"\u001b[1m"+conteudo.getUrlImagem()+"\u001b[m");
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
            var geradora =new GeradoraDeFigurinhas();
            geradora.cria(urlImage, nomeArquivo,textoFigurinha,imagemSelo,nEstrela,tipoestrela);
            System.out.println("\n");
       }
      
       
       
 
    }
}
