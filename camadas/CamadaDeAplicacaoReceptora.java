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
      
      
      FramePrincipal.visualPC1.textBitsMensagem.setText(Conversao.bitsBrutosParaString(fluxoBrutoDeBits));
      FramePrincipal.visualPC1.textBitsMensagem.update(FramePrincipal.visualPC1.textBitsMensagem.getGraphics());

      try{
        Thread.sleep(600);
      } catch (Exception e){
        e.printStackTrace();
      }

      FramePrincipal.visualPC1.textASCII.setText(Conversao.showAscII(fluxoBrutoDeBits));
      FramePrincipal.visualPC1.textASCII.update(FramePrincipal.visualPC1.textASCII.getGraphics());

      try{
        Thread.sleep(600);
      } catch (Exception e){
        e.printStackTrace();
      }

      FramePrincipal.visualPC1.textArea.setText(Conversao.bitsBrutosParaMensagem(fluxoBrutoDeBits));
      
    } else if(pcReceptor == 2){

      FramePrincipal.visualPC2.textBitsMensagem.setText(Conversao.bitsBrutosParaString(fluxoBrutoDeBits));
      FramePrincipal.visualPC2.textBitsMensagem.update(FramePrincipal.visualPC2.textBitsMensagem.getGraphics());

      try{
        Thread.sleep(600);
      } catch (Exception e){
        e.printStackTrace();
      }

      FramePrincipal.visualPC2.textASCII.setText(Conversao.showAscII(fluxoBrutoDeBits));
      FramePrincipal.visualPC2.textASCII.update(FramePrincipal.visualPC2.textASCII.getGraphics());

      try{
        Thread.sleep(600);
      } catch (Exception e){
        e.printStackTrace();
      }

      FramePrincipal.visualPC2.textArea.setText(Conversao.bitsBrutosParaMensagem(fluxoBrutoDeBits));
    } else{

      FramePrincipal.visualPC3.textBitsMensagem.setText(Conversao.bitsBrutosParaString(fluxoBrutoDeBits));
      FramePrincipal.visualPC3.textBitsMensagem.update(FramePrincipal.visualPC3.textBitsMensagem.getGraphics());

      try{
        Thread.sleep(600);
      } catch (Exception e){
        e.printStackTrace();
      }

      FramePrincipal.visualPC3.textASCII.setText(Conversao.showAscII(fluxoBrutoDeBits));
      FramePrincipal.visualPC3.textASCII.update(FramePrincipal.visualPC3.textASCII.getGraphics());

      try{
        Thread.sleep(600);
      } catch (Exception e){
        e.printStackTrace();
      }

      FramePrincipal.visualPC3.textArea.setText(Conversao.bitsBrutosParaMensagem(fluxoBrutoDeBits));
    }
  }


}
