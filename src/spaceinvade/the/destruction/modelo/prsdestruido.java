/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvade.the.destruction.modelo;

/**
 *essa classe devera ser generica
 * @author Davidqpinho
 */
public class prsdestruido  <T extends Personagem,V extends Personagem>{
 
//Há colisão entre as figuras true para sim???    
 public boolean colisao(T mob1, V mob2)
 {
     int xaxis,yaxis;
     boolean xcheck = false,ycheck = false;
     
     for(xaxis = mob1.getXpos();xaxis<=(mob1.getXpos()+mob1.getLdim());xaxis++){
         if(xaxis<=(mob2.getXpos() + mob2.getLdim()) && (xaxis >= mob2.getXpos())){
           xcheck = true;      
           break;
         }
     }
    
     for(yaxis = mob1.getYpos();yaxis<=(mob1.getYpos() + mob1.getAdim());yaxis++){
        if(yaxis <= (mob2.getYpos()+mob2.getAdim()) && (yaxis >= mob2.getYpos())){
           ycheck = true;      
           break;
        }
     }

return(xcheck && ycheck);

 }
 
//distancia maxima permissivel é maior ou igual a distancia atual? 
 
 public boolean compDist(T mob1, V mob2)
 {
     int maxXpos,minXpos;
     int maxYpos,minYpos;
     
     if((mob1.getXpos() + (mob1.getLdim())/2) > (mob2.getXpos() + (mob2.getLdim())/2)) 
     {
         maxXpos = (mob1.getXpos() + (mob1.getLdim())/2);
         minXpos = (mob2.getXpos() + (mob2.getLdim())/2);
     }
     else
     {
         maxXpos = (mob2.getXpos() + (mob2.getLdim())/2);
         minXpos = (mob1.getXpos() + (mob1.getLdim())/2);
     }
     
     if((mob1.getYpos() + (mob1.getAdim())/2) > (mob2.getYpos() + (mob2.getAdim())/2)) 
     {
         maxYpos = (mob1.getYpos() + (mob1.getAdim())/2);
         minYpos = (mob2.getYpos() + (mob2.getAdim())/2);
     }
     else
     {
         maxYpos = (mob2.getYpos() + (mob2.getAdim())/2);
         minYpos = (mob1.getYpos() + (mob1.getAdim())/2);
     }
     

     return(Math.sqrt((int)Math.pow(((mob1.getLdim()+mob2.getLdim())*1/2), 2)+
            ((int)Math.pow(((mob1.getAdim()+mob2.getAdim())*1/2), 2))) >=
            Math.sqrt((int)Math.pow((maxXpos-minXpos), 2)+
            ((int)Math.pow((maxYpos - minYpos), 2))) ?true:false);
 }
      
}
