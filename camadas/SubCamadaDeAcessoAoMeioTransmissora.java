package camadas;

import util.MeioDeComunicacao;

public class SubCamadaDeAcessoAoMeioTransmissora {
    
    public static int[] fluxoBrutoDeBits;

    public void subCamadaDeAcessoAoMeioTransmissora(int quadros[]){
        
        fluxoBrutoDeBits = subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(quadros);

        MeioDeComunicacao.meioDeComunicacao(fluxoBrutoDeBits);
    }

    public int[] subCamadaDeAcessoAoMeioTransmissoraCSMANaoPersistente(int quadros[]){

        return null;
    }
}
