package view;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import img.Imagens;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelSouth extends JPanel {

  public static JPanel paintPanel;
  private JLabel labelPaint;
  private JScrollPane scrollBits;
  private Font font;
  private final int ESPACO = 52;
  private final int Y = 70;
  public final int LARGURA = 60;
  private final int ALTURA = 80;
  private int x = 0;
  private Imagens imagem;
  public static boolean bandeira;

  public static List<Integer> fluxoDeBits;
  public static ArrayList<Image> imagensLinhas = new ArrayList<Image>();

  /*
   * *************************************************************** Metodo:
   * PanelSouth* Funcao: Construtor da classe PanelSouth* Parametros: nulo*
   * Retorno: void*
   */
  public PanelSouth() {

    Color cor = new Color(127, 255, 212);
    this.setBackground(cor);
    this.setPreferredSize(new Dimension(1000, 200));
    font = new Font("OldStyle", Font.BOLD, 18);

    PanelSouth.fluxoDeBits = new ArrayList<>();
    imagem = new Imagens();
    imagensLinhas = imagem.getVetorImagens();

  }

  /*
   * *************************************************************** Metodo:
   * paintComponent* Funcao: printar as imagens no JPanel referente aos bits*
   * Parametros: Graphics g* Retorno: void*
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (bandeira) { // checa se o array está vazio
      for (int i = 0; i < fluxoDeBits.size() - 1; i++) {

        if (fluxoDeBits.get(i) == 0) {
          if (fluxoDeBits.get(i + 1) == 1) {
            g.drawImage(imagensLinhas.get(0), x, Y, LARGURA, ALTURA, paintPanel); // imprime imagem linha0
            x += ESPACO;
          } else {
            g.drawImage(imagensLinhas.get(1), x, Y, LARGURA, ALTURA, paintPanel); // imprime imagem linha1
            x += ESPACO;
          }
        } else {
          if (fluxoDeBits.get(i + 1) == 1) {
            g.drawImage(imagensLinhas.get(3), x, Y, LARGURA, ALTURA, paintPanel); // imprime imagem linha3
            x += ESPACO;
          } else {
            g.drawImage(imagensLinhas.get(2), x, Y, LARGURA, ALTURA, paintPanel); // imprime imagem linha2
            x += ESPACO;
          }
        }
      }
      if (fluxoDeBits.get(fluxoDeBits.size() - 1) == 0) { // ultima imagem a imprimir na tela
        g.drawImage(imagensLinhas.get(1), x, Y, LARGURA, ALTURA, paintPanel);

      } else {
        g.drawImage(imagensLinhas.get(3), x, Y, LARGURA, ALTURA, paintPanel);
      }
      x = 0;

    }

  }

}
