/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

public class CamadaFisicaTransmissora {

  public static int indexSelecionado = 0;
  private static int[] fluxoBrutoDeBits;
  private static int[] quadro2;

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
  Metodo: camadaFisicaTransmissora*
  Funcao: executar determinada codificacao com base na escolha*
  Parametros: quadro[]*
  Retorno: void*
  *************************************************************** */
  public static void camadaFisicaTransmissora(int quadro[]) {
    quadro2 = new int[quadro.length];
    for(int i = 0; i< quadro.length; i++){
      quadro2[i] = quadro[i];
    }

    switch (indexSelecionado) {
    case 0: // codificacao binaria
      fluxoBrutoDeBits = camadaFisicaTransmissoraCodificacaoBinaria(quadro);
      
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits), FramePrincipal.TEXT_BITS_BRUTOS);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits),
          FramePrincipal.TEXT_BISTS_CODIFICADOS);
      break;
    case 2: // codificacao manchester
      fluxoBrutoDeBits = camadaFisicaTransmissoraCodificacaoManchester(quadro);
      
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro2),
          FramePrincipal.TEXT_BITS_BRUTOS);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits),
          FramePrincipal.TEXT_BISTS_CODIFICADOS);
      break;
    case 3: // codificacao manchester diferencial
      fluxoBrutoDeBits = camadaFisicaTransmissoraCodificacaoManchesterDiferencial(quadro);
      
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro2),
          FramePrincipal.TEXT_BITS_BRUTOS);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits),
          FramePrincipal.TEXT_BISTS_CODIFICADOS);
      break;
    }
    // enviando os bits codificados para a classe MeioDeComunicacao
    MeioDeComunicacao.meioDeComunicacao(fluxoBrutoDeBits);
  }

  /* ***************************************************************
  Metodo: camadaFisicaTransmissoraCodificacaoBinaria*
  Funcao: Obter os bits de cada caracter da mensagem*
  Parametros: int quadro[]*
  Retorno: int[]*
  *************************************************************** */
  public static int[] camadaFisicaTransmissoraCodificacaoBinaria(int quadro[]) {
    return quadro;

  }

/* ***************************************************************
  Metodo: camadaFisicaTransmissoraCodificacaoManchester*
  Funcao: Obter os bits de cada caracter da mensagem com base na codificacao manchester*
  Parametros: int quadro[]*
  Retorno: int[]*
  *************************************************************** */
  public static int[] camadaFisicaTransmissoraCodificacaoManchester(int quadro[]) {
    int cont = 1;
    int mask2 = 1 << 31;
    int mask01 = 1;
    int mask10 = 2;
    int valor = 0;
    int numeroBits = 0;

   
    int[] bitsBrutos = quadro;
    int[] fluxoBrutoDeBitsCodificados;

    int test = Integer.toBinaryString(bitsBrutos[bitsBrutos.length - 1]).length();


    if (test > 16) {
      fluxoBrutoDeBitsCodificados = new int[bitsBrutos.length * 2];//definindo o tamanho do vetor que estarao os bits codificados
    } else {
      fluxoBrutoDeBitsCodificados = new int[(bitsBrutos.length * 2) - 1];
    }

    for (int i = 0, pos = 0; i < bitsBrutos.length; i++) { //percorrendo o vetor de bits nao codificados

      int checaBits = Integer.toBinaryString(bitsBrutos[i]).length();
      if (checaBits <= 8) {
        numeroBits = 8;
        bitsBrutos[i] = bitsBrutos[i] << (32 - numeroBits);
      } else if (checaBits <= 16) {
        numeroBits = 16;
        bitsBrutos[i] = bitsBrutos[i] << (32 - numeroBits);
      } else if (checaBits <= 24) {
        numeroBits = 24;
        bitsBrutos[i] = bitsBrutos[i] << (32 - numeroBits);
      } else {
        numeroBits = 32;
      }

      while (cont <= numeroBits) { //percorrendo bit a bit 

        if ((mask2 & bitsBrutos[i]) == 0) {

          valor = valor << 2;

          valor = valor | mask01;

        } else {

          valor = valor << 2;

          valor = valor | mask10;

        }

        if (((numeroBits) == 8) && ((cont % numeroBits) == 0)) {

          fluxoBrutoDeBitsCodificados[pos] = valor;
          pos++;
          valor = 0;

        } else if ((cont % numeroBits == 16) || (cont % numeroBits) == 0) {

          fluxoBrutoDeBitsCodificados[pos] = valor;
          pos++;
          valor = 0;

        }
        bitsBrutos[i] = bitsBrutos[i] << 1;

        cont++;

      }
      cont = 1;

    }

    return fluxoBrutoDeBitsCodificados;
  }

  
