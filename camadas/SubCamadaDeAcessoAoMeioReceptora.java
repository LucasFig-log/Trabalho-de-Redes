package camadas;

import util.Conversao;

public class SubCamadaDeAcessoAoMeioReceptora {
    
    public static int[] fluxoBrutoDeBits;

    /* ***************************************************************
    Metodo: subCamadaDeAcessoAoMeioReceptora*
    Funcao: repassa a mensagem recebida para a camada de aplicacao receptora*
    Parametros: int[] quadros, int pcTransmissor, int pcReceptor*
    Retorno: void*
    *************************************************************** */
    public static void subCamadaDeAcessoAoMeioReceptora(int quadros[], int pcTransmissor, int pcReceptor){
        fluxoBrutoDeBits = subCamadaDeAcessoAoMeioReceptoraCSMANaoPersistente(quadros);
        //envia para a aplicacao receptora
        CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora(fluxoBrutoDeBits, pcTransmissor, pcReceptor);
    }

    public static int[] subCamadaDeAcessoAoMeioReceptoraCSMANaoPersistente(int[] quadros){
        
        //converte os bits em mensagem
        int[] mensagem = Conversao.bitsBrutosParaASCII(quadros);
        return mensagem;
    }
}
