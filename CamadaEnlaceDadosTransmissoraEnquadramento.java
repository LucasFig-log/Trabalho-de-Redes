/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

public class CamadaEnlaceDadosTransmissoraEnquadramento{
    

    public static int indexSelecionado = 2;
    private static int[] fluxoBrutoDeBits;

    /* ***************************************************************
  Metodo: setIndexSelecionado*
  Funcao: atribuir o valor do index selecionado*
  Parametros: int index*
  Retorno: void*
  *************************************************************** */
  public static void setIndexSelecionado(int index) {
    indexSelecionado = index;
    
  }


  /* ***************************************************************
  Metodo: camadaEnlaceDadosTransmissoraEnquadramento*
  Funcao: executar determinada codificacao com base na escolha*
  Parametros: quadro[]*
  Retorno: void*
  *************************************************************** */
    public static void camadaEnlaceDadosTransmissoraEnquadramento(int quadro[]){

        
        //insercao de Bytes
        fluxoBrutoDeBits = camadaEnlaceDadosTransmissoraEnquadramentoInsercaoDeBytes(quadro);
        
        //imprime na caixa de texto os quadros enquadrados
        FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits), FramePrincipal.TEXT_QUADROS_ENQUADRADOS);
              
        //envia os quadros para a camada enlace receptora
        CamadaEnlaceDadosTransmissora.novoQuadro = Conversao.asciiParaBits(fluxoBrutoDeBits);
    }

   

    /* ***************************************************************
  Metodo: camadaEnlaceDadosTransmissoraEnquadramentoInsercaoDeBytes*
  Funcao: executar a insercao de bytes para enquadrar*
  Parametros: quadro[]*
  Retorno: int[]*
  *************************************************************** */
    public static int[] camadaEnlaceDadosTransmissoraEnquadramentoInsercaoDeBytes(int quadro[]){
        
        //converte o quadro de bits para ascii
        quadro = Conversao.bitsBrutosParaASCII(quadro);
        
        char s = 'S'; //Flag de inicio
        char e = 'E'; //FLag de final 
        char especial = '$'; //Flag de escape
        int i = 0;
        int k = 0;
        int j = 0;

        int[] quadrosDefinidos;
        int quantidadeQuadros;
        int quantidadeCaracteres = 0;


        for(int a = 0; a < quadro.length; a++){
            if(quadro[a] == e || quadro[a] == s || quadro[a] == especial){
                quantidadeCaracteres++;
            }
        }

        if((quadro.length % 4) == 0){
            quantidadeQuadros = quadro.length/4;
            quadrosDefinidos = new int[quadro.length + (quantidadeQuadros*2) + (quantidadeCaracteres)];
        } else{
            quantidadeQuadros = quadro.length/4;
            quadrosDefinidos = new int[quadro.length + (quantidadeQuadros*2) + 2 + (quantidadeCaracteres)];
            
        }

        for( i = 0, k = 0; i < quantidadeQuadros; i++){
            
            quadrosDefinidos[k] = (int) s;
            k++;

            for(int c = 0; c < 4; c++){
                if((c == 0) && (quadro[j] == s)){
                    quadrosDefinidos[k] = (int) especial;
                    k++;   
                } else if(quadro[j] == especial || quadro[j] == s || quadro[j] == e ){
                    quadrosDefinidos[k] = (int) especial;
                    k++;
                }
                
                quadrosDefinidos[k] = quadro[j];
                k++;
                j++;

            }
            quadrosDefinidos[k] = (int) e;
            k++;
        }
        
        if((quadro.length % 4) != 0){
            
            quadrosDefinidos[k] = (int) s;
            k++;

            for(int c = 0; c < quadro.length % 4; c++){
                if(((c == 0) && (quadro[j] == s)) || (quadro[j] == especial) || (quadro[j] == s) || (quadro[j] == e)){
                    quadrosDefinidos[k] = (int) especial;
                    k++;
                }
                
                quadrosDefinidos[k] = quadro[j];
                k++;
                
                if(((c == 3) && (quadro[j] == e))){
                    quadrosDefinidos[k] = (int) especial;
                    k++; 
                }
                j++;
            }

            quadrosDefinidos[k] = (int) e;
            k++;
        }

        return quadrosDefinidos;
    }

    
   
}