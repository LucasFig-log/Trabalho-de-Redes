package util;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 27/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import camadas.CamadaEnlaceDadosReceptora;
import view.FramePrincipal;
import view.PanelSouth;
import view.PanelCenter;
import java.util.concurrent.Semaphore;
import java.lang.Math;

public class MeioDeComunicacao {
  public static int tamanho;

  public static Semaphore mutexMeio = new Semaphore(1);
  
  /* ***************************************************************
  Metodo: meioDeComunicacao*
  Funcao: transmitir a mensagem enviada*
  Parametros: int[] fluxoBrutoDeBits*
  Retorno: void*
  *************************************************************** */
  public static void meioDeComunicacao(Quadro... quadros) {
    tamanho = quadros.length;
   
    int[] quadroConvertido = Conversao.asciiParaBits(quadros[0].bits);
    
    int[] fluxoBrutoDeBitsPontA = quadroConvertido;
    int[] fluxoBrutoDeBitsPontB = new int[tamanho];
    

    new Thread(new Runnable(){
      int numero = 0;
      int cont = 1;
      int valorTransmitido = 0;
      int mask = 1 << 31;
      int contRuido = 0;

      @Override
      public void run(){
      PanelSouth.fluxoDeBits.clear();
        

      if (Math.random() <= PanelCenter.probabilidade){ //Caso de erro
          
          
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
                if (contRuido == 0){
                  valorTransmitido = valorTransmitido << 1;
                  valorTransmitido = valorTransmitido | 0;
                  PanelSouth.fluxoDeBits.add(0);
                  contRuido++;
                } else{
                  valorTransmitido = valorTransmitido << 1;
                  valorTransmitido = valorTransmitido | 1;
                  PanelSouth.fluxoDeBits.add(1);
                }
              }
              numero = numero << 1;
              cont++;
            }
            fluxoBrutoDeBitsPontB[i] = valorTransmitido;
            valorTransmitido = 0;
            cont = 1;
      
          }
      } else{ //Caso sem erro
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
      }

      try{
        PanelSouth.bandeira = true;
        FramePrincipal.rePintar();
        PanelSouth.mutex.acquire();
      }catch (InterruptedException e){
        System.out.println("Erro ao travar o painel sul");
      } 
   
    
    //envia os quadros para a camada enlace de dados receptora
    quadros[0].bits = fluxoBrutoDeBitsPontB;
    
    CamadaEnlaceDadosReceptora.camadaEnlaceDadosReceptora(quadros);
      }

    }).start(); 

    

  }

}
