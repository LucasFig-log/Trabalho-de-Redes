package camadas;

import util.Conversao;
import util.Eventos;
import java.util.concurrent.Semaphore;
import util.MeioDeComunicacao;
import util.Quadro;
import view.PanelCenter;

public class CamadaEnlaceDadosTransmissoraControleDeFluxo{

 
    public static Semaphore mutex = new Semaphore(0);
    public static Quadro[] buffer = new Quadro[CamadaEnlaceDadosReceptoraControleDeFluxo.MAX_SEQ + 2];
    public static int indexBuffer = 0;
    public static int[] envio;
    public static int[] send = new int[1];
 
   
    
    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeFluxo*
    Funcao: executa o codigo de controle de fluxo go back n*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    public static void camadaEnlaceDadosTransmissoraControleDeFluxo(Quadro... quadro){
        
        camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(quadro);

    }

    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN*
    Funcao: executa o codigo de controle de fluxo go back n*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    public static void camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(Quadro... quadro){
        
        envio = new int[quadro[0].bits.length];
        for(int i = 0; i< quadro[0].bits.length; i++){
            envio[i] = quadro[0].bits[i];
            
        }

        inicializarBuffer(quadro);
       
        //envia quadro por quadro
        for(int j = 0; j < envio.length; j++){
            
            
        try {
            
            
            send[0] = envio[j];
            if(CamadaEnlaceDadosReceptoraControleDeFluxo.nbuffer < CamadaEnlaceDadosReceptoraControleDeFluxo.MAX_SEQ){
                MeioDeComunicacao.mutexMeio.acquire();
                
                enviarQuadro(CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar, CamadaEnlaceDadosReceptoraControleDeFluxo.quadroEsperado, buffer, send);
                
                CamadaEnlaceDadosReceptoraControleDeFluxo.nbuffer++;
                CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar++; 
            }else{
                inicializarBuffer(quadro);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro mutex meio transmissora"  );
        }
        }
            
        
    }

    /* ***************************************************************
    Metodo: inicializarBuffer*
    Funcao: inicializa o buffer que armazena a janela*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    private static void inicializarBuffer(Quadro... quadro){
        
        for(int j = 0; j < buffer.length; j++, indexBuffer++){
            
            if(indexBuffer < quadro.length){
                buffer[j] = quadro[indexBuffer];
                
            }
        }
        
        
    }

    /* ***************************************************************
    Metodo: enviarQuadro*
    Funcao: enviar o quadro para o meio de comunicacao*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    public static void enviarQuadro(int proximoEnviar, int quadroEsperado, Quadro[] buffer, int[] envio){
        Quadro frame = new Quadro();
        int a = CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar;
        if(CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar > CamadaEnlaceDadosReceptoraControleDeFluxo.MAX_SEQ){
            CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar = CamadaEnlaceDadosReceptoraControleDeFluxo.MAX_SEQ;
        }
        frame.buffer = buffer[CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar];
         
        frame.sequencia = a;
        frame.ack = ((CamadaEnlaceDadosReceptoraControleDeFluxo.quadroEsperado + CamadaEnlaceDadosReceptoraControleDeFluxo.MAX_SEQ) % (CamadaEnlaceDadosReceptoraControleDeFluxo.MAX_SEQ + 1));
        frame.bits = envio;
        frame.temporizador(15);
        
        

        MeioDeComunicacao.meioDeComunicacao(frame);

    }

    
}