package camadas;

import camadas.CamadaEnlaceDadosTransmissora;
import util.Conversao;

public class CamadaEnlaceDadosTransmissoraControleDeFluxo{

    public static int[] fluxoDeBits;

    public static void camadaEnlaceDadosTransmissoraControleDeFluxo(int quadro[]){
        
        fluxoDeBits = camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(quadro);

    }

    public int[] camadaEnlaceDadosTransmissoraControleDeFluxoGoBackN(int quadro[]){

        int [] quadroAsc = Conversao.bitsBrutosParaASCII(quadro);
        int proximoQuadro = 0;
        public static boolean quadroRecebido = false;

        if (quadroAsc >= 3){
            janelaDeslizante = new int[3];
        } else{
            janelaDeslizante = new int[quadroAsc.length];
        }

        for (int i = 0; i < janelaDeslizante.length; i++){
            janelaDeslizante[i] = quadroAsc[i];
        }


        while (true){

            // mandar quadros na janela

            
            switch (quadroRecebido) {
                case quadroRecebido == true:
                    
                    break;
            
                case quadroRecebido == false:


                    break;
            }
        }

        return null;
    }
}