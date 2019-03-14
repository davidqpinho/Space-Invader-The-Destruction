package spaceinvade.the.destruction.modelo;

/**
 *
 * @author Davidqpinho
 */
public class mob_2 extends Personagem{
    /*
    *aqui é aonde sera definido o icon, e o metodo move
    */
        
    private static final int LARGURA = 43;
    private static final int ALTURA = 47;
    
    
    private static final int scorePts= 20; 
    
    private boolean direcao = false;
    
    private int counter;

  
    public mob_2(int x, int y,boolean D)
    {
       super(x,0,LARGURA,ALTURA);
      
       this.direcao = D;
       
       this.counter = 0;
       
    }
    
    public static int getScorePts() {
        return scorePts;
    }
   
 
    public void move(){
        
        if(this.ypos < Comum.ALTURA)
        {
            if(this.direcao == true)
            {
                this.ypos += 2;
                this.xpos += 2;
                ++this.counter;
                if( this.counter > 5)
                {
                    this.counter = 0;
                    this.direcao = false; 
                    
                }
            }
            else
            {
                this.xpos -= 2;
                this.ypos += 2;
                ++this.counter;
                if( this.counter > 5)
                {
                    this.counter = 0;
                    this.direcao = true; 
                } 
            }
        }
        else
        {
        this.setActive(false);
        }
           
    };
}
