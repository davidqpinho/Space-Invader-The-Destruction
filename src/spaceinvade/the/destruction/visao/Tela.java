/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvade.the.destruction.visao;


import spaceinvade.the.destruction.modelo.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;
//import java.io.Scanner;
/**
 *
 * @author Davidqpinho
 */
public class Tela extends JPanel implements Runnable, Comum
{
 private Dimension dimensao;  
 
 private final String background_imagem= "OpS.png";//string, direção endereço
 private final String background_game= "bkg.png";//string, direção endereço
 private ImageIcon background;//objeto
 private ImageIcon mob1Image;
 private ImageIcon mob2Image;
 private ImageIcon mob3Image;
 private ImageIcon playerIcon;
 private ImageIcon explosionIcon;
 private ImageIcon MissilIcon;
 

  
 private final String txt1= "Começar";
 private final String txt2= "Lista de recordes";
 private final String txt3= "Sair";
  
 public final String MOB_1="sprites/Mob1Img.png"; 
 public final String MOB_2="sprites/Mob2Img.png";
 public final String MOB_3="sprites/Mob3Img.png";
 public final String PLAYER="sprites/NaveImg.png";
 public final String EXPLOSION="sprites/exploImg.png";
 public final String MISSIL="sprites/missilImg.png";
  
 private byte menu_state = 1;
  
 private Thread animador;
  
 public boolean menu_on = true, score_table = false, game_running= false, game_on =true;
  
  
 private boolean leftKey = false;
 private boolean rightKey = false;
 private boolean upkey = false;
 private boolean downkey = false;
 private boolean spacekey = false;
    
 protected ArrayList<mob_1> Mob1;
 protected ArrayList<mob_2> Mob2;
 protected ArrayList<mob_3> Mob3;
 protected ArrayList<ObjDestruido> Exp;
 protected ArrayList<Missil> Shot;
 protected player Jogador;
 
 public int MeuScore=0;
 protected int explosionCounter;
 protected int MissilCounter;
 protected int Mob2Counter;
 protected int Mob3Counter;
 protected int mobCounter;
 protected int spownTime;
 protected long MissilTimer;
 protected int rajadaCounter = 0;
 protected int rajadaTimer = 0;
 protected int intervaloRajada = 200;
 protected boolean rajada_end= true;
 protected boolean checkRajada = false;
 protected boolean checkPrimeiro;
 protected int posicaodaRajada;
 
 protected int sorteioDeMobs;
 protected int numeroDeMobs;
 protected int nivel;
 
public escreve objEscritor;
   
  public Tela(){
      
  objEscritor = new escreve();
      
  addKeyListener(new TAdapter());
  
  setFocusable(true);
  
  this.dimensao = new Dimension(LARGURA,ALTURA);
  
  objEscritor.criaArquivo();
  objEscritor.lerArquivo();
  

  objEscritor.lerRegistros();
  //objEscritor.lerArquivo();
  //objEscritor.escreveArquivo(txt1);
  objEscritor.lerArquivo();
  //objEscritor.lerRegistros();

  mob1Image = new ImageIcon(getClass().getResource(MOB_1));
  mob2Image = new ImageIcon(getClass().getResource(MOB_2));
  mob3Image = new ImageIcon(getClass().getResource(MOB_3));
  explosionIcon = new ImageIcon(getClass().getResource(EXPLOSION));
  MissilIcon = new ImageIcon(getClass().getResource(MISSIL));
  playerIcon = new ImageIcon(getClass().getResource(PLAYER)); 
  
  Mob1 = new ArrayList<>();
  Mob2 = new ArrayList<>();
  Mob3 = new ArrayList<>();
  Exp  = new ArrayList<>();
  Shot = new ArrayList<>();
  Jogador = new player(LARGURA/2-21,650);
  

  initGame();
  
  }
  
    @Override
  public void addNotify()
  {
    super.addNotify();
    initGame();
  }

  public void initGame(){
       
        if(animador == null || !game_on)
      {
        animador = new Thread(this);
        animador.start();
      }
         
  }

