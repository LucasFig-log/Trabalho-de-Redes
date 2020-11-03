/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
public class Conversao {

   /* ***************************************************************
  Metodo: asciiParaBits*
  Funcao: converte o valor da tabela ascii em bits*
  Parametros: int[] quadro *
  Retorno: int[]*
  *************************************************************** */
  public static int[] asciiParaBits(int[] quadro) {

    int valor = 0;
    int tamanho = 0;

    if (quadro.length % 4 == 0) {
      tamanho = quadro.length / 4;
    } else {
      tamanho = (quadro.length / 4) + 1;
    }
    int fluxoBrutoDeBits[] = new int[tamanho];

    for (int i = 0, j = 0; i < quadro.length; i++) {
      valor <<= 8;
      valor = valor | quadro[i];
      if (i % 4 >= 0 && i == quadro.length - 1) {
        fluxoBrutoDeBits[j] = valor;
      } else if (i % 4 == 3) {
        fluxoBrutoDeBits[j] = valor;
        valor = 0;
        j++;
      }
    }

    return fluxoBrutoDeBits;
  }

 /* ***************************************************************
  Metodo: bitsBrutosParaASCII*
  Funcao: converte os bits para o valor referente a tabela ascII*
  Parametros: int[] bitsBrutos*
  Retorno: int[]*
  *************************************************************** */
  public static int[] bitsBrutosParaASCII(int[] bitsBrutos) {
    int[] value;
    int numero = 0;
    int valorAscii = 0;
    int mask = 1 << 31;
    int cont = 1;
    int numeroBits;

    int checaBits = Integer.toBinaryString(bitsBrutos[bitsBrutos.length - 1]).length();

    if (checaBits == 7) {

      value = new int[((bitsBrutos.length - 1) * 4) + 1];
    } else if ((checaBits == 15) || (checaBits == 16)) {
      value = new int[((bitsBrutos.length - 1) * 4) + 2];
    } else if (checaBits == 23) {
      value = new int[((bitsBrutos.length - 1) * 4) + 3];
    } else {
      value = new int[(bitsBrutos.length * 4)];
    }

    for (int i = 0, pos = 0; i < bitsBrutos.length; i++) {
      numero = bitsBrutos[i];

      checaBits = Integer.toBinaryString(numero).length();

      if (checaBits == 7) {
        numeroBits = 8;
        numero = numero << (32 - numeroBits);
      } else if ((checaBits == 15) || (checaBits == 16)) {
        numeroBits = 16;
        numero = numero << (32 - numeroBits);
      } else if (checaBits == 23) {
        numeroBits = 24;
        numero = numero << (32 - numeroBits);
      } else {
        numeroBits = 32;
      }

      while (cont <= numeroBits) {
        if ((mask & numero) == 0) {

          valorAscii = valorAscii << 1;
          valorAscii = valorAscii | 0;
        } else {
          valorAscii = valorAscii << 1;
          valorAscii = valorAscii | 1;
        }
        numero = numero << 1;

        if ((cont % 8) == 0) {

          value[pos] = valorAscii;
          pos++;
          valorAscii = 0;
        }
        cont++;

      }
      cont = 1;
    }

    return value;
  }

  /* ***************************************************************
  Metodo: bitsBrutosParaString*
  Funcao: converte os bits em String*
  Parametros: int[] bitsBrutos*
  Retorno: String*
  *************************************************************** */
  public static String bitsBrutosParaString(int[] bitsBrutos) {
    StringBuilder string = new StringBuilder();
    int mask = 1 << 31;
    int cont = 1;

    for (int i = 0; i < bitsBrutos.length; i++) {
      int numero = bitsBrutos[i];

      int checaBits = Integer.toBinaryString(numero).length();
      if (checaBits <= 8) {
        checaBits = 8;

      } else if (checaBits <= 16) {
        checaBits = 16;
      } else if (checaBits <= 24) {
        checaBits = 24;
      } else {
        checaBits = 32;
      }
      numero = numero << (32 - checaBits);

      while (cont <= checaBits) {
        if ((mask & numero) == 0) {
          string.append('0');
          if ((cont % 8) == 0) {
            string.append(' ');
          }
          if ((cont % 32) == 0) {
            string.append("\n");
          }
        } else {
          string.append('1');
          if ((cont % 8) == 0) {
            string.append(' ');
          }
          if ((cont % 32) == 0) {
            string.append("\n");
          }
        }
        numero = numero << 1;
        cont++;
      }
      cont = 1;
    }

    
    return string.toString();

  }
  /* ***************************************************************
  Metodo: bitsBrutosParaMensagem*
  Funcao: converte os bits em carecter*
  Parametros: int[] bitsBrutos*
  Retorno: String*
  *************************************************************** */
  public static String bitsBrutosParaMensagem(int[] bitsBrutos) {
    StringBuilder string = new StringBuilder();
    int value[];
    value = bitsBrutosParaASCII(bitsBrutos);
    for (int i = 0; i < value.length; i++) {
      string.append((char) value[i]);
    }
    return string.toString();
  }

}
