package camadas;

import util.Eventos;
import util.MeioDeComunicacao;
import util.Quadro;
import view.PanelCenter;

import java.util.ArrayList;

public class CamadaEnlaceDadosReceptoraControleDeFluxo{
    static boolean erro = false;
    static int[] fluxoBrutoDeBits;
    static int[] buffer;
    public static Eventos tipo = Eventos.ENVIAR;
    static ArrayList<Integer> todosQuadros = new ArrayList<>();

    public static int[] camadaEnlaceDadosReceptoraControleDeFluxo(Quadro... quadro){
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(quadro);
        
        return fluxoBrutoDeBits;

    }

    public static int[] camadaEnlaceDadosReceptoraControleDeFluxoGoBackN(Quadro... quadro){
    
        if(erro){
            CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado += 1;
            MeioDeComunicacao.mutexMeio.release();
        } else{
            if(CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado == quadro[0].sequencia){
                
                System.out.println("recebe quadro");
                CamadaEnlaceDadosTransmissoraControleDeFluxo.quadroEsperado += 1;
                MeioDeComunicacao.mutexMeio.release();
                
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
            
        }

        return buffer;
    }

    
}