package camadas;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import util.MeioDeComunicacao;
import java.lang.Math;


public class SubCamadaDeAcessoAoMeioTransmissora {
    
    public static int[] fluxoBrutoDeBits;

    /* ***************************************************************
    Metodo: subCamadaDeAcessoAoMeioTransmissora*
    Funcao: Checa se o meio de comunicacao esta livre para o envio da mensagem*
    Parametros: int[] quadros, int pcTransmissor, int pcReceptor*
    Retorno: void*
    *************************************************************** */
    public static void subCamadaDeAcessoAoMeioTransmissora(int quadros[], int pcTransmissor, int pcReceptor){
        
        fluxoBrutoDeBits = subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(quadros);
        //passa para o meio de comunicacao
        MeioDeComunicacao.meioDeComunicacao(fluxoBrutoDeBits, pcTransmissor, pcReceptor);
    }

    public static int[] subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(int quadros[]){

        //realiza a checagem se o meio de comunicacao esta livre
        while(true){
            if(MeioDeComunicacao.fluxoBrutoDeBitsPontA.isEmpty()){
                return quadros;
            } else{
                try {
                    Thread.sleep((int)(Math.random()*10) + 1);
                } catch (Exception e) {
                    System.out.println("erro na espera do acesso ao meio");
                }
            }   
        }
        
    }
}
