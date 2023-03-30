import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    public void cria(InputStream inputStream,String nomearquivo,String textofigurinha,
    InputStream seloInputStream, Integer nEstrela,String tipoestrela) throws Exception{
    //ler a imagem
    //var inputStream= new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_2.jpg");
    BufferedImage imagemoriginal = ImageIO.read(inputStream);
    //criar nova imagem em memoria com transparencia e com tamanho novo
    int largura= imagemoriginal.getWidth();
    int altura= imagemoriginal.getHeight();
    int novaAltura= altura + 200;
    BufferedImage novaImagem= new BufferedImage(largura,novaAltura,BufferedImage.TRANSLUCENT);
    //copiar a imagem original para a imagem (em memoria)
    Graphics2D graphics=(Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemoriginal,0,0,null);
    //colocando outra imagem por cima
    BufferedImage imageSelo=ImageIO.read(seloInputStream);
    int positionImageSeloY=novaAltura-imageSelo.getHeight();
    //System.out.println("Tamanho altura do selo"+imageSelo.getHeight());
    //System.out.println("Tamanho altura da foto"+novaImagem.getHeight());
    graphics.drawImage(imageSelo,0,positionImageSeloY,null);
    //config da fonte
    var fonte=new Font(Font.SERIF,Font.BOLD,68);
    graphics.setFont(fonte);
    graphics.setColor(Color.RED);
    //escrever frase pra figurinha
    FontMetrics fontMetrics = graphics.getFontMetrics();
    var retangulo=fontMetrics.getStringBounds(textofigurinha,graphics);
    int larguraTexto = (int) retangulo.getWidth();
    int posicaoTextoX=(largura-larguraTexto)/2;
    int posicaoTextoY=novaAltura-100;
    var retanguloo=fontMetrics.getStringBounds(tipoestrela,graphics);
    int larguraEstrela = (int) retanguloo.getWidth();
    int posicaoEstrelaX=((largura-larguraEstrela)/2)-300;
    int posicaoEstrelaY=novaAltura;
    int contadorestrela=10;
    graphics.drawString(textofigurinha,posicaoTextoX,posicaoTextoY);
    //escrevendo numero de estrelas
    for(int i=0;i<nEstrela-1;i++){
        if(nEstrela>=9){
            graphics.setFont(fonte);
            graphics.setColor(Color.black);
            graphics.drawString(tipoestrela,posicaoEstrelaX,posicaoEstrelaY);
            posicaoEstrelaX+=80;
            
        }
        else  if(nEstrela>=6){
            graphics.setFont(fonte);
            graphics.setColor(Color.black);
            graphics.drawString(tipoestrela,posicaoEstrelaX,posicaoEstrelaY);
            posicaoEstrelaX+=80;
           
        }
        else 
        graphics.setFont(fonte);
        graphics.setColor(Color.black);
        graphics.drawString(tipoestrela,posicaoEstrelaX,posicaoEstrelaY);
    }
    //fazendo contorno do texto
    FontRenderContext renderFont= graphics.getFontRenderContext();
    var textLayout = new TextLayout(textofigurinha,fonte,renderFont);
     Shape outline= textLayout.getOutline(null);
     AffineTransform transform= graphics.getTransform();
     transform.translate(posicaoTextoX,posicaoTextoY);
     graphics.setTransform(transform);
     graphics.setColor(Color.BLACK);
     graphics.draw(outline);
     graphics.setClip(outline);
     var outlineStroke = new BasicStroke(largura* 0.001f);
     graphics.setStroke(outlineStroke);
    //escrever nova imagem em um arquivo    
    ImageIO.write(novaImagem,"png", new File("saida/"+nomearquivo+".png"));
    
    
    }
    
}
