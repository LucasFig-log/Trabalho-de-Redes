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
import view.FramePrincipal;

public class CamadaDeAplicacaoTransmissora {

  /* ***************************************************************
  Metodo: camadaDeAplicacaoTransmissora*
  Funcao: transformar os caracteres em valores com base na tabela ascII*
  Parametros: String mensagem*
  Retorno: void*
  *************************************************************** */
  public static void camadaDeAplicacaoTransmissora(String mensagem) {

    //FramePrincipal.limparCamposTexto();
    int quadro[] = new int[mensagem.length()];

    for (int i = 0; i < mensagem.length(); i++) {
      quadro[i] = mensagem.charAt(i);
    }
    // passando o vetor com os numeros de cada caractere para a CamadaFisicaTransmissora
    
    //imprime na caixa de texto os caracteres e seus valores ascII
    Conversao.showAscII(quadro, 7);  
    
    //converte a mensagem para bits
    quadro = Conversao.asciiParaBits(quadro);
    
    
    
  }
}
