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
public class mob_3 extends Personagem{
    /*
    *aqui é aonde sera definido o icon, e o metodo move
    */
        
    private static final int LARGURA = 52;
    private static final int ALTURA = 58;
    
    
    private static final int scorePts= 100; 
    
    private int xppos, yppos;

  
    public mob_3(int x, int y)
    {
       super(x,0,LARGURA,ALTURA);
       
    }
    
    public static int getScorePts() {
        return scorePts;
    }
   
    public void getPlayerPosition(int x, int y)
    {
        this.xppos = x;
        this.yppos = y;
    }
 
    public void move(){
        
        if(this.xppos < this.xpos)
        {
        if(!(this.xpos < this.xppos+6))this.xpos -= 3;
        }
        else
        {
        if(!(this.xpos > this.xppos+6))this.xpos += 3;
        }
        if(this.yppos < this.ypos)
        {
        if(!(this.ypos < this.yppos+6))this.ypos -= 3;
        }
        else
        {
        if(!(this.ypos > this.yppos+6))this.ypos += 3;
        }
        
       
    };
}

