package camadas;

import util.Eventos;
import util.MeioDeComunicacao;
import camadas.CamadaEnlaceDadosTransmissoraControleDeFluxo;
import camadas.CamadaEnlaceDadosTransmissora;
import util.Quadro;
import java.util.ArrayList;

public class CamadaEnlaceDadosReceptoraControleDeFluxo{
    
    static int[] fluxoBrutoDeBits;
    static Eventos tipo = Eventos.ENVIAR;
    static ArrayList<Integer> todosQuadros = new ArrayList<>();

    public static void camadaEnlaceDadosReceptoraControleDeFluxo(Quadro... quadro){
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(quadro);
        MeioDeComunicacao.mutexMeio.release();
        


    }

    public static int[] camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(Quadro... quadro){
        
        
        
        
        int[] buffer;



        
        System.out.println(quadro[0].sequencia +" "+ CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado);

        if(CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado == quadro[0].sequencia){
            CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado = CamadaEnlaceDadosTransmissoraControleDeFluxo.proximoSeq;
            System.out.println("Entrou no if");
            todosQuadros.add(quadro[0].bits[0]);
            quadro[0].stopTemporizador();

            Quadro ack = new Quadro();
            ack.sequencia = 9999;
            ack.bits[0] = 97;
            ack.ACK = true;
            CamadaEnlaceDadosTransmissoraControleDeErros.mandarACK(ack);
        } 
        
        if(quadro[0].ACK == true){
            System.out.println("ack");
            CamadaEnlaceDadosTransmissoraControleDeFluxo.nbuffer -= 1;
            CamadaEnlaceDadosTransmissoraControleDeFluxo.mutex.release();
        }

        buffer = new int[todosQuadros.size()];
        
        for(int i = 0; i < todosQuadros.size(); i++){
            buffer[i] = todosQuadros.get(i);
            
        }



        return buffer;
    }

    
}