  @Override
  public void paint(Graphics g){
      
      super.paint(g);
      
      Graphics2D g2 = (Graphics2D)g;      
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      Font title_estilo = new Font("Microsoft Sans Serif",Font.CENTER_BASELINE,32);
      Font selec_estilo = new Font("Microsoft Sans Serif",Font.CENTER_BASELINE,36);
      
      g.setColor(Color.WHITE);
      
      g.setFont(title_estilo);
      
      FontMetrics title_metrica = this.getFontMetrics(title_estilo);
      FontMetrics selec_metrica = this.getFontMetrics(selec_estilo);
      
    if(game_on)
    {
        if (menu_on)
        {
            g2.drawImage(background.getImage(), 0, 0, this);
            
            switch(menu_state)
            {
                case 1:
                
                g.drawString(txt2, (LARGURA - title_metrica.stringWidth(txt2))/2, (ALTURA/2+30));
                g.drawString(txt3, (LARGURA - title_metrica.stringWidth(txt3))/2, (ALTURA/2+60)); 
                g.setColor(Color.YELLOW);
                g.drawString(txt1, (LARGURA - selec_metrica.stringWidth(txt1))/2, ALTURA/2);
                break;
                case 2:
                g.drawString(txt1, (LARGURA - title_metrica.stringWidth(txt1))/2, (ALTURA/2));
                g.drawString(txt3, (LARGURA - title_metrica.stringWidth(txt3))/2, (ALTURA/2+60)); 
                g.setColor(Color.YELLOW);
                g.drawString(txt2, (LARGURA - selec_metrica.stringWidth(txt2))/2, ALTURA/2+30);    
                break;
                case 3:
                g.drawString(txt1, (LARGURA - title_metrica.stringWidth(txt1))/2, (ALTURA/2));
                g.drawString(txt2, (LARGURA - title_metrica.stringWidth(txt2))/2, (ALTURA/2+30)); 
                g.setColor(Color.YELLOW);
                g.drawString(txt3, (LARGURA - selec_metrica.stringWidth(txt3))/2, ALTURA/2+60); 
                break;
                    
            }
           
        }
        else if(game_running)
        {
                g2.drawImage(background.getImage(), 0, 0, this);
                
                paintList(g2,Shot,MissilIcon);
                g2.drawImage(this.playerIcon.getImage(),
                            this.Jogador.getXpos(),
                            this.Jogador.getYpos(),
                            this);
                
                paintList(g2,Mob1,mob1Image);
                paintList(g2,Mob2,mob2Image);
                paintList(g2,Mob3,mob3Image);
                paintList(g2,Exp,explosionIcon);
                
                
                g.drawString("" + this.Jogador.getScorePt(), (LARGURA - 100), 30);
                
                //g.fillRect(481, 129, this.Jogador.getScorePt(), 26);
                
                repaint();
        }
        else if(score_table)
        {
            g2.drawImage(background.getImage(), 0, 0, this);
            for(int i=0;i<10;i++)
                g.drawString("" + objEscritor.lista.get(i).getValor(), (LARGURA)/2, 650-30*i);
                repaint();
        }
    }
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
  }// end of paint method    
 
  private <T extends Personagem> void paintList(Graphics2D g, ArrayList<T> Moblist, ImageIcon Icon)
  {
    Graphics2D g2;
    g2 = g;
      for(int i = 0; i < Moblist.size(); i++){ 
          if(Moblist.get(i).isActive()){
          g2.drawImage(Icon.getImage(),Moblist.get(i).getXpos(),Moblist.get(i).getYpos(),this);
          }
      }
  }
  
