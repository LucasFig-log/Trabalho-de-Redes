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
public class CamadaEnlaceDadosReceptora{

   

    

      /* ***************************************************************
  Metodo: camadaEnlaceDadosTransmissora*
  Funcao: executar funcoes da camada enlace de dados*
  Parametros: quadro[]*
  Retorno: void
  *************************************************************** */
    public static void camadaEnlaceDadosReceptora(Quadro... quadro){
        
        
        quadro[0].bits = CamadaEnlaceDadosReceptoraControleDeErro.camadaEnlaceDadosReceptoraControleDeErro(quadro[0].bits);
        
        quadro[0].bits =  CamadaEnlaceDadosReceptoraControleDeFluxo.camadaEnlaceDadosReceptoraControleDeFluxo(quadro);
        
        
        CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora(quadro[0].bits);
    }
}