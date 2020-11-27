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
import view.PanelWest;
public class CamadaDeAplicacaoReceptora {

 /* ***************************************************************
  Metodo: camadaDeAplicacaoReceptora*
  Funcao: converte os bits decodificados em mensagem*
  Parametros: int fluxoBrutoDeBits[]*
  Retorno: void*
  *************************************************************** */
  public static void camadaDeAplicacaoReceptora(int fluxoBrutoDeBits[]) {
    
    aplicacaoReceptora(Conversao.bitsBrutosParaMensagem(fluxoBrutoDeBits));
    // if(CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroAscCorrigido.length == CamadaEnlaceDadosTransmissoraControleDeFluxo.proximoSeq){
    //   System.out.println("fim da mensagem");
    //   // CamadaEnlaceDadosTransmissoraControleDeFluxo.proximoSeq = 0;
    //   // CamadaEnlaceDadosTransmissoraControleDeFluxo.base = 0;
    //   // CamadaEnlaceDadosTransmissoraControleDeFluxo.nbuffer = 0;
    //   // CamadaEnlaceDadosReceptoraControleDeFluxo.todosQuadros.clear();
    //   CamadaEnlaceDadosTransmissoraControleDeFluxo.flag = true;

    // }
      
      

  }

/* ***************************************************************
  Metodo: aplicacaoReceptora*
  Funcao: imprime a mensagem decodificada*
  Parametros: String mensagem*
  Retorno: void*
  *************************************************************** */
  public static void aplicacaoReceptora(String mensagem) {
    FramePrincipal.imprimirNaTela(mensagem, FramePrincipal.TEXT_AREA_DECODIFICADO);
    PanelWest.send.setEnabled(true);
  }
}