  @Override
  public void run()
  {
        
      while(game_on)
    {
        if (menu_on)
        {
            background = new ImageIcon(getClass().getResource(background_imagem));
            repaint();
            if(isUpkey())
            {
                --menu_state;   
                setUpkey(false);
            }
            if(isDownkey())
            {
                ++menu_state;  
                setDownkey(false);
            }
            if(isSpacekey())
            {
                  game_running=(menu_state==1?true:false);
                  score_table=(menu_state==2?true:false);
                  menu_on = false;
                         
            }
            if(menu_state >= 4)menu_state = 1;
            if(menu_state <= 0)menu_state = 3;
            
            thdelay(300);
            
        }
        else if(game_running)
        {
            background = new ImageIcon(getClass().getResource(background_game));
            gameMeth();
        }
        else if(score_table)
        {
            if(isUpkey())
            {
                score_table=false;
                menu_on=true;
                setUpkey(false);
            }
             thdelay(300);
        }
        else
        {
            if(!menu_on && !game_running && !score_table)
                System.exit(0);
                
        }
    }
   
  }

   
  private void gameMeth()
  {

       
        criarRajada();
    
        Random gerar = new Random();
        
        Jogador.setActive(true);
        Jogador.setXpos(LARGURA/2);
        Jogador.setYpos(650);
        Jogador.setScorePt(0);
        
        this.explosionCounter = 0;
        this.MissilCounter = 0;
        this.Mob2Counter = 0;
        this.Mob3Counter = 0;
        this.mobCounter = 0;
        
        this.nivel = 10;
        
        while(game_running)
        {
            spawnRajada(100*rajadaPosition(Jogador));

            Mob3find();
            
            spawner(gerar);
            
            DestructionFinder();
            MissilFinder();
                 
            mover(Mob1);
            mover(Mob2);
            mover(Mob3);
            mover(Exp);
            mover(Shot);
                     
            Jogador.getKey(leftKey, rightKey, upkey, downkey, spacekey);
            
            if(Jogador.isActive()){Jogador.move();}
            
            if(Jogador.isActive())
            {
              ++this.MissilTimer;
              if(this.MissilTimer >= 20 ){
                  if(this.isSpacekey()){
                  this.MissilTimer = 0;
                  spawnMissil(Jogador);
                  }
              }  
            }
            
            clearKeyBoard();
            
            end_match();    
            
            thdelay(30);
            }
    }
  
   private void spawner(Random gerador){

    Random spawnPosition = new Random();
   
   if((--this.spownTime) <= 1)
   {
     this.spownTime = (20+10*nivel);
    if(gerador.nextInt(99) <= this.nivel*10){
    spawnMob2(spawnPosition.nextInt(LARGURA-4)+2);
    }else{
    spawnMob3(spawnPosition.nextInt(LARGURA-4)+2);
    }
    if(((++this.mobCounter) >= 5) && this.nivel >= 1)
    {
        this.mobCounter =0;
        --this.nivel;
    }
   }
  }
  
  private void end_match()
  {
    if(!Jogador.isActive() && !Exp.get(Jogador.getPlayerExplosion()).isActive())
    {
    Mob1.clear();
    Mob2.clear();
    Mob3.clear();
    Exp.clear();
    Shot.clear();
    objEscritor.insertRegistro("david",this.Jogador.getScorePt());
   game_running=false;
   menu_on = true;
    
    }
  }
  private void DestructionFinder()
  {
   prsdestruido<player,mob_1> destruidor_mob1 = new prsdestruido<> ();
   prsdestruido<player,mob_2> destruidor_mob2 = new prsdestruido<> ();
   prsdestruido<player,mob_3> destruidor_mob3 = new prsdestruido<> ();
        
   mob_1 refmob1 ;
   mob_2 refmob2 ;
   mob_3 refmob3 ;
        
   boolean mob_d = false;
   boolean colision = false;
   
   for(int i =0; i< Mob1.size(); i++)
   {
       refmob1 = Mob1.get(i);
       if(Jogador.isActive()&& refmob1.isActive()){
       mob_d = destruidor_mob1.compDist(Jogador, refmob1);
       if(mob_d)colision = destruidor_mob1.colisao(Jogador, refmob1);
       
       if(colision)
       {
           Jogador.setActive(false);
           Mob1.get(i).setActive(false);
           Jogador.setPlayerExplosion(spawnExp(Jogador));
           spawnExp(Mob1.get(i));
           break;
        }
       }
   }
   for(int i =0; i<Mob2.size(); i++)
   {
       refmob2 = Mob2.get(i);
       if(Jogador.isActive()&& refmob2.isActive()){
       mob_d = destruidor_mob2.compDist(Jogador, refmob2);
       if(mob_d)colision = destruidor_mob2.colisao(Jogador, refmob2);
       
       if(colision)
       {
           Jogador.setActive(false);
           Mob2.get(i).setActive(false);
           Jogador.setPlayerExplosion(spawnExp(Jogador));
           spawnExp(Mob2.get(i));
           break;
        }
       }
   }
   for(int i =0; i<Mob3.size(); i++)
   {
       refmob3 = Mob3.get(i);
       if(Jogador.isActive()&& refmob3.isActive()){
       mob_d = destruidor_mob3.compDist(Jogador, refmob3);
       if(mob_d)colision = destruidor_mob3.colisao(Jogador, refmob3);
       
       if(colision)
       {
           Jogador.setActive(false);
           Mob3.get(i).setActive(false);
           Jogador.setPlayerExplosion(spawnExp(Jogador));
           spawnExp(Mob3.get(i));
           break;
        }
       }
   }
    
  }
  
