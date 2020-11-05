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
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
  private JPanel panelInv;
  private Font font;
  private Font font2;

  /* ***************************************************************
  Metodo: PanelCenter*
  Funcao: Construtor da classe PanelCenter*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
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
   


    panelInv = new JPanel();
    panelInv.setPreferredSize(new Dimension(500, 200));
    panelInv.setBackground(cor);

    labelPaint = new JLabel("Sinais dos Bits Codificados");
    labelPaint.setFont(new Font("OldStyle", Font.BOLD, 18));
    labelPaint.setHorizontalAlignment(SwingConstants.CENTER);
    labelPaint.setPreferredSize(new Dimension(450, 100));

    // adcionando todos os componentes ao JPanel
    add(labelTextTransmissao);
    add(bytesDeFlagRadioButton);
    add(panelInv);
    add(labelPaint);


    radioGroup2 = new ButtonGroup();
    radioGroup2.add(bytesDeFlagRadioButton);
    
    
  }

  
}
