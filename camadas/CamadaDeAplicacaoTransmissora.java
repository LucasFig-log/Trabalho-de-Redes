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
import camadas.CamadaEnlaceDadosTransmissora;
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

    
      System.out.println("limpando");
      CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado = 0;
      CamadaEnlaceDadosTransmissoraControleDeFluxo.proximoSeq = 0;
      CamadaEnlaceDadosTransmissoraControleDeFluxo.base = 0;
      CamadaEnlaceDadosTransmissoraControleDeFluxo.nbuffer = 0;
      CamadaEnlaceDadosReceptoraControleDeErro.contAck = 0;
      CamadaEnlaceDadosReceptoraControleDeFluxo.todosQuadros.clear();
      CamadaEnlaceDadosTransmissoraControleDeFluxo.mutex = new Semaphore(0);
      MeioDeComunicacao.mutexMeio = new Semaphore(1);
      PanelCenter.labelACK.setText("Recebimendo de Ack");

    FramePrincipal.limparCamposTexto();
    int quadro[] = new int[mensagem.length()];

    for (int i = 0; i < mensagem.length(); i++) {
      quadro[i] = mensagem.charAt(i);
    }
    // passando o vetor com os numeros de cada caractere para a CamadaFisicaTransmissora
    
    //imprime na caixa de texto os caracteres e seus valores ascII
    Conversao.showAscII(quadro, FramePrincipal.TEXT_ASCII);  
    
    //converte a mensagem para bits
    quadro = Conversao.asciiParaBits(quadro);
    
    Quadro quadroEnquadrado = new Quadro();
    quadroEnquadrado.bits = quadro;
    
    CamadaEnlaceDadosTransmissora.camadaEnlaceDadosTransmissora(quadroEnquadrado);
  }
}
