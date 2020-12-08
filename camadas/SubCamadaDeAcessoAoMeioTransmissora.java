package camadas;

import util.MeioDeComunicacao;
import java.lang.Math;

public class SubCamadaDeAcessoAoMeioTransmissora {
    
    public static int[] fluxoBrutoDeBits;

    public static void subCamadaDeAcessoAoMeioTransmissora(int quadros[], int pcTransmissor, int pcReceptor){
        
        fluxoBrutoDeBits = subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(quadros);

        MeioDeComunicacao.meioDeComunicacao(fluxoBrutoDeBits, pcTransmissor, pcReceptor);
    }

    public static int[] subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(int quadros[]){

        
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
