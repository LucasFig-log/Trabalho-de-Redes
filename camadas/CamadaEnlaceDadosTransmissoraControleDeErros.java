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
import util.Quadro;

public class CamadaEnlaceDadosTransmissoraControleDeErros{

    public static int[] fluxoBrutoDeBits;
    
    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeErros*
    Funcao: executa o codigo de bit de paridade*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
    public static void camadaEnlaceDadosTransmissoraControleDeErros(Quadro... quadro){
       
        //imprime na caixa de texto quadro antes da paridade par
        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro[0].bits), FramePrincipal.TEXT_QUADROS_ENQUADRADOS);
       
        //chama a funcao que coloca o bit de paridade par
        fluxoBrutoDeBits = camadaEnlaceDadosTransmissoraControleDeErrosBitDeParidadePar(quadro);
       
        //imprime na caixa de texto quadro em paridade par 
        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits), FramePrincipal.TEXT_QUADROS_EM_PARIDADE_PAR);
       
        //retorna o novo vetor para a camada enlace dados
        CamadaEnlaceDadosTransmissora.novoQuadro = fluxoBrutoDeBits;
    }

    /* ***************************************************************
    Metodo: camadaEnlaceDadosTransmissoraControleDeErrosBitDeParidadePar*
    Funcao: coloca a mensagem em bit de paridade par*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
    public static int [] camadaEnlaceDadosTransmissoraControleDeErrosBitDeParidadePar(Quadro... quadro){

        int[] quadroBitsParidadePar;
        int[] quadrosConvertidos = Conversao.bitsBrutosParaASCII(quadro[0].bits);
        int quantidadeBitsUm = 0;
        
        

        

        for (int i = 0; i < quadrosConvertidos.length; i++ ){
            quantidadeBitsUm = quantidadeDeBitsUm(quadrosConvertidos[i]);

            if(quantidadeBitsUm % 2 == 0){
                quadrosConvertidos[i] <<= 1;
            } else{
                quadrosConvertidos[i] <<= 1;
                quadrosConvertidos[i] = quadrosConvertidos[i] | 1;
            }

        }

        quadroBitsParidadePar = Conversao.asciiParaBits(quadrosConvertidos);

        for(int i = 0; i<quadroBitsParidadePar.length; i++){
            System.out.println(quadroBitsParidadePar[i]);
        }

        

        

        return quadroBitsParidadePar;
    }


    /* ***************************************************************
    Metodo: quantidadeDeBitsUm*
    Funcao: conta a quantidade de bits um na mensagem*
    Parametros: quadro[]*
    Retorno: int[]*
    *************************************************************** */
    public static int quantidadeDeBitsUm(int quadro){
        int quantidadeBitsUm = 0;
        int valor = 0;
        int quantidadeBitsValor = 0;
        int displayMask = 1 << 31;

        

            valor = quadro;
            quantidadeBitsValor = Integer.toBinaryString(valor).length();
            valor <<= (32 - (quantidadeBitsValor+1));
            

            for (int j = 0; j <= quantidadeBitsValor; j++){

        

                if ((valor & displayMask) != 0){
                    quantidadeBitsUm += 1;
                } 

                valor <<= 1;
            }
            
        
        return quantidadeBitsUm;
    }
}