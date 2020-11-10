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

public class CamadaEnlaceDadosTransmissoraControleDeErros{

    public static int[] fluxoBrutoDeBits;
    
    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeErros*
    Funcao: executa o codigo de bit de paridade*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
    public static void camadaEnlaceDadosTransmissoraControleDeErros(int quadro[]){
        
        fluxoBrutoDeBits = camadaEnlaceDadosTransmissoraControleDeErrosBitDeParidadePar(quadro);
         
        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits), FramePrincipal.TEXT_QUADROS_EM_PARIDADE_PAR);

        CamadaEnlaceDadosTransmissora.novoQuadro = fluxoBrutoDeBits;
    }

    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeErrosBitDeParidadePar*
    Funcao: coloca a mensagem em bit de paridade par*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
    public static int [] camadaEnlaceDadosTransmissoraControleDeErrosBitDeParidadePar(int quadro[]){

        int[] quadroBitsParidadePar;
        int quantidadeBitsUm = 0;
        
        

        int quandidadeBitsUltimoInt = Integer.toBinaryString(quadro[quadro.length - 1]).length();
        
        if (quandidadeBitsUltimoInt >= 31){
            quadroBitsParidadePar = new int[quadro.length + 1];
        } else{
            quadroBitsParidadePar = new int[quadro.length];
        }

        quantidadeBitsUm  = quantidadeDeBitsUm(quadro);

        for (int i = 0; i < quadro.length; i++){
            quadroBitsParidadePar[i] = quadro[i];
           
        }

        

        if (quantidadeBitsUm % 2 == 0){
            if (quandidadeBitsUltimoInt >= 31){
                quadroBitsParidadePar[quadroBitsParidadePar.length -1] = 0;
            } else{
                quadroBitsParidadePar[quadroBitsParidadePar.length-1] <<= 1;
            }
            
        } else{
            if (quandidadeBitsUltimoInt >= 31){
                quadroBitsParidadePar[quadroBitsParidadePar.length -1] = 1;
            } else{
                quadroBitsParidadePar[quadroBitsParidadePar.length-1] <<= 1;
            quadroBitsParidadePar[quadroBitsParidadePar.length-1] = quadroBitsParidadePar[quadroBitsParidadePar.length-1] | 1;
            }
            

        }

        return quadroBitsParidadePar;
    }


    /* ***************************************************************
    Metodo: quantidadeDeBitsUm*
    Funcao: conta a quantidade de bits um na mensagem*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
    public static int quantidadeDeBitsUm(int[] quadro){
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
        return quantidadeBitsUm;
    }
}