package camadas;

import util.MeioDeComunicacao;

public class SubCamadaDeAcessoAoMeioTransmissora {
    
    public static int[] fluxoBrutoDeBits;

    public static void subCamadaDeAcessoAoMeioTransmissora(int quadros[], int pcTransmissor, int pcReceptor){
        
        fluxoBrutoDeBits = subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(quadros);

        MeioDeComunicacao.meioDeComunicacao(fluxoBrutoDeBits, pcTransmissor, pcReceptor);
    }

    public static int[] subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(int quadros[]){

        return quadros;
    }
}
