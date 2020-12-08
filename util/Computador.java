package util;
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import camadas.CamadaDeAplicacaoTransmissora;


public class Computador {
    
    private int id;

    //construtor do objeto computador
    public Computador(int id){
        this.id = id;
        
    }

    /* ***************************************************************
    Metodo: setId*
    Funcao: coloca o id do computador*
    Parametros: int id *
    Retorno: void*
    *************************************************************** */
    public void setId(int id){
        this.id = id;
    }

    /* ***************************************************************
    Metodo: getId*
    Funcao: pega o id do computador*
    Parametros: *
    Retorno: int*
    *************************************************************** */
    public int getId(){
        return this.id;
    }

    /* ***************************************************************
    Metodo: mandarMensagem*
    Funcao: Passa a mensagem para a camada de aplicacao transmissora*
    Parametros: String mensagem, int pcTransmissor, int pcReceptor*
    Retorno: void*
    *************************************************************** */
    public void mandarMensagem(String mensagem, int pcTransmissor, int pcReceptor){
        
        CamadaDeAplicacaoTransmissora.camadaDeAplicacaoTransmissora(mensagem, pcTransmissor, pcReceptor);
    }
}
