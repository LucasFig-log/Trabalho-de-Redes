package util;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import view.FramePrincipal;
import view.PanelSouth;
import camadas.SubCamadaDeAcessoAoMeioReceptora;

import java.util.ArrayList;




public class MeioDeComunicacao {
  public static int tamanho;
  
  public static ArrayList<Integer> fluxoBrutoDeBitsPontA = new ArrayList<Integer>(); //buffer para o envio de mensagem
  public static ArrayList<Integer> fluxoBrutoDeBitsPontB = new ArrayList<Integer>(); //buffer para o envio de mensagem

  /* ***************************************************************
  Metodo: meioDeComunicacao*
  Funcao: transmitir a mensagem enviada*
  Parametros: int[] fluxoBrutoDeBits*
  Retorno: void*
  *************************************************************** */
  public static void meioDeComunicacao(int fluxoBrutoDeBits[], int pcTransmissor, int pcReceptor) {
    
    //passa os bits recebidos para o buffer
    for(int i = 0; i < fluxoBrutoDeBits.length; i++){
      fluxoBrutoDeBitsPontA.add(fluxoBrutoDeBits[i]);
      
    }
    
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      System.out.println("erro sleep do meio");
    }

    int numero = 0;
    int cont = 1;
    int valorTransmitido = 0;
    int mask = 1 << 31;
    

    
    PanelSouth.fluxoDeBits.clear();
      

    //executa a transferencia
    for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
      numero = fluxoBrutoDeBitsPontA.get(i);
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
      
      fluxoBrutoDeBitsPontB.add(valorTransmitido);
      valorTransmitido = 0;
      cont = 1;

    }
    
    int[] transmissao = new int[fluxoBrutoDeBitsPontB.size()];

    //passando para o vetor de transmissao
    for(int i = 0; i < fluxoBrutoDeBitsPontB.size(); i++){
      transmissao[i] = fluxoBrutoDeBitsPontB.get(i);
    }

    //limpando os buffers para liberar o meio
    fluxoBrutoDeBitsPontA.clear();
    fluxoBrutoDeBitsPontB.clear();
   
    
    try{
      PanelSouth.bandeira = true;
      FramePrincipal.rePintar();
      PanelSouth.mutex.acquire();
    }catch (InterruptedException e){
      System.out.println("Erro ao travar o painel sul");
    } 
    
    
  
    //envia os quadros para a camada enlace de dados receptora
    SubCamadaDeAcessoAoMeioReceptora.subCamadaDeAcessoAoMeioReceptora(transmissao, pcTransmissor, pcReceptor);  


    

  }

}
