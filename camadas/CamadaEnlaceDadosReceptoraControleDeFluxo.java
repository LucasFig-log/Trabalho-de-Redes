package camadas;

import util.Eventos;
import camadas.CamadaEnlaceDadosTransmissoraControleDeFluxo;
import util.Quadro;

public class CamadaEnlaceDadosReceptoraControleDeFluxo{
    
    static int[] fluxoBrutoDeBits;
    static Eventos tipo = Eventos.ENVIAR;

    public static void camadaEnlaceDadosReceptoraControleDeFluxo(Quadro... quadro){
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(quadro[0].bits);

        CamadaEnlaceDadosReceptora.bitsQuadro = fluxoBrutoDeBits;

    }

    public static int[] camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(int quadro[]){
        
        
        int quadroEsperado = CamadaEnlaceDadosTransmissoraControleDeFluxo.proximoSeq;
        int[] buffer;




        if(quadroEsperado == quadro[0]){
            //salvo o quadro
            //mando o ack
            //atualizo o quadro esperado

        }
        return null;
    }

    
}