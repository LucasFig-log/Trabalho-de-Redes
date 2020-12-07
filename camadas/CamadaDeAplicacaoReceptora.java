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

public class CamadaDeAplicacaoReceptora {

 /* ***************************************************************
  Metodo: camadaDeAplicacaoReceptora*
  Funcao: converte os bits decodificados em mensagem*
  Parametros: int fluxoBrutoDeBits[]*
  Retorno: void*
  *************************************************************** */
  public static void camadaDeAplicacaoReceptora(int fluxoBrutoDeBits[], int pcTransmissor, int pcReceptor) {
    if(pcReceptor == 1){
      FramePrincipal.visualPC1.textArea.setText(Conversao.bitsBrutosParaMensagem(fluxoBrutoDeBits));
    } else if(pcReceptor == 2){
      FramePrincipal.visualPC2.textArea.setText(Conversao.bitsBrutosParaMensagem(fluxoBrutoDeBits));
    } else{
      FramePrincipal.visualPC3.textArea.setText(Conversao.bitsBrutosParaMensagem(fluxoBrutoDeBits));
    }
  }


}
