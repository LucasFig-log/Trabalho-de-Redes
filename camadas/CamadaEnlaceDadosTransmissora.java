package camadas;

/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import util.Quadro;

public class CamadaEnlaceDadosTransmissora{
  
  


    /* ***************************************************************
  Metodo: camadaEnlaceDadosTransmissora*
  Funcao: executar funcoes da camada de enlace de dados*
  Parametros: quadro[]*
  Retorno: void
  *************************************************************** */
    public static void camadaEnlaceDadosTransmissora(Quadro... quadro){
        
        quadro[0].bits =  CamadaEnlaceDadosTransmissoraControleDeErros.camadaEnlaceDadosTransmissoraControleDeErros(quadro);
        
        CamadaEnlaceDadosTransmissoraControleDeFluxo.camadaEnlaceDadosTransmissoraControleDeFluxo(quadro);
        
        
        
        
        
    }
}