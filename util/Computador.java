package util;

import camadas.CamadaDeAplicacaoTransmissora;


public class Computador {
    
    private int id;


    public Computador(int id){
        this.id = id;
        
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void mandarMensagem(String mensagem, int pcTransmissor, int pcReceptor){
        
        CamadaDeAplicacaoTransmissora.camadaDeAplicacaoTransmissora(mensagem, pcTransmissor, pcReceptor);
    }
}
