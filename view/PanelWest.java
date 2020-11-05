package view;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import camadas.CamadaDeAplicacaoTransmissora;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class PanelWest extends JPanel {

  public JTextArea textArea;
  private JScrollPane scrollTextArea;
  public static JTextArea textASCII;
  private JScrollPane scrollTextASCII;
  public static JTextArea textBitsCodificados;
  private JScrollPane scrollTextBitsCodificados;
  public static JTextArea textBitsBrutos;
  private JScrollPane scrollTextBitsBrutos;
  public static JTextArea textQuadrosEnquadrados;
  private JScrollPane scrollQuadrosEnquadrados;
  private JButton send;
  private JLabel labelText;
  private JLabel labelBitsCodificado;
  private JLabel labelASCII;
  private JLabel labelBitsBrutos;
  private JLabel labelQuadrosEnquadrados;
  private Font font;
  private Font font2;
  private Color cor;

  /*
   * *************************************************************** Metodo:
   * PanelWest* Funcao: Construtor da classe PanelWest* Parametros: nulo* Retorno:
   * void*
   */
  public PanelWest() {
    cor = new Color(255, 250, 205);
    this.setBackground(cor);
    setPreferredSize(new Dimension(350, 200));
    font = new Font("OldStyle", Font.BOLD, 18);
    font2 = new Font("Coolvetica", Font.HANGING_BASELINE, 13);

    // configuracoes do JLabel com o texto especifico
    labelText = new JLabel("Mensagem Decodificada");
    labelText.setFont(font);
    labelText.setPreferredSize(new Dimension(250, 30));

    // configuracoes do campo de texto onde a mensagem sera digitada
    textArea = new JTextArea();
    textArea.setFont(font2);
    textArea.setLineWrap(true);
    scrollTextArea = new JScrollPane(textArea);
    scrollTextArea.setPreferredSize(new Dimension(150, 50));
    scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

    // configuracoes do botao
    send = new JButton("Enviar");
    send.setBackground(cor);
    send.setFont(new Font("OldStyle", Font.BOLD, 13));
    send.setPreferredSize(new Dimension(80, 30));
    ButtonHandler handler = new ButtonHandler();
    send.addActionListener(handler);

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
    scrollTextASCII.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

    // configuracoes do JLabel com o texto especifico
    labelQuadrosEnquadrados = new JLabel("Quadros Enquadrados");
    labelQuadrosEnquadrados.setFont(font);
    labelQuadrosEnquadrados.setPreferredSize(new Dimension(300, 30));

    // configuracoes do campo de texto que exibe os quadros enquadrados
    textQuadrosEnquadrados = new JTextArea();
    textQuadrosEnquadrados.setEditable(false);
    textQuadrosEnquadrados.setLineWrap(true);
    scrollQuadrosEnquadrados = new JScrollPane(textQuadrosEnquadrados);
    scrollQuadrosEnquadrados.setPreferredSize(new Dimension(300, 50));
    scrollQuadrosEnquadrados.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    // adcionando todos os componentes ao JPanel
    add(labelText);
    add(scrollTextArea);
    add(send);
    add(labelASCII);
    add(scrollTextASCII);
    add(labelQuadrosEnquadrados);
    add(scrollQuadrosEnquadrados);
   

  }

  // classe privada para tratar o evento do botao que envia a mensagem
  private class ButtonHandler implements ActionListener {

    /*
     * *************************************************************** Metodo:
     * actionPerformed* Funcao: tratar o evendo de botao* Parametros: ActionEvent
     * event* Retorno: void*
     */
    public void actionPerformed(ActionEvent event) {

      if (textArea.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Caixa de texto vazia! ", "Alerta! ", JOptionPane.ERROR_MESSAGE);
      } else {
        CamadaDeAplicacaoTransmissora.camadaDeAplicacaoTransmissora(textArea.getText());
      }

    }
  }
}
