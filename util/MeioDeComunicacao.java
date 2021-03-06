package util;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import camadas.CamadaEnlaceDadosReceptora;
import view.FramePrincipal;
import view.PanelSouth;

public class MeioDeComunicacao {
  public static int tamanho;
  
  /* ***************************************************************
  Metodo: meioDeComunicacao*
  Funcao: transmitir a mensagem enviada*
  Parametros: int[] fluxoBrutoDeBits*
  Retorno: void*
  *************************************************************** */
  public static void meioDeComunicacao(int fluxoBrutoDeBits[]) {
    tamanho = fluxoBrutoDeBits.length;
    int[] fluxoBrutoDeBitsPontA = fluxoBrutoDeBits;
    int[] fluxoBrutoDeBitsPontB = new int[tamanho];
    int numero = 0;
    int cont = 1;
    int valorTransmitido = 0;
    int mask = 1 << 31;

    PanelSouth.fluxoDeBits.clear();

    for (int i = 0; i < tamanho; i++) {
      numero = fluxoBrutoDeBitsPontA[i];
      int checaBits = Integer.toBinaryString(numero).length();
      if (checaBits <= 8) {
        checaBits = 8;

      } else if (checaBits <= 16) {
        checaBits = 16;
      } else if (checaBits <= 24) {
        checaBits = 24;
      } else {
        checaBits = 32;
      }
      numero = numero << (32 - checaBits);

      while (cont <= checaBits) {
        if ((mask & numero) == 0) {
          valorTransmitido = valorTransmitido << 1;
          valorTransmitido = valorTransmitido | 0;
          PanelSouth.fluxoDeBits.add(0);

        } else {
          valorTransmitido = valorTransmitido << 1;
          valorTransmitido = valorTransmitido | 1;
          PanelSouth.fluxoDeBits.add(1);
        }
        numero = numero << 1;
        cont++;
      }
      fluxoBrutoDeBitsPontB[i] = valorTransmitido;
      valorTransmitido = 0;
      cont = 1;

    }
    PanelSouth.bandeira = true;
    FramePrincipal.rePintar();
    
    //envia os quadros para a camada enlace de dados receptora
    CamadaEnlaceDadosReceptora.camadaEnlaceDadosReceptora(fluxoBrutoDeBitsPontB);

  }

}
