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
public class ObjDestruido extends Personagem 
{
    private static final int LARGURA = 68;
    private static final int ALTURA = 60;
    
    private int xppos, yppos;
    
    private int counter=0;
        
    public ObjDestruido(Personagem o)
    {
        super(o.xpos,o.ypos,LARGURA,ALTURA);
        this.counter = 20;
    }
        public void getObjPosition(int x, int y)
    {
        this.xppos = x;
        this.yppos = y;
    }
    

    public void move(){
      if((--this.counter)<= 0) this.setActive(false);
      
     }
}
