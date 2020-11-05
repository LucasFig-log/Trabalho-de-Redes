package view;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class PanelEast extends JPanel {

  public static JTextArea textArea;
  private JScrollPane scrollTextArea;
  public static JTextArea textASCII;
  private JScrollPane scrollTextASCII;
  public static JTextArea textBitsDecodificados;
  private JScrollPane scrollBitsDecodificados;
  public static JTextArea textBitsBrutos;
  private JScrollPane scrollTextBitsBrutos;
  public static JTextArea textQuadrosEnquadrados;
  private JScrollPane scrollQuadrosEnquadrados;
  private JLabel labelText;
  private JLabel labelBitsDecodificado;
  private JLabel labelASCII;
  private JLabel labelBitsBrutos;
  private JLabel labelQuadrosEnquadrados;
  private Font font;

    /* ***************************************************************
  Metodo: PanelEast*
  Funcao: Construtor da classe PanelEast*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public PanelEast() {

    this.setBackground(Color.GRAY);
    this.setPreferredSize(new Dimension(350, 200));
    Color cor = new Color(255, 250, 205);
    this.setBackground(cor);
    font = new Font("OldStyle", Font.BOLD, 18);

    // configuracoes do JLabel com o texto especifico
    labelText = new JLabel("Mensagem Decodificada");
    labelText.setFont(font);
    labelText.setPreferredSize(new Dimension(250, 30));

    // configuracoes do campo de texto onde a mensagem aparecera
    textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    scrollTextArea = new JScrollPane(textArea);
    scrollTextArea.setPreferredSize(new Dimension(150, 50));
    scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    // configuracoes do JLabel com o texto especifico
    labelASCII = new JLabel("Transcrição pra tabela ASCII");
    labelASCII.setFont(font);
    labelASCII.setPreferredSize(new Dimension(300, 30));

    // configuracoes do campo de texto que exibe os caracteres da tabela ASCII
    textASCII = new JTextArea();
    textASCII.setEditable(false);
    textASCII.setLineWrap(true);
    scrollTextASCII = new JScrollPane(textASCII);
    scrollTextASCII.setPreferredSize(new Dimension(300, 50));
    scrollTextASCII.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

     // configuracoes do JLabel com o texto especifico
    labelQuadrosEnquadrados = new JLabel("Quadros Desenquadrados Recebidos");
    labelQuadrosEnquadrados.setFont(new Font("OldStyle", Font.BOLD, 14));
    labelQuadrosEnquadrados.setPreferredSize(new Dimension(300, 30));

    // configuracoes do campo de texto que exibe os quadros desenquadrados
    textQuadrosEnquadrados = new JTextArea();
    textQuadrosEnquadrados.setEditable(false);
    textQuadrosEnquadrados.setLineWrap(true);
    scrollQuadrosEnquadrados = new JScrollPane(textQuadrosEnquadrados);
    scrollQuadrosEnquadrados.setPreferredSize(new Dimension(300, 50));
    scrollQuadrosEnquadrados.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


    // adcionando todos os componentes ao JPanel
    add(labelText);
    add(scrollTextArea);
    add(labelASCII);
    add(scrollTextASCII);
    add(labelQuadrosEnquadrados);
    add(scrollQuadrosEnquadrados);
  

  }
}
