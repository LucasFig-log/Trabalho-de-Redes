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
import util.Computador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;



public class PanelVisualComputador extends JPanel {
  private int id;
  public Computador computador;
  public int indexSelecionado = 2;

  public  JTextArea textArea;
  private JScrollPane scrollTextArea;
  public  JTextArea textASCII;
  private JScrollPane scrollTextASCII;
  public  JTextArea textBitsParidade;
  private JScrollPane scrollTextBitsParidade;
  public  JRadioButton comunicacaoPC1;
  public  JRadioButton comunicacaoPC2;
  public JRadioButton comunicacaoPC3;
  private ButtonGroup radioGroup;
 
  public  JTextArea textBitsMensagem;
  private JScrollPane scrollBitsMensagem;
  public  JButton send;
  private JLabel labelText;
  private JLabel labelBitsParidade;
  private JLabel labelASCII;
  private JLabel labelBitsMensagem;
  private JLabel labelComunicao;
  private Font font;
  private Font font2;
  private Color cor;
  public  String mensagem;
  /*
   * *************************************************************** Metodo:
   * PanelWest* Funcao: Construtor da classe PanelWest* Parametros: nulo* Retorno:
   * void*
   */
  public PanelVisualComputador(int id) {
    this.id = id;
    computador = new Computador(id);
    
    //parte visual
    cor = new Color(255, 250, 205);
    this.setBackground(cor);
    setPreferredSize(new Dimension(350, 200));
    font = new Font("OldStyle", Font.BOLD, 18);
    font2 = new Font("Coolvetica", Font.BOLD, 15);
    
    // configuracoes do JLabel com o texto especifico
    labelText = new JLabel("Computador "+id);
    labelText.setFont(font);
    labelText.setPreferredSize(new Dimension(200, 30));

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
    send.addKeyListener(new KeyListener(){
      @Override
      public void keyTyped(KeyEvent e){

      }
      @Override
      public void keyReleased(KeyEvent e){

      }
      @Override
      public void keyPressed(KeyEvent e){
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER ){
          
          if (textArea.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Caixa de texto vazia! ", "Alerta! ", JOptionPane.ERROR_MESSAGE);
          } else{
            send.setEnabled(false);
            //CamadaDeAplicacaoTransmissora.camadaDeAplicacaoTransmissora(textArea.getText());
          }
        } 
      }
    });
    
    labelComunicao = new JLabel("Comunicação");
    labelComunicao.setFont(font);
    labelComunicao.setPreferredSize(new Dimension(300,40));

    
    
    // configuracoes do JLabel com o texto especifico
    labelASCII = new JLabel("Transcrição pra tabela ASCII");
    labelASCII.setFont(font);
    labelASCII.setPreferredSize(new Dimension(300, 30));

    // configuracoes do campo de texto que exibe os caracteres da tabela ASCII
    textASCII = new JTextArea();
    textASCII.setEditable(false);
    textASCII.setLineWrap(true);
    scrollTextASCII = new JScrollPane(textASCII);
    scrollTextASCII.setPreferredSize(new Dimension(300, 70));
    scrollTextASCII.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

    // configuracoes do JLabel com o texto especifico
    labelBitsMensagem = new JLabel("Bits referente a mensagem");
    labelBitsMensagem.setFont(font2);
    labelBitsMensagem.setPreferredSize(new Dimension(300, 30));

    // configuracoes do campo de texto que exibe os quadros enquadrados
    textBitsMensagem = new JTextArea();
    textBitsMensagem.setEditable(false);
    textBitsMensagem.setLineWrap(true);
    scrollBitsMensagem = new JScrollPane(textBitsMensagem);
    scrollBitsMensagem.setPreferredSize(new Dimension(300, 70));
    scrollBitsMensagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    // configuracoes do JLabel com o texto especifico
    labelBitsParidade = new JLabel("Quadro em Paridades Par");
    labelBitsParidade.setFont(font);
    labelBitsParidade.setPreferredSize(new Dimension(300, 30));

