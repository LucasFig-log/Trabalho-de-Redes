package camadas;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import util.Conversao;
import util.MeioDeComunicacao;
import view.FramePrincipal;
import view.PanelVisualComputador;

public class CamadaDeAplicacaoTransmissora {

  /* ***************************************************************
  Metodo: camadaDeAplicacaoTransmissora*
  Funcao: transformar os caracteres em valores com base na tabela ascII*
  Parametros: String mensagem*
  Retorno: void*
  *************************************************************** */
  public static void camadaDeAplicacaoTransmissora(String mensagem, int pcTransmissor, int pcReceptor) {

    //FramePrincipal.limparCamposTexto();
    int quadro[] = new int[mensagem.length()];

    for (int i = 0; i < mensagem.length(); i++) {
      quadro[i] = mensagem.charAt(i);
    }
   
    //imprime na caixa de texto os caracteres e seus valores ascII
    String mensagemAscii =  Conversao.showAscII(quadro);  
    String mensagemBits = Conversao.bitsBrutosParaString(quadro);

    FramePrincipal.imprimirNaTela(mensagemAscii, mensagemBits, pcTransmissor);
    
    
    //converte a mensagem para bits
    quadro = Conversao.asciiParaBits(quadro);

    SubCamadaDeAcessoAoMeioTransmissora.subCamadaDeAcessoAoMeioTransmissora(quadro, pcTransmissor, pcReceptor);
    
    
    
  }
}
