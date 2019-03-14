/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvade.the.destruction.modelo;


/**
 *essa classe devera ser abstrata
 * @author Davidqpinho
 */
public abstract class Personagem {
   
   protected int xpos, ypos;
   protected int Ldim,Adim;
   
   private boolean active = false;
   
      
   
  
      
public Personagem(int x, int y, int largura, int altura){
   xpos = x;
   ypos = y;
   Ldim= largura;
   Adim = altura;
     
   }

abstract  public void move(); 
        
    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getLdim() {
        return Ldim;
    }

    public void setLdim(int Ldim) {
        this.Ldim = Ldim;
    }

    public int getAdim() {
        return Adim;
    }

    public void setAdim(int Adim) {
        this.Adim = Adim;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
   
   
}
