/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvade.the.destruction.modelo;

/**
 *
 * @author Davidqpinho
 */
public class mob_1 extends Personagem{
    /*
    *aqui é aonde sera definido o icon, e o metodo move
    */
        
    private static final int LARGURA = 19;
    private static final int ALTURA = 58;
    
    
    private static final int scorePts= 100; 

  
    public mob_1(int x, int y)
    {
       super(x,y,LARGURA,ALTURA);
       
    }
    
    public static int getScorePts() {
        return scorePts;
    }
   
 
    public void move(){
        
        if(this.ypos < Comum.ALTURA)
        {
        this.ypos += 10;
        }
        else
        {
        this.setActive(false);
        System.out.println("Inativo");
        }
        
        
    };
}
