package util;

import java.util.Timer;
import java.util.TimerTask;
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

    public void temporizador(int segundos){
        this.timer = new Timer();
        this.timer.schedule(new RemindTask(), segundos*1000);
        timerTela = new javax.swing.Timer(segundos*1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                PanelCenter.labelTimer.setText(String.valueOf(cont));
                cont++;
                if(cont == segundos*1000){
                    timerTela.stop();
                }
            }
        });
        timerTela.start();

    }

    public void stopTemporizador(){
        this.timer.cancel();
    }

    class RemindTask extends TimerTask{
        public void run(){
            System.out.println("Acabou o tempo");
            timer.cancel();
        }
    }
}



