package camadas;

import view.FramePrincipal;
import util.Conversao;
import javax.swing.JOptionPane;

public class CamadaEnlaceDadosReceptoraControleDeErro{
    
    public static int [] fluxoBrutoDeBits;

    public static void camadaEnlaceDadosReceptoraControleDeErro(int quadro[]){
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraControleDeErrosBitsDeParidadePar(quadro);

        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro), FramePrincipal.TEXT_QUADROS_SEM_PARIDADE_PAR);

        CamadaEnlaceDadosReceptora.novoQuadro = fluxoBrutoDeBits;
    }

    public static int[] camadaEnlaceDadosReceptoraControleDeErrosBitsDeParidadePar(int quadro[]){
        
        int[] quadroSemParidadePar;

        int quandidadeBitsUltimoInt = Integer.toBinaryString(quadro[quadro.length - 1]).length();
        

        if (quandidadeBitsUltimoInt > 1){
            quadroSemParidadePar = new int[quadro.length];
            
            for(int i = 0; i< quadroSemParidadePar.length; i++){
                quadroSemParidadePar[i] = quadro[i];
            }
            quadroSemParidadePar[quadroSemParidadePar.length-1] >>= 1;
            
        } else{
            quadroSemParidadePar = new int[quadro.length -1];
            
            for(int i = 0; i < quadroSemParidadePar.length; i++){
                quadroSemParidadePar[i] = quadro[i];
                
            }            
            
        }


        int quantidadeBitsUm = 0;
        int valor = 0;
        int quantidadeBitsValor = 0;
        int displayMask = 1 << 31;

        for (int i = 0; i < quadro.length; i++){

            valor = quadro[i];
            quantidadeBitsValor = Integer.toBinaryString(valor).length();
            valor <<= (32 - (quantidadeBitsValor+1));
            

            for (int j = 0; j <= quantidadeBitsValor; j++){

                if ((valor & displayMask) != 0){
                    quantidadeBitsUm += 1;
                } 

                valor <<= 1;
            }
            
        }

        System.out.println(quantidadeBitsUm);
        if (quantidadeBitsUm % 2 == 0){
            
            return quadroSemParidadePar;
            
        } else{
            System.out.println("ERRO");
            FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro), FramePrincipal.TEXT_QUADROS_SEM_PARIDADE_PAR);
            JOptionPane.showConfirmDialog(null, "Erro no envio da mensagem, tente novamente. ", "Alerta! ", JOptionPane.OK_CANCEL_OPTION);
            
            return quadroSemParidadePar;
        }

        
    }
}