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
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Hashtable;

public class PanelCenter extends JPanel {

  private JRadioButton binarioRadioButton;
  private JRadioButton manchesterRadioButton;
  private JRadioButton manchesterDifRadioButton;
  private JRadioButton contagemDeCaracteresRadioButton;
  private JRadioButton bytesDeFlagRadioButton;
  private JRadioButton flagsIniciaisFinaisRadioButton;
  private JRadioButton violacaoCamadaFisicaRadioButton;
  private ButtonGroup radioGroup;
  private ButtonGroup radioGroup2;
  private JLabel labelText;
  private JLabel labelPaint;
  private JLabel labelTextTransmissao;
  private JLabel labelProbabilidadeErro;
  private JPanel panelInv;
  private JPanel panelInvDivisor;
  private JSlider sliderErro;
  private Font font;
  private Font font2;
  public static double probabilidade;

  /* ***************************************************************
  Metodo: PanelCenter*
  Funcao: Construtor da classe PanelCenter*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  @SuppressWarnings("unchecked")
  public PanelCenter() {

    Color cor = new Color(255, 250, 205);
    this.setBackground(cor);
    setPreferredSize(new Dimension(300, 200));
    font2 = new Font("OldStyle", Font.BOLD, 15);

    // configuracoes do JLabel com o texto especifico
    labelTextTransmissao = new JLabel("Tipo de Enquadramento");
    labelTextTransmissao.setFont(font2);
    labelTextTransmissao.setPreferredSize(new Dimension(200, 50));

    // configurando os JRadioButtons para a selecao dos tipos de codificacao
    bytesDeFlagRadioButton = new JRadioButton("Inserção de Bytes", true);
    bytesDeFlagRadioButton.setBackground(cor);
    
    // configuracoes do JLabel com o texto especifico
    labelProbabilidadeErro = new JLabel("Probabilidade de erro");
    labelProbabilidadeErro.setFont(font2);
    labelProbabilidadeErro.setPreferredSize(new Dimension(175,40));

    // configurando o JSlider para a selecionar a probabilidade de erro
    sliderErro = new JSlider(0, 100, 0);
    sliderErro.setBackground(cor);
    sliderErro.setPaintLabels(true);
    sliderErro.setMinorTickSpacing(50); //adicionar os ticks 
    sliderErro.setPaintTicks(true);
    sliderErro.setSnapToTicks(false);
    
    //mapeando a posicao do JSlider
    Hashtable position = new Hashtable();
    position.put(0, new JLabel("0%"));
    position.put(50, new JLabel("50%"));
    position.put(100, new JLabel("100%"));
    sliderErro.setLabelTable(position);
    
    //Captura os numeros do slider
    sliderErro.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e){
        probabilidade = (double)((JSlider)e.getSource()).getValue()/100;
        
      }

    });


    panelInv = new JPanel();
    panelInv.setPreferredSize(new Dimension(500, 30));
    panelInv.setBackground(cor);

    panelInvDivisor = new JPanel();
    panelInvDivisor.setPreferredSize(new Dimension(500, 200));
    panelInvDivisor.setBackground(cor);

    labelPaint = new JLabel("Sinais dos Bits");
    labelPaint.setFont(new Font("OldStyle", Font.BOLD, 18));
    labelPaint.setHorizontalAlignment(SwingConstants.CENTER);
    labelPaint.setPreferredSize(new Dimension(450, 150));


    // adcionando todos os componentes ao JPanel
    //add(labelTextTransmissao);
    //add(bytesDeFlagRadioButton);
    //add(panelInv);
    add(labelProbabilidadeErro);
    add(sliderErro);
    add(panelInvDivisor);
    add(labelPaint);
    

    //radiobutton do bytesDeFlagRadioButton
    radioGroup2 = new ButtonGroup();
    radioGroup2.add(bytesDeFlagRadioButton);
    
    
  }

  
}
