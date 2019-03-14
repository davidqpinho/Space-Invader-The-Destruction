/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvade.the.destruction;


import spaceinvade.the.destruction.modelo.*;
import spaceinvade.the.destruction.visao.*;
import javax.swing.JFrame;

/**
 *
 * @author Davidqpinho
 */
public class SpaceInvadeTheDestruction extends JFrame implements Comum{

    public SpaceInvadeTheDestruction(){
   
     add(new Tela());
     setTitle("Space Invader The destruction"); 
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     
     setSize(LARGURA,ALTURA);
     setLocationRelativeTo(null);
     setVisible(true);
     setResizable(false);
    
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SpaceInvadeTheDestruction();
    }
}
