package camadas;

import util.Conversao;

public class SubCamadaDeAcessoAoMeioReceptora {
    
    public static int[] fluxoBrutoDeBits;

    public static void subCamadaDeAcessoAoMeioReceptora(int quadros[], int pcTransmissor, int pcReceptor){
        fluxoBrutoDeBits = subCamadaDeAcessoAoMeioReceptoraCSMANaoPersistente(quadros);

        CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora(fluxoBrutoDeBits, pcTransmissor, pcReceptor);
    }

    public static int[] subCamadaDeAcessoAoMeioReceptoraCSMANaoPersistente(int[] quadros){
        
        
        int[] mensagem = Conversao.bitsBrutosParaASCII(quadros);
        return mensagem;
    }
}
