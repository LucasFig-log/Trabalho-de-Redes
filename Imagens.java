
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Imagens {

  private ArrayList<Image> imagens;

 /* *****************************************************************************
  Metodo: Imagens*
  Funcao: Construtor da classe Imagens*
  Parametros: nulo*
  Retorno: void*
  ***************************************************************************** */
  public Imagens() {
    try {
      this.imagens = new ArrayList<Image>();
         
      for (int i = 0; i <= 3; i++) { //armazena as imagens na lista
        Image imagem = ImageIO.read(new File("linha" + i + ".png"));
        imagens.add(imagem);
      }

    } catch (IOException e) {
      System.out.println("Erro ao carregar imagem");
    }
  }

 /* *****************************************************************************
  Metodo: getVetorImagens*
  Funcao: pegar o arrayList das imagens carregadas*
  Parametros: nulo*
  Retorno: ArayList<Image>*
  ***************************************************************************** */
  public ArrayList<Image> getVetorImagens() {
    return this.imagens;
  }

}
