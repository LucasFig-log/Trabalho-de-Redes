/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
public class CamadaFisicaReceptora {
  public static int fluxoBrutoDeBits[];

 /* ***************************************************************
  Metodo: camadaFisicaReceptora*
  Funcao: decodificar os bits enviados com base na escolha*
  Parametros: int quadro[]*
  Retorno: void*
  *************************************************************** */
  public static void camadaFisicaReceptora(int quadro[]) {

    switch (CamadaFisicaTransmissora.indexSelecionado) {
    case 0: // decodificacao binaria
      fluxoBrutoDeBits = camadaFisicaReceptoraDecodificacaoBinaria(quadro);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits),
          FramePrincipal.TEXT_BITS_RECEBIDOS);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits),
          FramePrincipal.TEXT_BITS_BRUTOS_DECODIFICADO);
      

      break;
    case 2: // decodificacao manchester
      fluxoBrutoDeBits = camadaFisicaReceptoraDecodificacaoManchester(quadro);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro), FramePrincipal.TEXT_BITS_RECEBIDOS);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits),
          FramePrincipal.TEXT_BITS_BRUTOS_DECODIFICADO);
      

      break;
    case 3: // decodificacao manchester diferencial
      fluxoBrutoDeBits = camadaFisicaReceptoraDecodificacaoManchesterDiferencial(quadro);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(quadro), FramePrincipal.TEXT_BITS_RECEBIDOS);
      FramePrincipal.imprimirNaTela(Conversao.bitsBrutosParaString(fluxoBrutoDeBits),
          FramePrincipal.TEXT_BITS_BRUTOS_DECODIFICADO);
      

      break;

    }
    
    //envia os quadros decodificados para a camada enlace de dados receptora
    CamadaEnlaceDadosReceptora.camadaEnlaceDadosReceptora(fluxoBrutoDeBits);
    
  }

  /* ***************************************************************
  Metodo: camadaFisicaReceptoraDecodificacaoBinaria*
  Funcao: decodificar os bits de cada caracter da mensagem com base na codificacao binaria*
  Parametros: int quadro[]*
  Retorno: int[]*
  *************************************************************** */
  public static int[] camadaFisicaReceptoraDecodificacaoBinaria(int quadro[]) {
    int vectorDecodificado[] = new int[quadro.length];

  
    return Conversao.bitsBrutosParaASCII(quadro);
  }

