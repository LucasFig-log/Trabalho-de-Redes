/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

public class CamadaEnlaceDadosTransmissora{
  
  public static int[] novoQuadro;


    /* ***************************************************************
  Metodo: camadaEnlaceDadosTransmissora*
  Funcao: executar funcoes da camada de enlace de dados*
  Parametros: quadro[]*
  Retorno: void
  *************************************************************** */
    public static void camadaEnlaceDadosTransmissora(int quadro[]){
        CamadaEnlaceDadosTransmissoraEnquadramento.camadaEnlaceDadosTransmissoraEnquadramento(quadro);
        CamadaEnlaceDadosTransmissoraControleDeErros.camadaEnlaceDadosTransmissoraControleDeErros(quadro);
        CamadaEnlaceDadosTransmissoraControleDeFluxo.camadaEnlaceDadosTransmissoraControleDeFluxo(quadro);
        
        

        
        CamadaFisicaTransmissora.camadaFisicaTransmissora(novoQuadro);
    }
}