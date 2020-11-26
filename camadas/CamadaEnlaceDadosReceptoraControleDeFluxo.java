package camadas;

import util.Eventos;
import camadas.CamadaEnlaceDadosTransmissoraControleDeFluxo;
import camadas.CamadaEnlaceDadosTransmissora;
import util.Quadro;
import java.util.ArrayList;

public class CamadaEnlaceDadosReceptoraControleDeFluxo{
    
    static int[] fluxoBrutoDeBits;
    static Eventos tipo = Eventos.ENVIAR;

    public static void camadaEnlaceDadosReceptoraControleDeFluxo(Quadro... quadro){
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(quadro);


        CamadaEnlaceDadosReceptora.bitsQuadro = fluxoBrutoDeBits;

    }

    public static int[] camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(Quadro... quadro){
        
        
        int quadroEsperado = CamadaEnlaceDadosTransmissoraControleDeFluxo.proximoSeq;
        ArrayList<Integer> todosQuadros = new ArrayList<>();
        int[] buffer;




        if(quadroEsperado == quadro[0].sequencia){
            todosQuadros.add(quadro[0].bits[0]);
            quadro[0].stopTemporizador();
            Quadro ack = new Quadro();
            ack.ACK = true;
            CamadaEnlaceDadosTransmissora.camadaEnlaceDadosTransmissora(ack);
        } else if(quadro[0].ACK == true){
            //decresce no nbuffer e manda um quadro sem ser ack
        }

        buffer = new int[todosQuadros.size()];
        for(int i = 0; i < todosQuadros.size(); i++){
            buffer[i] = todosQuadros.get(i);
            System.out.println(buffer[i]);
        }



        return buffer;
    }

    
}