  private void MissilFinder()
  {
   prsdestruido<Missil,mob_1> destruidor_mob1 = new prsdestruido<> ();
   prsdestruido<Missil,mob_2> destruidor_mob2 = new prsdestruido<> ();
   prsdestruido<Missil,mob_3> destruidor_mob3 = new prsdestruido<> ();
        
   mob_1 refmob1 ;
   mob_2 refmob2 ;
   mob_3 refmob3 ;
   Missil refMissil;
        
   boolean mob_d = false;
   boolean colision = false;
   
   for(int j =0; j< Shot.size(); j++){
       
       refMissil = Shot.get(j);
        for(int i =0; i< Mob1.size(); i++)
        {
            refmob1 = Mob1.get(i);
            if(refMissil.isActive()&& refmob1.isActive()){
            mob_d = destruidor_mob1.compDist(refMissil, refmob1);
            if(mob_d)colision = destruidor_mob1.colisao(refMissil, refmob1);

            if(colision)
            {
                Jogador.setScorePt(Jogador.getScorePt()+Mob1.get(i).getScorePts());
                Shot.get(j).setActive(false);
                Mob1.get(i).setActive(false);
                spawnExp(Shot.get(j));
                spawnExp(Mob1.get(i));
                break;
             }
            }
        }
        if(colision)break;
   }
   for(int j =0; j< Shot.size(); j++){
       
       refMissil = Shot.get(j);
        for(int i =0; i< Mob2.size(); i++)
        {
            refmob2 = Mob2.get(i);
            if(refMissil.isActive()&& refmob2.isActive()){
            mob_d = destruidor_mob2.compDist(refMissil, refmob2);
            if(mob_d)colision = destruidor_mob2.colisao(refMissil, refmob2);

            if(colision)
            {
                Jogador.setScorePt(Jogador.getScorePt()+Mob2.get(i).getScorePts());
                Shot.get(j).setActive(false);
                Mob2.get(i).setActive(false);
                spawnExp(Shot.get(j));
                spawnExp(Mob2.get(i));
                break;
             }
            }
        }
        if(colision)break;
   }
   for(int j =0; j< Shot.size(); j++){
       
       refMissil = Shot.get(j);
        for(int i =0; i< Mob3.size(); i++)
        {
            refmob3 = Mob3.get(i);
            if(refMissil.isActive()&& refmob3.isActive()){
            mob_d = destruidor_mob3.compDist(refMissil, refmob3);
            if(mob_d)colision = destruidor_mob3.colisao(refMissil, refmob3);

            if(colision)
            {
                Jogador.setScorePt(Jogador.getScorePt()+Mob3.get(i).getScorePts());
                Shot.get(j).setActive(false);
                Mob3.get(i).setActive(false);
                spawnExp(Shot.get(j));
                spawnExp(Mob3.get(i));
                break;
             }
            }
        }
        if(colision)break;
   }  
  }
  
  private void Mob3find()
  {
            for(int i=0; i < this.Mob3.size(); i++)
            this.Mob3.get(i).getPlayerPosition(Jogador.getXpos(), Jogador.getYpos());
  }
    private void spawnMissil(player Ob)
  {
  this.Shot.add(new Missil(Ob));
  this.Shot.get(this.MissilCounter).setActive(true);
  ++this.MissilCounter;
  }
  private int spawnExp(Personagem Ob)
  {
  this.Exp.add(new ObjDestruido(Ob));
  this.Exp.get(this.explosionCounter).setActive(true);
  ++this.explosionCounter;
  return (this.explosionCounter-1);
  }
  
  private void spawnMob2(int pos)
  {
  this.Mob2.add(new mob_2(pos,0,true));
  this.Mob2.get(this.Mob2Counter).setActive(true);
  ++this.Mob2Counter;
  }
    