/* ***************************************************************
  Metodo: camadaFisicaTransmissoraCodificacaoManchesterDiferencial*
  Funcao: Obter os bits de cada caracter da mensagem com base na codificacao manchester diferencial*
  Parametros: int quadro[]*
  Retorno: int[]*
  *************************************************************** */
  public static int[] camadaFisicaTransmissoraCodificacaoManchesterDiferencial(int quadro[]) {
    int[] bitsBrutos = quadro;

    
    int[] fluxoBrutoDeBitsCodificados;
    int cont = 1;
    int contador = 0;
    int valor = -1;
    int mask01 = 1;
    int mask10 = 2;
    int numeroBits = 0;
    int test = Integer.toBinaryString(bitsBrutos[bitsBrutos.length - 1]).length();

    int maskCompara = 1 << 31;

    if (test > 16) {
      fluxoBrutoDeBitsCodificados = new int[bitsBrutos.length * 2]; //definindo o tamanho do vetor que estarao os bits codificados
    } else {
      fluxoBrutoDeBitsCodificados = new int[(bitsBrutos.length * 2) - 1];
    }

    for (int i = 0, pos = 0; i < bitsBrutos.length; i++) { //percorrendo o vetor de bits nao codificados

      int checaBits = Integer.toBinaryString(bitsBrutos[i]).length();
      if (checaBits <= 8) {
        numeroBits = 8;

        bitsBrutos[i] = bitsBrutos[i] << (32 - numeroBits);
      } else if (checaBits <= 16) {
        numeroBits = 16;

        bitsBrutos[i] = bitsBrutos[i] << (32 - numeroBits);
      } else if (checaBits <= 24) {
        numeroBits = 24;

        bitsBrutos[i] = bitsBrutos[i] << (32 - numeroBits);
      } else {
        numeroBits = 32;

      }

      while (cont <= (numeroBits)) { //percorrendo bit a bit

        if (valor == -1) {
          if ((maskCompara & bitsBrutos[i]) == 0) {
            fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] | mask01;
            valor = 0;
          } else {
            fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] | mask10;
            valor = 1;
          }

        } else {
          if ((maskCompara & bitsBrutos[i]) == 0) {
            if (valor == 0) {

              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] << 2;
              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] | mask01;
              valor = 0;
            } else {

              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] << 2;
              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] | mask10;

              valor = 1;

            }
          } else {
            if (valor == 0) {

              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] << 2;
              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] | mask10;
              valor = 1;
            } else {

              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] << 2;
              fluxoBrutoDeBitsCodificados[pos] = fluxoBrutoDeBitsCodificados[pos] | mask01;
              valor = 0;

            }
          }
        }

        if (cont == 16) {
          pos++;
        } else if ((cont % numeroBits) == 0) {
          pos++;

        }
        bitsBrutos[i] = bitsBrutos[i] << 1;

        cont++;
        contador++;
      }
      cont = 1;

    }

    return fluxoBrutoDeBitsCodificados;
  }

 /* ***************************************************************
  Metodo: showAscII*
  Funcao: imprime o caracter e o valor referente a tabela ASCII*
  Parametros: int value[], int tipoDeImpressao*
  Retorno: void*
  *************************************************************** */
  public static void showAscII(int[] value, int tipoDeImpressao) {
    StringBuilder stringAscii = new StringBuilder();

    for (int i = 0; i < value.length; i++) {
      stringAscii.append(" " + (char) value[i] + "  ->  " + value[i] + "\n ");
    }
    // imprime a mensagem no campo especifico
    FramePrincipal.imprimirNaTela(stringAscii.toString(), tipoDeImpressao);

  }

}
