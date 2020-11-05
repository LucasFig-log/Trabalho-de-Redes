
/* ***************************************************************
Autor: Lucas Santos Figueiredo*
Matricula: 201810803*
Inicio: 23/01/2020*
Ultima alteracao: 03/11/2020*
Nome: Simulador de Redes*
Funcao: Exemplificar o funcionamento de um envio de mensagem.
*************************************************************** */
import javax.swing.JFrame;
import view.FramePrincipal;

public class Principal {
  public static void main(String[] args) {
    FramePrincipal framePrincipal = new FramePrincipal();
    framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePrincipal.setSize(1000, 600);
    framePrincipal.centerContainer(framePrincipal);
    framePrincipal.setVisible(true);
  }
}