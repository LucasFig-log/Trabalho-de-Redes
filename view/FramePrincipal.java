package view;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import util.Computador;

public class FramePrincipal extends JFrame {
  public static final int TEXT_AREAPC1 = 0;
  public static final int TEXT_ASCIIPC1 = 1;
  public static final int TEXT_BITSPC1 = 2;
  public static final int TEXT_AREAPC2 = 3;
  public static final int TEXT_ASCIIPC2 = 4;
  public static final int TEXT_BITSPC2 = 5;
  public static final int TEXT_AREAPC3 = 6;
  public static final int TEXT_ASCIIPC3 = 7;
  public static final int TEXT_BITSPC3 = 8; 
  
  private static Font font;

  public BorderLayout layout;
  public static PanelSouth panelSouth;
  public static PanelVisualComputador visualPC1;
  public static PanelVisualComputador visualPC2;
  public static PanelVisualComputador visualPC3;

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
    
        
    visualPC1 = new PanelVisualComputador(1);
    visualPC2 =  new PanelVisualComputador(2);
    visualPC3 =  new PanelVisualComputador(3);
    
    panelSouth = new PanelSouth();
    scrollBits = new JScrollPane(panelSouth);
    scrollBits.setPreferredSize(new Dimension(1000, 250));
    scrollBits.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    
    //this.getRootPane().setDefaultButton(visualPC1.send);
    
    // adicionando os JPanel's individuais ao frame principal
    add(visualPC1,BorderLayout.WEST);
    add(visualPC2, BorderLayout.CENTER);
    add(visualPC3, BorderLayout.EAST);
    add(scrollBits, BorderLayout.SOUTH);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1000, 730);
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
          visualPC1.textArea.setFont(font);
          visualPC1.textArea.setText(mensagem);
          visualPC1.textArea.update(visualPC1.textArea.getGraphics());

        
        case 1:
          visualPC1.textASCII.setFont(font);
          visualPC1.textASCII.setText(mensagem);
          visualPC1.textASCII.update(visualPC1.textASCII.getGraphics());
          
          break;
        case 2:
          visualPC1.textQuadrosEnquadrados.setFont(font);
          visualPC1.textQuadrosEnquadrados.setText(mensagem);
          visualPC1.textQuadrosEnquadrados.update(visualPC1.textQuadrosEnquadrados.getGraphics());
          break;
        case 3:
          visualPC2.textArea.setFont(font);
          visualPC2.textArea.setText(mensagem);
          visualPC2.textArea.update(visualPC2.textArea.getGraphics());
          break;
        case 4:
          visualPC2.textASCII.setFont(font);
          visualPC2.textASCII.setText(mensagem);
          visualPC2.textASCII.update(visualPC2.textASCII.getGraphics());
          break;
        case 5:
          
          break;
        case 6:
          visualPC3.textArea.setFont(font);
          visualPC3.textArea.setText(mensagem);
          visualPC3.textArea.update(visualPC2.textArea.getGraphics());
          break;
        case 7:
          visualPC3.textASCII.setFont(font);
          visualPC3.textASCII.setText(mensagem);
          visualPC3.textASCII.update(visualPC3.textASCII.getGraphics());
          break;
        case 8:
          visualPC3.textQuadrosEnquadrados.setFont(font);
          visualPC3.textQuadrosEnquadrados.setText(mensagem);
          visualPC3.textQuadrosEnquadrados.update(visualPC2.textQuadrosEnquadrados.getGraphics());
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
