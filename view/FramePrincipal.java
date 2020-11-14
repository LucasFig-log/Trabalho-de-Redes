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
  public static final int TEXT_QUADROS_ENQUADRADOS = 1;
  public static final int TEXT_QUADROS_EM_PARIDADE_PAR = 2;
  public static final int TEXT_AREA_DECODIFICADO = 3;
  public static final int TEXT_ASCII_DECODIFICADO = 4;
  public static final int TEXT_BITS_BRUTOS_DECODIFICADO = 5;
  public static final int TEXT_QUADROS_SEM_PARIDADE_PAR = 6;
  public static final int TEXT_QUADROS_DESENQUADRADOS_RECEBIDOS = 8;
  private static Font font;

  public BorderLayout layout;
  public static PanelSouth panelSouth;
  public static PanelWest panelWest;
  private JScrollPane scrollBits;

  /* ***************************************************************
  Metodo: this*
  Funcao: Construtor da classe this*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public FramePrincipal() {

    super("Trabalho 04");

    layout = new BorderLayout();
    font = new Font("Coolvetica", Font.HANGING_BASELINE, 13);
    panelWest = new PanelWest();
    panelSouth = new PanelSouth();
    scrollBits = new JScrollPane(panelSouth);
    scrollBits.setPreferredSize(new Dimension(1000, 200));
    scrollBits.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    
    this.getRootPane().setDefaultButton(panelWest.send);
    
    // adicionando os JPanel's individuais ao frame principal
    add(panelWest, BorderLayout.WEST);
    
    add(new PanelCenter(), BorderLayout.CENTER);
    add(new PanelEast(), BorderLayout.EAST);
    add(scrollBits, BorderLayout.SOUTH);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1000, 630);
    this.setResizable(false);
    this.centerContainer(this);
    this.setVisible(true);
    
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
          PanelWest.textQuadrosEnquadrados.setFont(font);
          PanelWest.textQuadrosEnquadrados.setText(mensagem);
          PanelWest.textQuadrosEnquadrados.update(PanelWest.textQuadrosEnquadrados.getGraphics());
          break;
        case 2:
           PanelWest.textBitsParidade.setFont(font);
           PanelWest.textBitsParidade.setText(mensagem);
           PanelWest.textBitsParidade.update(PanelWest.textBitsParidade.getGraphics());
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
          // PanelEast.textBitsBrutos.setFont(font);
          // PanelEast.textBitsBrutos.setText(mensagem);
          // PanelEast.textBitsBrutos.update(PanelEast.textBitsBrutos.getGraphics());
          break;
        case 6:
          PanelEast.textBitsParidadeReceptora.setFont(font);
          PanelEast.textBitsParidadeReceptora.setText(mensagem);
          PanelEast.textBitsParidadeReceptora.update(PanelEast.textBitsParidadeReceptora.getGraphics());
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

  public static void limparCamposTexto(){

    //PanelWest.textArea.setText("");
    PanelWest.textASCII.setText("");
    PanelWest.textQuadrosEnquadrados.setText("");
    PanelWest.textBitsParidade.setText("");
    PanelEast.textArea.setText("");
    PanelEast.textASCII.setText("");
    PanelEast.textQuadrosEnquadrados.setText("");
    PanelEast.textBitsParidadeReceptora.setText("");
  }
}