  private void spawnMob3(int pos)
  {
  this.Mob3.add(new mob_3(pos,0));
  this.Mob3.get(this.Mob3Counter).setActive(true);
  ++this.Mob3Counter;
  }
  private int rajadaPosition(player ob)
  {
     return ob.getXpos()/100;
  }
  //cria objetos da rajada
  private void criarRajada()
  {
      if(this.Mob1.size() < 5)
      {
      for(int i = 0; i < 5; i++)  
      this.Mob1.add(new mob_1(0,0));
      }
        
  }
//utilizar a variavel rajadaend para verificar se a rajada terminou
  private void spawnRajada(int position){
  
                  
            if(rajada_end != true)
              {
                if(rajadaTimer > this.intervaloRajada/2)
                    checkPrimeiro = true;
                    
                if(( --rajadaTimer <= 0))
                  {
                    if(checkPrimeiro == true)
                    {
                        checkPrimeiro = false;
                        posicaodaRajada = position;
                    }
                      
                  rajadaTimer = 20;   
                  rajada_end = rajada(posicaodaRajada);
                   }
              }
              else
              {
               
               if(!this.Mob1.get(this.Mob1.size()-1).isActive()){
               for(int i = 0; i < this.Mob1.size(); i++)
               {
               this.Mob1.get(i).setYpos(0);
               }  
   
               this.rajadaCounter = 0;
               rajada_end = false;
               rajadaTimer = this.intervaloRajada;
               }
               //indicar que acabou
            }
           
  }
  
  //libera cada missil, quando liberar toda a rajada retorna true
  private boolean rajada(int startpoint)
  {
     /*
 
     */
     if(this.rajadaCounter < this.Mob1.size())
     {
             this.Mob1.get(this.rajadaCounter).setXpos(startpoint + this.rajadaCounter*50);
             this.Mob1.get(this.rajadaCounter).setActive(true);
             this.rajadaCounter++;
             return false;
     }else{
     return true;
     }
     
  }
  //mover rajada
  private <T extends Personagem> void mover(ArrayList<T> Moblist)
  {
      for(int i = 0; i < Moblist.size(); i++){
                if(Moblist.get(i).isActive())
                    Moblist.get(i).move();
                }
  }
  public void clearKeyBoard()
  {
                setLeftKey(false);
                setRightKey(false);
                setUpkey(false);
                setDownkey(false);
                setSpacekey(false);
  }
          
  private class TAdapter extends KeyAdapter{
  
    @Override
    public void keyReleased(KeyEvent e)
        {
                 //setLeftKey(false); setRightKey(false);
                 //setUpkey(false); setDownkey(false); 
                 //setSpacekey(false);
        }
        @Override
    public void keyPressed(KeyEvent e)
        {
            if(game_on){
                
                int key = e.getKeyCode();
        
                setLeftKey((key == KeyEvent.VK_LEFT) ? true:false);
                setRightKey((key == KeyEvent.VK_RIGHT) ? true:false);
                setUpkey((key == KeyEvent.VK_UP) ? true:false);
                setDownkey((key == KeyEvent.VK_DOWN) ? true:false);
                setSpacekey((key == KeyEvent.VK_SPACE) ? true:false);
            }
        }
  }//fim TAdapter 
    
    private void thdelay(int time){
                
            try
            {
                Thread.sleep(time);
            }
            catch(InterruptedException e)
            {
                System.out.println("Erro de interrupção");
            }
    
    }
  
    public void setLeftKey(boolean leftKey) {
        this.leftKey = leftKey;
    }

    public void setRightKey(boolean rightKey) {
        this.rightKey = rightKey;
    }

    public void setUpkey(boolean upkey) {
        this.upkey = upkey;
    }

    public void setDownkey(boolean downkey) {
        this.downkey = downkey;
    }

    public void setSpacekey(boolean spacekey) {
        this.spacekey = spacekey;
    }
  
   public boolean isLeftKey() {
        return leftKey;
    }

    public boolean isRightKey() {
        return rightKey;
    }

    public boolean isUpkey() {
        return upkey;
    }

    public boolean isDownkey() {
        return downkey;
    }

    public boolean isSpacekey() {
        return spacekey;
    }
  
    
}
