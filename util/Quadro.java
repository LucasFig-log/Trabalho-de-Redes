package util;

import java.util.Timer;
import java.util.TimerTask;
import camadas.CamadaEnlaceDadosReceptoraControleDeFluxo;
import view.PanelCenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Quadro {
    public int[] buffer = new int[1];
    public int sequencia;
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
    }

    class RemindTask extends TimerTask{
        public void run(){
            System.out.println("Acabou o tempo");
            CamadaEnlaceDadosReceptoraControleDeFluxo.tipo = Eventos.TIMEOUT;
            timer.cancel();
        }
    }
}



