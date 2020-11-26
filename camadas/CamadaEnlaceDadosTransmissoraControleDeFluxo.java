package camadas;

import camadas.CamadaEnlaceDadosTransmissora;
import util.Conversao;
import util.Eventos;

import java.util.concurrent.Semaphore;

import camadas.CamadaEnlaceDadosReceptoraControleDeFluxo;
import util.MeioDeComunicacao;
import util.Quadro;

public class CamadaEnlaceDadosTransmissoraControleDeFluxo{

    public static int nbuffer = 0;
    public static Semaphore mutex = new Semaphore(0);
    public static int proximoSeq = 0;
    public static int quadroEsperado = 0;
    
    public static void camadaEnlaceDadosTransmissoraControleDeFluxo(Quadro... quadro){
        
        camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(quadro[0]);

    }

    public static void camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(Quadro quadro){

        

        
        new Thread(new Runnable(){
            @Override
            public void run(){
                
                int[] quadroAsc = Conversao.bitsBrutosParaASCII(quadro.bits);
                int[] envio = new int[1];
                int base = 0;
        
                
                int MAXSEQ = 3;
                int[] buffer;


                if (quadroAsc.length >= 4){
                    buffer = new int[MAXSEQ + 1];
                } else{
                    buffer = new int[quadroAsc.length - 1];
                }
                
                
                
                while(true){

                    try {

                        MeioDeComunicacao.mutexMeio.acquire();
                        switch (CamadaEnlaceDadosReceptoraControleDeFluxo.tipo) {
                            case ENVIAR:
                                Quadro quadroEnviado = new Quadro();
                                envio[0] = quadroAsc[proximoSeq];
                                quadroEnviado.bits = envio;
                                quadroEnviado.buffer = envio;
                                quadroEnviado.sequencia = proximoSeq;
                                buffer[proximoSeq] = quadroAsc[proximoSeq];
                                quadroEnviado.ack = ((proximoSeq + MAXSEQ) % (MAXSEQ + 1));
                                nbuffer += 1;
                                proximoSeq += 1;
                                quadroEnviado.temporizador(10);
                                System.out.println("Enviar quadro");
                                MeioDeComunicacao.meioDeComunicacao(quadroEnviado);
            
                                
                                
                                
                                
                                break;
                            case  RECEBER_ACK:
                                
                                
                                break;
                            case  TIMEOUT:
                                
            
            
                                break;
                            default:
                                break;
                        }
            
                        if (nbuffer < MAXSEQ){
                            CamadaEnlaceDadosReceptoraControleDeFluxo.tipo = Eventos.ENVIAR;
                        } else{
                            
                            mutex.acquire();
                
                        }
            
                    }
                     catch (Exception e) {
                        System.out.println("Erro no mutex, transição");
                }
            }   
        
            }
        }).start();
        
    }

    
}