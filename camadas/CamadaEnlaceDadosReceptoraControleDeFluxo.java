package camadas;

import util.Eventos;
import util.MeioDeComunicacao;
import camadas.CamadaEnlaceDadosTransmissoraControleDeFluxo;
import camadas.CamadaEnlaceDadosTransmissora;
import util.Quadro;
import java.util.ArrayList;

public class CamadaEnlaceDadosReceptoraControleDeFluxo{
    
    static int[] fluxoBrutoDeBits;
    static int[] buffer;
    public static Eventos tipo = Eventos.ENVIAR;
    static ArrayList<Integer> todosQuadros = new ArrayList<>();

    public static int[] camadaEnlaceDadosReceptoraControleDeFluxo(Quadro... quadro){
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(quadro);
        //MeioDeComunicacao.mutexMeio.release();
        
        return fluxoBrutoDeBits;

    }

    public static int[] camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(Quadro... quadro){
        
    
        

        
        //System.out.println(quadro[0].sequencia +" "+ CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado);

        if(CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado == quadro[0].sequencia){
            
            
            CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado += 1;
            MeioDeComunicacao.mutexMeio.release();
            //System.out.println("é quadro");
            todosQuadros.add(quadro[0].bits[0]);
            quadro[0].stopTemporizador();

            Quadro ack = new Quadro();
            ack.sequencia = 9999;
            ack.bits[0] = 184;
            ack.ACK = true;
            
            try {
                MeioDeComunicacao.mutexMeio.acquire();
            } catch (Exception e) {
               System.out.println("erro mutex na receptora");
            }
            CamadaEnlaceDadosTransmissoraControleDeErros.mandarACK(ack);
        } 
        
        if(quadro[0].ACK == true){
            
            
            System.out.println("ack");
            CamadaEnlaceDadosTransmissoraControleDeFluxo.nbuffer -= 1;
            MeioDeComunicacao.mutexMeio.release();
            
            CamadaEnlaceDadosTransmissoraControleDeFluxo.mutex.release();

            
            
        }

        buffer = new int[todosQuadros.size()];
       
        for(int i = 0; i < todosQuadros.size(); i++){
            buffer[i] = todosQuadros.get(i);
            //System.out.println(buffer[i]+" armazenado no buffer pra imprimir");
            
        }



        return buffer;
    }

    
}