/* ***************************************************************
  Metodo: camadaFisicaReceptoraDecodificacaoManchester*
  Funcao: decodificar os bits de cada caracter da mensagem com base na codificacao Manchester*
  Parametros: int quadro[]*
  Retorno: int[]*
  *************************************************************** */
  public static int[] camadaFisicaReceptoraDecodificacaoManchester(int quadro[]) {
    int vectorDecodificado[];
    int mask01 = 1 << 30;
    int mask10 = 1 << 31;
    int numero = 0;
    int cont = 0;
    int valorDecodificado = 0;
    int numeroBits = 0;
    int test = 0;

    test = Integer.toBinaryString(quadro[quadro.length - 1]).length();

    if (test <= 16) {
      vectorDecodificado = new int[(quadro.length * 2) - 1]; //definindo o tamanho do vetor que recebera os bits decodificados
    } else {
      vectorDecodificado = new int[quadro.length * 2];
    }

    for (int i = 1, pos = 0; i <= quadro.length; i++) {
      numero = quadro[i - 1];
      int checaBits = Integer.toBinaryString(quadro[i - 1]).length();

      if (checaBits <= 8) {
        numeroBits = 8;
        numero = numero << (32 - numeroBits);
      } else if (checaBits <= 16) {
        numeroBits = 16;
        numero = numero << (32 - numeroBits);
      } else if (checaBits <= 24) {
        numeroBits = 24;
        numero = numero << (32 - numeroBits);
      } else {
        numeroBits = 32;
      }

      while (cont <= (numeroBits / 2)) { // comparando cada dois bits
        if ((mask01 & numero) == mask01) {
          valorDecodificado = valorDecodificado << 1;
          valorDecodificado = valorDecodificado | 0;
        } else if ((mask10 & numero) == mask10) {
          valorDecodificado = valorDecodificado << 1;
          valorDecodificado = valorDecodificado | 1;
        }
        numero = numero << 2;
        cont++;
        if (cont % 8 == 0) {
          vectorDecodificado[pos] = valorDecodificado;
          pos++;
          valorDecodificado = 0;
        }
      }

      cont = 0;
    }

    return vectorDecodificado;
  }

 /* ***************************************************************
  Metodo: camadaFisicaReceptoraDecodificacaoManchesterDiferencial*
  Funcao: decodificar os bits de cada caracter da mensagem com base na codificacao Manchester Diferencial*
  Parametros: int quadro[]*
  Retorno: int[]*
  *************************************************************** */
  public static int[] camadaFisicaReceptoraDecodificacaoManchesterDiferencial(int quadro[]) {
    int vectorDecodificado[];
    int mask11 = 1610612736;
    int mask00 = 0;
    int mask01 = 1 << 30;
    int mask10 = 1 << 31;
    int numero = 0;
    int cont = 0;
    int contador = 1;
    int valorDecodificado = 0;
    int numeroBits = 0;
    int transicao = 0;
    int numeroTransicao = 0;
    int numeroTransicao2 = 0;
    int temp = 0;
    int test = 0;

    test = Integer.toBinaryString(quadro[quadro.length - 1]).length(); //pegando a quantidades de bits do inteiro

    if (test <= 16) {
      vectorDecodificado = new int[(quadro.length * 2) - 1]; // definindo o tamanho do vetor que recebera os bits decodificados
    } else {
      vectorDecodificado = new int[quadro.length * 2];
    }

    for (int i = 1, pos = 0; i <= quadro.length; i++) {
      numero = quadro[i - 1];
      int checaBits = Integer.toBinaryString(quadro[i - 1]).length();

      if (checaBits <= 8) {
        numeroBits = 8;
        numero = numero << (32 - numeroBits);
      } else if (checaBits <= 16) {
        numeroBits = 16;
        numero = numero << (32 - numeroBits);
      } else if (checaBits <= 24) {
        numeroBits = 24;
        numero = numero << (32 - numeroBits);
      } else {
        numeroBits = 32;
      }

      while (cont < (numeroBits / 2)) { //compara de dois em dois bits
        if (contador == 1) {
          if ((mask01 & numero) == mask01) {
            valorDecodificado = valorDecodificado << 1;
            valorDecodificado = valorDecodificado | 0;

          } else if ((mask10 & numero) == mask10) {
            valorDecodificado = valorDecodificado << 1;
            valorDecodificado = valorDecodificado | 1;

          }
        } else if ((temp == 16)) {
          numeroTransicao = quadro[i - 2];
          numeroTransicao = numeroTransicao << 30;
          numeroTransicao2 = numero >>> 2;
          transicao = numeroTransicao | numeroTransicao2;

          if (((mask11 & transicao) == mask00) || ((mask11 & transicao) == mask11)) {
            valorDecodificado = valorDecodificado << 1;
            valorDecodificado = valorDecodificado | 1;

          } else {
            valorDecodificado = valorDecodificado << 1;
            valorDecodificado = valorDecodificado | 0;

          }

          temp = 0;

        } else {
          if (((mask11 & numero) == mask00) || ((mask11 & numero) == mask11)) {
            valorDecodificado = valorDecodificado << 1;
            valorDecodificado = valorDecodificado | 1;

          } else {
            valorDecodificado = valorDecodificado << 1;
            valorDecodificado = valorDecodificado | 0;

          }
          numero = numero << 2;
        }

        cont++;
        contador++;
        temp++;
        if (cont % 8 == 0) {

          vectorDecodificado[pos] = valorDecodificado;
          pos++;
          valorDecodificado = 0;
        }
      }

      cont = 0;
    }
    return vectorDecodificado;
  }

}
