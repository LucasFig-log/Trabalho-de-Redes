package camadas;

import camadas.CamadaEnlaceDadosTransmissora;
import util.Conversao;
//import util.Eventos;

public class CamadaEnlaceDadosTransmissoraControleDeFluxo{

    

    public static void camadaEnlaceDadosTransmissoraControleDeFluxo(int quadro[]){
        
        camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(quadro);

    }

    public static void camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(int quadro[]){

        int[] quadroAsc = Conversao.bitsBrutosParaASCII(quadro);
        int[] envio = new int[1];
        int base = 0;
        int proximoSeq = 0;
        int nbuffer = 0;
        int MAXSEQ = 3;
        


        if (quadroAsc.length >= 4){
           int buffer[] = new int[MAXSEQ + 1];
        } else{
            int buffer[] = new int[quadroAsc.length - 1];
        }


        while(true){

            Eventos tipo = Eventos.ENVIAR;
            switch (tipo) {
                case tipo = Eventos.ENVIAR:
                    
                    break;
                case tipo = Eventos.RECEBER_ACK:
                    
                    
                    break;
                case tipo = Eventos.TIMEOUT:



                    break;
                default:
                    break;
            }

        }



        


        
    }
}