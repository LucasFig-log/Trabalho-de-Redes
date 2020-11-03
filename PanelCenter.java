
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
    setPreferredSize(new Dimension(300, 400));
    font = new Font("OldStyle", Font.BOLD, 14);
    font2 = new Font("OldStyle", Font.BOLD, 13);

    // configuracoes do JLabel com o texto especifico
    labelText = new JLabel("Escolha um tipo de codificação");
    labelText.setFont(font);
    labelText.setPreferredSize(new Dimension(250, 50));

    // configurando os JRadioButtons para a selecao dos tipos de codificacao
    binarioRadioButton = new JRadioButton("Codificação Binária", true);
    binarioRadioButton.setBackground(cor);
    manchesterRadioButton = new JRadioButton("Codificação Manchester", false);
    manchesterRadioButton.setBackground(cor);
    manchesterDifRadioButton = new JRadioButton("Codificação Manchester Diferencial", false);
    manchesterDifRadioButton.setBackground(cor);

    // configuracoes do JLabel com o texto especifico
    labelTextTransmissao = new JLabel("Tipo de Enquadramento");
    labelTextTransmissao.setFont(font2);
    labelTextTransmissao.setPreferredSize(new Dimension(180, 50));

    // configurando os JRadioButtons para a selecao dos tipos de codificacao
    //contagemDeCaracteresRadioButton = new JRadioButton("Contagem De Caracteres", true);
    //contagemDeCaracteresRadioButton.setBackground(cor);
    bytesDeFlagRadioButton = new JRadioButton("Inserção de Bytes", true);
    bytesDeFlagRadioButton.setBackground(cor);
    //flagsIniciaisFinaisRadioButton = new JRadioButton("Flags Inicias e Finais, Com Bits", false);
    //flagsIniciaisFinaisRadioButton.setBackground(cor);
    //violacaoCamadaFisicaRadioButton = new JRadioButton(" Violação da Camada Física", false);
    //violacaoCamadaFisicaRadioButton.setBackground(cor);


    panelInv = new JPanel();
    panelInv.setPreferredSize(new Dimension(500, 200));
    panelInv.setBackground(cor);

    labelPaint = new JLabel("Sinais dos Bits Codificados");
    labelPaint.setFont(new Font("OldStyle", Font.BOLD, 18));
    labelPaint.setHorizontalAlignment(SwingConstants.CENTER);
    labelPaint.setPreferredSize(new Dimension(500, 25));

    // adcionando todos os componentes ao JPanel
    add(labelText);
    add(binarioRadioButton);
    add(manchesterRadioButton);
    add(manchesterDifRadioButton);
    add(labelTextTransmissao);
    //add(contagemDeCaracteresRadioButton);
    add(bytesDeFlagRadioButton);
    //add(violacaoCamadaFisicaRadioButton);
    //add(flagsIniciaisFinaisRadioButton);
    add(panelInv);
    add(labelPaint);

    // criando o objeto radioGroup que é resposavel por gerenciar as escolhas e
    // adcionando os radiobuttons nele
    radioGroup = new ButtonGroup();
    radioGroup.add(binarioRadioButton);
    radioGroup.add(manchesterRadioButton);
    radioGroup.add(manchesterDifRadioButton);

    //
    binarioRadioButton.addItemListener(new RadioButtonHandler());
    manchesterRadioButton.addItemListener(new RadioButtonHandler());
    manchesterDifRadioButton.addItemListener(new RadioButtonHandler());

    radioGroup2 = new ButtonGroup();
    //radioGroup2.add(contagemDeCaracteresRadioButton);
    radioGroup2.add(bytesDeFlagRadioButton);
    //radioGroup2.add(flagsIniciaisFinaisRadioButton);
    //radioGroup2.add(violacaoCamadaFisicaRadioButton);

    //contagemDeCaracteresRadioButton.addItemListener(new RadioButtonHandler());
    bytesDeFlagRadioButton.addItemListener(new RadioButtonHandler());
    //flagsIniciaisFinaisRadioButton.addItemListener(new RadioButtonHandler());
    //violacaoCamadaFisicaRadioButton.addItemListener(new RadioButtonHandler());
  }

  // classe privada RadioButton responsavel por tratar os eventos dos radiobuttons
  private class RadioButtonHandler implements ItemListener {

    /* ***************************************************************
  Metodo: itemStateChanged*
  Funcao: manopular evento de selecao*
  Parametros: ItemEvent event*
  Retorno: void*
  *************************************************************** */
    public void itemStateChanged(ItemEvent event) {
      int indexSelecionado;
      int indexSelecionado2 = 2;

      if (binarioRadioButton.isSelected()) {
        indexSelecionado = 0;
      } else if (manchesterRadioButton.isSelected()) {
        indexSelecionado = 2;
      } else {
        indexSelecionado = 3;
      }

      

      
	
      CamadaFisicaTransmissora.setIndexSelecionado(indexSelecionado); //envia o index do RadioButton selecionado
      
      CamadaEnlaceDadosTransmissoraEnquadramento.setIndexSelecionado(indexSelecionado2); //envia o index do RadioButton selecionado
    }

  }
}
