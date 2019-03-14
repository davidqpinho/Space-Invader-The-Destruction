/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvade.the.destruction.modelo;


    
public class Missil extends Personagem {

 private static final int LARGURA = 11;
 private static final int ALTURA = 50;
    
    public Missil(player player_ob){
    super((player_ob.xpos+player_ob.getLARGURA()/2),
            (player_ob.ypos + player_ob.getALTURA()/2),
            LARGURA,ALTURA);
    }
 
 @Override
 public void move(){
         if(this.ypos > 0)
        {
         this.ypos -=15;   
        }
         else
        {
        this.setActive(false);
        }
    }
}