    // configuracoes do campo de texto que exibe os quadros enquadrados
    textBitsParidade = new JTextArea();
    textBitsParidade.setEditable(false);
    textBitsParidade.setLineWrap(true);
    scrollTextBitsParidade = new JScrollPane(textBitsParidade);
    scrollTextBitsParidade. setPreferredSize(new Dimension(300, 50));
    scrollTextBitsParidade.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    
    // adcionando todos os componentes ao JPanel
    
    add(labelText);
    add(scrollTextArea);
    add(send);
    add(labelComunicao);
    criarRadioButton(this.id); //cria radioButton para cara computador
    add(labelASCII);
    add(scrollTextASCII);
    add(labelBitsMensagem);
    add(scrollBitsMensagem);
    

   

  }

  public void criarRadioButton(int id){
   

    if(id == 1){
      comunicacaoPC2 = new JRadioButton("Computador 2",true);
      comunicacaoPC2.setBackground(cor);
      comunicacaoPC3 = new JRadioButton("Computador 3");
      comunicacaoPC3.setBackground(cor);

      add(comunicacaoPC2);
      add(comunicacaoPC3);

      radioGroup = new ButtonGroup();
      radioGroup.add(comunicacaoPC2);
      radioGroup.add(comunicacaoPC3);

      comunicacaoPC2.addItemListener(new RadioButtonHandler());
      comunicacaoPC3.addItemListener(new RadioButtonHandler());
    } else if(id == 2){
      
      comunicacaoPC1 = new JRadioButton("Computador 1", true);
      comunicacaoPC1.setBackground(cor);
      comunicacaoPC3 = new JRadioButton("Computador 3");
      comunicacaoPC3.setBackground(cor);

      add(comunicacaoPC1);
      add(comunicacaoPC3);

      radioGroup = new ButtonGroup();
      radioGroup.add(comunicacaoPC1);
      radioGroup.add(comunicacaoPC3);

      comunicacaoPC1.addItemListener(new RadioButtonHandler());
      comunicacaoPC3.addItemListener(new RadioButtonHandler());

    } else{
      comunicacaoPC1 = new JRadioButton("Computador 1", true);
      comunicacaoPC1.setBackground(cor);
      comunicacaoPC2 = new JRadioButton("Computador 2");
      comunicacaoPC2.setBackground(cor);

      add(comunicacaoPC1);
      add(comunicacaoPC2);

      radioGroup = new ButtonGroup();
      radioGroup.add(comunicacaoPC1);
      radioGroup.add(comunicacaoPC2);

      comunicacaoPC1.addItemListener(new RadioButtonHandler());
      comunicacaoPC2.addItemListener(new RadioButtonHandler());
      
    }

   
    
  }

  private class RadioButtonHandler implements ItemListener {

    /* ***************************************************************
    Metodo: itemStateChanged*
    Funcao: manopular evento de selecao*
    Parametros: ItemEvent event*
    Retorno: void*
    *************************************************************** */
    public void itemStateChanged(ItemEvent event) {
            

      if(comunicacaoPC1.isSelected()){
        indexSelecionado = 1;
      } else if(comunicacaoPC2.isSelected()){
        indexSelecionado = 2;
      } else if(comunicacaoPC3.isSelected()){
        indexSelecionado = 3;
      }
      
      
    }

  }
  // classe privada para tratar o evento do botao que envia a mensagem
  public class ButtonHandler implements ActionListener{

    /*****************************************************************     
     * Metodo:actionPerformed* 
     * Funcao: tratar o evendo de botao* 
     * Parametros: ActionEvent event* 
     * Retorno: void*
     */
    public void actionPerformed(ActionEvent event) {

      if (textArea.getText().equals("") && indexSelecionado == 0) {
        JOptionPane.showMessageDialog(null, "Caixa de texto vazia ou tipo de comunicação não! ", "Alerta! ", JOptionPane.ERROR_MESSAGE);
      } else {
        //send.setEnabled(false);
        
        mensagem = textArea.getText();
        System.out.println(indexSelecionado);
        computador.mandarMensagem(mensagem, computador.getId() ,indexSelecionado);
        //CamadaDeAplicacaoTransmissora.camadaDeAplicacaoTransmissora(mensagem);
        
        
      }

    }
    
  }

  
  

   
}
