package camadas;
import java.util.concurrent.Semaphore;

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
import view.PanelCenter;
import util.Quadro;

public class CamadaDeAplicacaoTransmissora {

  /* ***************************************************************
  Metodo: camadaDeAplicacaoTransmissora*
  Funcao: transformar os caracteres em valores com base na tabela ascII*
  Parametros: String mensagem*
  Retorno: void*
  *************************************************************** */
  public static void camadaDeAplicacaoTransmissora(String mensagem) {

      //Redefinindo as variaveis para enviar a mensagem
      
      CamadaDeAplicacaoReceptora.mensagemConcatenada = "";
      CamadaEnlaceDadosReceptoraControleDeFluxo.ackEsperado = 0;
      CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar = 0;
      CamadaEnlaceDadosReceptoraControleDeFluxo.quadroEsperado = 0;
      CamadaEnlaceDadosReceptoraControleDeFluxo.nbuffer = 0;
      CamadaEnlaceDadosTransmissoraControleDeFluxo.buffer = new Quadro[CamadaEnlaceDadosReceptoraControleDeFluxo.MAX_SEQ + 1];
      CamadaEnlaceDadosTransmissoraControleDeFluxo.indexBuffer = 0;
      
      CamadaEnlaceDadosTransmissoraControleDeFluxo.mutex = new Semaphore(0);
      MeioDeComunicacao.mutexMeio = new Semaphore(1);
      

    FramePrincipal.limparCamposTexto();
    int quadro[] = new int[mensagem.length()];

    for (int i = 0; i < mensagem.length(); i++) {
      quadro[i] = mensagem.charAt(i);
    }
    
    
    //imprime na caixa de texto os caracteres e seus valores ascII
    Conversao.showAscII(quadro, FramePrincipal.TEXT_ASCII);  
    
    //converte a mensagem para bits
    quadro = Conversao.asciiParaBits(quadro);
    
    Quadro quadroEnquadrado = new Quadro();
    quadroEnquadrado.bits = quadro;
    
    CamadaEnlaceDadosTransmissora.camadaEnlaceDadosTransmissora(quadroEnquadrado);
  }
  
}

