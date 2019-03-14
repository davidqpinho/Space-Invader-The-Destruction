package spaceinvade.the.destruction.modelo;

/**
 *
 * @author Davidqpinho
 */
public class player extends Personagem{
    /*
    *aqui é aonde sera definido o icon, e o metodo move
    */
        
    private static final int LARGURA = 43;
    private static final int ALTURA = 47;

    private boolean leftKey = false;
    private boolean rightKey = false;
    private boolean upkey = false; 
    private boolean downkey = false; 
    private boolean spacekey = false;
    
    private int playerExplosion;
    
    private int scorePt = 0;
    
  
    public player(int x, int y)
    {
       super(x,y,LARGURA,ALTURA);
      
    }
 
    
    
    public void getKey( boolean leftKey,
                        boolean rightKey,
                        boolean upkey, 
                        boolean downkey, 
                        boolean spacekey)
    {
    
    this.leftKey = leftKey;
    this.rightKey = rightKey;
    this.upkey = upkey;
    this.downkey = downkey;
    this.spacekey = spacekey;
    
    }
    
    public void move(){
        
    if(this.xpos > 0)this.xpos = (this.leftKey ? this.xpos-8:this.xpos);    
    if(this.xpos < Comum.LARGURA-(this.getLARGURA())*2)this.xpos = (this.rightKey ? this.xpos+8:this.xpos);  
    if(this.ypos < 650)this.ypos = (this.downkey ? this.ypos+8:this.ypos);    
    if(this.ypos > 5)this.ypos = (this.upkey ? this.ypos-8:this.ypos);  
           
    }

    public int getPlayerExplosion() {
        return playerExplosion;
    }

    public void setPlayerExplosion(int playerExplosion) {
        this.playerExplosion = playerExplosion;
    }
    
    public static int getLARGURA() {
        return LARGURA;
    }

    public static int getALTURA() {
        return ALTURA;
    }

    public int getScorePt() {
        return scorePt;
    }

    public void setScorePt(int scorePt) {
        this.scorePt = scorePt;
    }
    
}
