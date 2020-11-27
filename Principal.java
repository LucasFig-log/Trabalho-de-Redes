
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */

import view.FramePrincipal;
import javax.swing.SwingUtilities;

public class Principal {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable(){
      @Override
      public void run(){
        new FramePrincipal();
      }
    });    
  }
}