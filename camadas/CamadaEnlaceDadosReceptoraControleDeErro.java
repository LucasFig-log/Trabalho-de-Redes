package camadas;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import view.FramePrincipal;
import util.Conversao;
import javax.swing.JOptionPane;

public class CamadaEnlaceDadosReceptoraControleDeErro{
    
    public static int [] fluxoBrutoDeBits;

    /* ***************************************************************
    Metodo: camadaEnlaceDadosReceptoraControleDeErro*
    Funcao: executa o controle de erros*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
    public static void camadaEnlaceDadosReceptoraControleDeErro(int quadro[]){
        
        
        //confere a paridade par
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraControleDeErrosBitsDeParidadePar(quadro);
        
        //imprime os bits
        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro), FramePrincipal.TEXT_QUADROS_SEM_PARIDADE_PAR);
        
        //imprime na caixa de texto quadro sem pararidade par recebidos
        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits), FramePrincipal.TEXT_QUADROS_DESENQUADRADOS_RECEBIDOS);

        //passa os bits checados para a camada receptora
        Conversao.showAscII(Conversao.bitsBrutosParaASCII(fluxoBrutoDeBits),
          FramePrincipal.TEXT_ASCII_DECODIFICADO);

        CamadaEnlaceDadosReceptora.novoQuadro = fluxoBrutoDeBits;
    }

    /* ***************************************************************
    Metodo: camadaEnlaceDadosReceptoraControleDeErrosBitsDeParidadePar*
    Funcao: confere se o bits recebidos estao em paridade par*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
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

        
        if (quantidadeBitsUm % 2 == 0){
            
            return quadroSemParidadePar;
            
        } else{
            
            FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro), FramePrincipal.TEXT_QUADROS_SEM_PARIDADE_PAR);
            JOptionPane.showMessageDialog(null, "Erro no envio da mensagem, tente novamente. ", "Alerta! ", JOptionPane.ERROR_MESSAGE);
            
            return quadroSemParidadePar;
        }

        
    }
}