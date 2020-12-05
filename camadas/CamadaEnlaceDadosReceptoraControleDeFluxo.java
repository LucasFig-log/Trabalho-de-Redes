package camadas;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import util.Eventos;
import util.MeioDeComunicacao;
import util.Quadro;
import view.PanelCenter;

import java.util.ArrayList;

public class CamadaEnlaceDadosReceptoraControleDeFluxo{
    
    static int[] fluxoBrutoDeBits;
    
    public static int MAX_SEQ = 3;
    public static Eventos tipo;
    public static int proximoQuadroEnviar = 0;
    public static int ackEsperado = 0;
    public static int quadroEsperado = 0;
    public static int nbuffer = 0;
    
    
    public static int i;
    
    
    /* ***************************************************************
    Metodo: camadaEnlaceDadosReceptoraControleDeFluxo*
    Funcao: executa o codigo de controle de fluxo go back n*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    public static void camadaEnlaceDadosReceptoraControleDeFluxo(Quadro... quadro){
        camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(quadro);
        

    }

    /* ***************************************************************
    Metodo: camadaEnlaceDadosReceptoraControleDeFluxoGoBackN*
    Funcao: executa o codigo de controle de fluxo go back n de recepcao*
    Parametros: Quadros[]*
    Retorno: void[]*
    *************************************************************** */
    public static void camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(Quadro... quadro){
        
            switch (tipo) {
            
                case RECEBER_QUADRO:
                    
                    if(quadro[0].sequencia == quadroEsperado){
                        
                        quadroEsperado++;
                        
                        CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora(quadro[0].bits);
                        MeioDeComunicacao.mutexMeio.release();
                        
                    }

                    while(entre(ackEsperado, quadro[0].ack, proximoQuadroEnviar)){
                        
                        nbuffer--;
                        quadro[0].stopTemporizador();
                        ackEsperado++;
                        PanelCenter.labelACK.setText("Recebimendo de Ack");
                        PanelCenter.labelACK.update(PanelCenter.labelACK.getGraphics());
                        if(!entre(ackEsperado, quadro[0].ack, proximoQuadroEnviar)){
                            
                            PanelCenter.labelACK.setText("Ack recebido");
                            PanelCenter.labelACK.update(PanelCenter.labelACK.getGraphics());
                        }

                    }
                    
                break;

                case ERRO:
                    MeioDeComunicacao.mutexMeio.release();
                    break;
            }

    
    }

    public static boolean entre(int a, int b, int c){
        
        if (((a <= b) && (b < c)) || ((c < a) && (a <= b)) || ((b < c) && (c < a)))
            return(true);
        else
            return(false);
        
    }

    
}