package camadas;

import camadas.CamadaEnlaceDadosTransmissora;
import util.Conversao;
import util.Eventos;
import camadas.CamadaEnlaceDadosReceptoraControleDeFluxo;
import util.MeioDeComunicacao;
import util.Quadro;

public class CamadaEnlaceDadosTransmissoraControleDeFluxo{

    
    public static int proximoSeq = 0;
    
    public static void camadaEnlaceDadosTransmissoraControleDeFluxo(int quadro[]){
        
        camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(quadro);

    }

    public static void camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(int quadro[]){

        int[] quadroAsc = Conversao.bitsBrutosParaASCII(quadro);
        int[] envio = new int[1];
        int base = 0;
        
        int nbuffer = 0;
        int MAXSEQ = 3;
        int[] buffer;


        if (quadroAsc.length >= 4){
            buffer = new int[MAXSEQ + 1];
        } else{
            buffer = new int[quadroAsc.length - 1];
        }


        while(true){

            
            switch (CamadaEnlaceDadosReceptoraControleDeFluxo.tipo) {
                case ENVIAR:
                    Quadro quadroEnviado = new Quadro();
                    envio[0] = quadroAsc[proximoSeq];
                    quadroEnviado.bits = envio;
                    quadroEnviado.buffer = envio;
                    buffer[proximoSeq] = quadroAsc[proximoSeq];
                    quadroEnviado.ack = ((proximoSeq + MAXSEQ) % (MAXSEQ + 1));
                    nbuffer += 1;
                    proximoSeq += 1;
                    quadroEnviado.temporizador(5);
                    MeioDeComunicacao.meioDeComunicacao(quadroEnviado);

                    
                    System.out.println("Enviar quadro");
                    CamadaEnlaceDadosReceptoraControleDeFluxo.tipo = Eventos.RECEBER_ACK;
                    
                    break;
                case  RECEBER_ACK:
                    
                    
                    break;
                case  TIMEOUT:
                    


                    break;
                default:
                    break;
            }

            // if (nbuffer < MAXSEQ){
            //     CamadaEnlaceDadosReceptoraControleDeFluxo.tipo = Eventos.ENVIAR;
            // } else{
            //     CamadaEnlaceDadosReceptoraControleDeFluxo.tipo = Eventos.RECEBER_ACK;
            // }

            

        }



        


        
    }
}