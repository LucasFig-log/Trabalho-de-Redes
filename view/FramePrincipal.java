package view;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import view.PanelCenter;
import view.PanelEast;
import view.PanelSouth;
import view.PanelWest;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FramePrincipal extends JFrame {
  public static final int TEXT_ASCII = 0;
  public static final int TEXT_BITS_BRUTOS = 1;
  public static final int TEXT_BISTS_CODIFICADOS = 2;
  public static final int TEXT_AREA_DECODIFICADO = 3;
  public static final int TEXT_ASCII_DECODIFICADO = 4;
  public static final int TEXT_BITS_BRUTOS_DECODIFICADO = 5;
  public static final int TEXT_BITS_RECEBIDOS = 6;
  public static final int TEXT_QUADROS_ENQUADRADOS = 7;
  public static final int TEXT_QUADROS_DESENQUADRADOS_RECEBIDOS = 8;
  private static Font font;

  public BorderLayout layout;
  public static PanelSouth panelSouth;
  private JScrollPane scrollBits;

  /* ***************************************************************
  Metodo: FramePrincipal*
  Funcao: Construtor da classe FramePrincipal*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public FramePrincipal() {

    super("Trabalho 03");

    layout = new BorderLayout();
    font = new Font("Coolvetica", Font.HANGING_BASELINE, 13);

    panelSouth = new PanelSouth();
    scrollBits = new JScrollPane(panelSouth);
    scrollBits.setPreferredSize(new Dimension(1000, 200));
    scrollBits.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    // adicionando os JPanel's individuais ao frame principal
    add(new PanelWest(), BorderLayout.WEST);
    add(new PanelCenter(), BorderLayout.CENTER);
    add(new PanelEast(), BorderLayout.EAST);
    add(scrollBits, BorderLayout.SOUTH);

  }

   /* ***************************************************************
  Metodo: rePintar*
  Funcao: repinta as imagens apos uma nova mensagem enviada*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public static void rePintar() {
    
    
    try {
      panelSouth.setPreferredSize(new Dimension(panelSouth.LARGURA * PanelSouth.fluxoDeBits.size(), 200));
      panelSouth.revalidate();
      panelSouth.repintar();
      Thread.sleep(1000);
    } catch (Exception e) {
      System.out.println("Erro ao repintar");
    }

  }

   /* ***************************************************************
  Metodo: imprimirNaTela*
  Funcao: imprime os textos em determinados locais*
  Parametros: String mensagem, int tipoDeImpressao*
  Retorno: void*
  *************************************************************** */
  public static void imprimirNaTela(String mensagem, int tipoDeImpressao) {

    
      switch (tipoDeImpressao) {
        case 0:
          PanelWest.textASCII.setFont(font);
          PanelWest.textASCII.setText(mensagem);
          PanelWest.textASCII.update(PanelWest.textASCII.getGraphics());
          
          break;
        case 1:
          PanelWest.textBitsBrutos.setFont(font);
          PanelWest.textBitsBrutos.setText(mensagem);
          PanelWest.textBitsBrutos.update(PanelWest.textBitsBrutos.getGraphics());
          break;
        case 2:
          PanelWest.textBitsCodificados.setFont(font);
          PanelWest.textBitsCodificados.setText(mensagem);
          PanelWest.textBitsCodificados.update(PanelWest.textBitsCodificados.getGraphics());
          break;
        case 3:
          PanelEast.textArea.setFont(font);
          PanelEast.textArea.setText(mensagem);
          PanelEast.textArea.update(PanelEast.textArea.getGraphics());
          break;
        case 4:
          PanelEast.textASCII.setFont(font);
          PanelEast.textASCII.setText(mensagem);
          PanelEast.textASCII.update(PanelEast.textASCII.getGraphics());
          break;
        case 5:
          PanelEast.textBitsBrutos.setFont(font);
          PanelEast.textBitsBrutos.setText(mensagem);
          PanelEast.textBitsBrutos.update(PanelEast.textBitsBrutos.getGraphics());
          break;
        case 6:
          PanelEast.textBitsDecodificados.setFont(font);
          PanelEast.textBitsDecodificados.setText(mensagem);
          PanelEast.textBitsDecodificados.update(PanelEast.textBitsDecodificados.getGraphics());
          break;
        case 7:
          PanelWest.textQuadrosEnquadrados.setFont(font);
          PanelWest.textQuadrosEnquadrados.setText(mensagem);
          PanelWest.textQuadrosEnquadrados.update(PanelWest.textQuadrosEnquadrados.getGraphics());
          break;
        case 8:
          PanelEast.textQuadrosEnquadrados.setFont(font);
          PanelEast.textQuadrosEnquadrados.setText(mensagem);
          PanelEast.textQuadrosEnquadrados.update(PanelEast.textQuadrosEnquadrados.getGraphics());
          break;    
        }
        try{
          Thread.sleep(600);
        } catch (Exception e){
          e.printStackTrace();
        }
  
  }

  /* ***************************************************************
  Metodo: centerContainer*
  Funcao: Posiciona o JFrame no centro da tela*
  Parametros: Container container*
  Retorno: void*
  *************************************************************** */
  public void centerContainer(Container container) {
    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int componentWidth = container.getWidth();
    int componentHeigth = container.getHeight();
    container.setBounds((screenSize.width - componentWidth) / 2, (screenSize.height - componentHeigth) / 2,
        componentWidth, componentHeigth);
  }
}
