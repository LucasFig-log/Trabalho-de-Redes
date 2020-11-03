/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

public class CamadaEnlaceDadosReceptoraEnquadramento{
    private static int[] fluxoBrutoDeBits;

    /* ***************************************************************
  Metodo: camadaEnlaceDadosTransmissoraEnquadramentoContagemDeCaracteres*
  Funcao: executar a contagens de caractes para enquadralos*
  Parametros: quadro[]*
  Retorno: int[]*
  *************************************************************** */
    public static void camadaEnlaceDadosReceptoraEnquadramento(int quadro[]){
        
    
        //insercao de bytes
        fluxoBrutoDeBits = camadaEnlaceDadosReceptoraEnquadramentoInsercaoDeBytes(quadro);
        
        
        //imprime na caixa de texto quadros desenquadrados recebidos
        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits), FramePrincipal.TEXT_QUADROS_DESENQUADRADOS_RECEBIDOS);
        
        //imprime na caixa de texto a conversão caracter em ascII
        CamadaFisicaTransmissora.showAscII(fluxoBrutoDeBits,
          FramePrincipal.TEXT_ASCII_DECODIFICADO);

        //envia os quadros desenquadrados para a camada de aplicacao receptora
        CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora(fluxoBrutoDeBits);
    }


    /* ***************************************************************
  Metodo: camadaEnlaceDadosTransmissoraEnquadramentoInsercaoDeBytes*
  Funcao: desenquadrar os quadros recebidos*
  Parametros: quadro[]*
  Retorno: int[]*
  *************************************************************** */
    public static int[] camadaEnlaceDadosReceptoraEnquadramentoInsercaoDeBytes(int quadro[]){
        
        int[] quadrosPuros;
        char s = 'S';
        char e = 'E';
        char especial = '$';
        int quantidadeEspeciais = 0;
        int quantidadeCaracteresEspeciais = 0;
        int quantidadeFlag = 0;

        for(int i = 0; i < quadro.length; i++){
            if(quadro[i] == especial ){
                quantidadeEspeciais++;
                
            } else if(quadro[i] == e || quadro[i] == s){
                quantidadeFlag++;
            }

        }

        if(quantidadeEspeciais == 0){
            quadrosPuros = new int[quadro.length - quantidadeFlag];
        } else{
            for(int i = 0; i < quadro.length; i++){
                if(quadro[i] == s || quadro[i] == e){
                    quantidadeCaracteresEspeciais++;
                } else{
                    if(quadro[i] == especial){
                        quantidadeCaracteresEspeciais++;
                        i++;
                    }
                }
            }

            quadrosPuros = new int[quadro.length - quantidadeCaracteresEspeciais];
        }

        for(int i = 0, j = 0; i< quadro.length; i++){
            if(quadro[i] == especial){
                quadrosPuros[j] = quadro[i+1];
                j++;
                i++;
            } else if(quadro[i] != e && quadro[i] != s){
                quadrosPuros[j] = quadro[i];
                j++;
            }
        }

        
        return quadrosPuros;
    }
   
}