package util;

import java.util.Timer;
import java.util.TimerTask;

import camadas.CamadaEnlaceDadosReceptoraControleDeErro;
import camadas.CamadaEnlaceDadosReceptoraControleDeFluxo;
import camadas.CamadaEnlaceDadosTransmissoraControleDeFluxo;
import view.PanelCenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Quadro {
    public Quadro buffer;
    public int sequencia;
    public int quadroEsperado;
    public int ack;
    public int[] bits = new int[1];
    public Timer timer;
    public javax.swing.Timer timerTela;

    public Boolean ACK = false;

    public int cont;

    /* ***************************************************************
    Metodo: temporizador*
    Funcao: da inicio ao cronometro do quadro*
    Parametros: int segundos*
    Retorno: void*
    *************************************************************** */
    public void temporizador(int segundos){
        this.timer = new Timer();
        this.timer.schedule(new RemindTask(), segundos*1000);
        timerTela = new javax.swing.Timer(segundos*1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                PanelCenter.labelTimer.setText("Cronometro: "+String.valueOf(cont));
                cont++;
                if(cont == segundos){
                    timerTela.stop();
                }
            }
        });
        timerTela.start();

    }

    /* ***************************************************************
    Metodo: stopTemporizador*
    Funcao: para o cronometro do quadro*
    Parametros: *
    Retorno: void*
    *************************************************************** */
    public void stopTemporizador(){
        
        this.timer.cancel();
        this.timer.purge();
    }

    class RemindTask extends TimerTask{
        public void run(){
            
            timer.cancel();
            timer.purge();
            try {
                System.out.println(MeioDeComunicacao.mutexMeio.availablePermits());
                
                
                
                CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar = CamadaEnlaceDadosReceptoraControleDeFluxo.ackEsperado;
            //reenvia os quadros na janela        
            for(CamadaEnlaceDadosReceptoraControleDeFluxo.i = 1; CamadaEnlaceDadosReceptoraControleDeFluxo.i < CamadaEnlaceDadosReceptoraControleDeFluxo.nbuffer; CamadaEnlaceDadosReceptoraControleDeFluxo.i++){
                MeioDeComunicacao.mutexMeio.acquire();
                CamadaEnlaceDadosTransmissoraControleDeFluxo.enviarQuadro(CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar, CamadaEnlaceDadosReceptoraControleDeFluxo.quadroEsperado, CamadaEnlaceDadosTransmissoraControleDeFluxo.buffer, CamadaEnlaceDadosTransmissoraControleDeFluxo.send);
                CamadaEnlaceDadosReceptoraControleDeFluxo.proximoQuadroEnviar++;
            }
            } catch (Exception e) {
                System.out.println("erro aquire timeout");
            }
            
        }
    }
}



