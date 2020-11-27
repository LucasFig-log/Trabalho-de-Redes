package camadas;

import util.Conversao;
import util.Eventos;
import java.util.concurrent.Semaphore;
import util.MeioDeComunicacao;
import util.Quadro;

public class CamadaEnlaceDadosTransmissoraControleDeFluxo{

    public static int nbuffer = 0;
    public static Semaphore mutex = new Semaphore(0);
    public static int proximoSeq = 0;
    public static int quadroEsperado = 0;
    public static int base = 0;
    public static boolean flag = false;
    public static int [] quadroAscCorrigido;
    
    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeFluxo*
    Funcao: executa o codigo de controle de fluxo go back n*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    public static void camadaEnlaceDadosTransmissoraControleDeFluxo(Quadro... quadro){
        
        camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(quadro[0]);

    }

    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN*
    Funcao: executa o codigo de controle de fluxo go back n*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    public static void camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(Quadro quadro){

        
        new Thread(new Runnable(){
            @Override
            public void run(){
                
                int[] quadroAsc = Conversao.bitsBrutosParaASCII(quadro.bits); 
                int[] envio = new int[1];
                int base = 0;
                int cont = 0;

                for(int i = 0; i < quadroAsc.length; i++){
                    if(quadroAsc[i] == 0){
                        cont++;
                    }
                }
                
                quadroAscCorrigido = new int[quadroAsc.length - cont];
                cont = 0;
                for(int i = 0; i <quadroAsc.length; i++){
                    if(quadroAsc[i] != 0){
                        quadroAscCorrigido[i-cont] = quadroAsc[i];
                    } else{
                        cont++;
                    }
                }
                
                int MAXSEQ = 3;
                int[] buffer;


                if (quadroAsc.length >= 4){
                    buffer = new int[MAXSEQ + 1];
                } else{
                    buffer = new int[quadroAscCorrigido.length - 1];
                }
                
                
                
                while(true){
                    
                    
                    if(quadroAscCorrigido.length == proximoSeq){
                        
                        break;
                                   
                        
                    } else{
                        
                        try {
                            MeioDeComunicacao.mutexMeio.acquire();
                        } catch (Exception e) {
                            System.out.println("mutex do meio");
                        }
                        switch (CamadaEnlaceDadosReceptoraControleDeFluxo.tipo) {
                            case ENVIAR:
                                Quadro quadroEnviado = new Quadro();
                                envio[0] = quadroAscCorrigido[proximoSeq];
                                quadroEnviado.bits = envio;
                                quadroEnviado.buffer = envio;
                                quadroEnviado.sequencia = proximoSeq;
                                buffer[base] = quadroAscCorrigido[proximoSeq];
                                quadroEnviado.ack = ((proximoSeq + MAXSEQ) % (MAXSEQ + 1));
                                nbuffer += 1;
                                base += 1;
                                proximoSeq += 1;
                                quadroEnviado.temporizador(10);
                                System.out.println(quadroEnviado.bits[0]);
                                System.out.println("Enviar quadro, buffer antes de enviar " + nbuffer);
                                MeioDeComunicacao.meioDeComunicacao(quadroEnviado);
                                
                                break; 
                            case  TIMEOUT:
                                System.out.println("Entrou no timeout");
                                break;
                            default:
                                break;
                        }
                        

                        if (nbuffer < MAXSEQ){
                            CamadaEnlaceDadosReceptoraControleDeFluxo.tipo = Eventos.ENVIAR;
                        } else{
                            System.out.println("else nbuffer ");
                            
                            try {
                            mutex.acquire();
                                
                            } catch (Exception e) {
                                System.out.println("mutex do else");
                            }
                        }

                        if(base == 4){
                            base = 0;
                        }
                    }
                } 
            }
        }).start();
        
    }

    
}