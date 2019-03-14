/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvade.the.destruction.modelo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Davidqpinho
 */
public class escreve {
    
    public File scoresList = new File("Scores.txt");
    
    public ArrayList<registros> lista = new ArrayList<>();
    
    public static final int numeroReg = 10;
    
    public escreve()
    {
    for(int i = 0;i<this.numeroReg;i++)
        this.lista.add(new registros("00000",0));
    }
    
    public void criaArquivo()
    {
          try{ if( scoresList.createNewFile() )
          {
              System.out.println("O arquivo foi criado");
          }else{
              System.out.println("O arquivo não foi criado, talvez ele já exista");
          }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
  
    public void escreverArquivo()
    {
    try( FileWriter fw = new FileWriter(this.scoresList) ){
        /*for(int counter = 0; counter < 6; counter++)
        {
            fw.write(1);
        }*/
        for(int i = 0;i<this.numeroReg;i++)
        {
        fw.write(this.lista.get(i).nome);
        
        for(int j = 4; j>=0 ;j--)
        fw.write(acharString((this.lista.get(i).valor - (this.lista.get(i).valor/(int)Math.pow(10,
                j+1)*(int)Math.pow(10, j+1))) ,(int)Math.pow(10, j)));
        }
        for(int counter = 0; counter < 6; counter++)
        {
            fw.write(1);
        }
        fw.flush();
     }catch(IOException ex){
      ex.printStackTrace();
    }
   }
  
    public void insertRegistro(String nome, int valor)
    {
         for(int i = 0;i<this.numeroReg;i++)
         {
         if(valor > this.lista.get(i).getValor())
            {
             this.lista.get(0).setNome(nome);
             this.lista.get(0).setValor(valor);
             break;
            }
         }
         
        Collections.sort (this.lista, new Comparator(){
                      public int compare(Object o1, Object o2) {
                      registros p1 = (registros) o1;
                      registros p2 = (registros) o2;
                      return p1.getValor() < p2.getValor() ? -1 : (p1.getValor() > p2.getValor() ? +1 : 0);
                    }});

   escreverArquivo();
    }
    
  public void lerRegistros()
    {
        for(int i = 0;i<this.numeroReg;i++)
        {
            System.out.println(this.lista.get(i).getNome() + " " +this.lista.get(i).getValor());
        }
    }
   
//      public void escreveArquivo(String nome){
//    try( FileWriter fw = new FileWriter(scoresList) ){
//        fw.write(nome);
//        fw.flush();
//    }catch(IOException ex){
//      ex.printStackTrace();
//    }
//   }
    private String acharString(int numero, int divisor)
    {
        int ret = numero/divisor;
        String temp;
        temp = new String();
        
        switch(ret)
        {
        case(0):
        temp = "0";
        break;
        case(1):
        temp = "1";
        break;    
        case(2):
        temp = "2";
        break;     
        case(3):
        temp = "3";
        break;     
        case(4):
        temp = "4";
        break;     
        case(5):
        temp = "5";
        break;     
        case(6):
        temp = "6";
        break;     
        case(7):
        temp = "7";
        break;    
        case(8):
        temp = "8";
        break;   
        case(9):
        temp = "9";
        break;     
            
        }
        return temp;
    }

   public void lerArquivo(){ 
       
       String temp, temp2;
       int total=0,nscore=0;
       temp = new String();
       temp2 = new String();
        
       try( FileReader fr = new FileReader(scoresList) ){
        int  c =   fr.read();

       
        while( c != -1){
            temp += (char) c;
            c =  fr.read();
        }
        
        System.out.println( temp );
    }catch(IOException ex){
      ex.printStackTrace();
    }
    
    int count=0,count2=0;
    

    for(nscore = 0;nscore<10;nscore++)
    {
    for(int i = 0; i < 5;i++,count++)
    {
    temp2 += temp.charAt(count);
    }
    for(int i = 0; i < 5;i++,count++)
    {
         String aChar = new Character((char)temp.charAt(count+count2)).toString();
         total +=(temp.charAt(count+count2)*(int)Math.pow(10, 9-count));
    }
    System.out.println("Nome: "+this.lista.get(nscore).getNome()+" Total:" + this.lista.get(nscore).getValor());
    }
   } 
//   public void lerArquivo(){ 
//    try( FileReader fr = new FileReader(scoresList) ){
//        int  c =   fr.read();
//        int temp;
//        int counter =4;
//        int total = 0;
//        while( c != -1){
//            System.out.print( (char) c );
//            temp=c;
//            String aChar = new Character((char)temp).toString();
//            System.out.print( aChar);
//            temp = Integer.parseInt(aChar) ;
//            if(counter>=0){
//            total += ((temp)*(int)Math.pow(10, counter));
//            counter--;}
//            c =  fr.read();
//          
//        }
//        System.out.println( "O total é " + total);
//    }catch(IOException ex){
//      ex.printStackTrace();
//    }
    
/*    public void lerArquivo()
    { 
    try( FileReader fr = new FileReader(this.scoresList) ){
        int  c = 0;// =   fr.read();
        int lerNome = 0;
        int total = 0;
        int temp;
        String aChar ;
        //StringBuffer sb = new StringBuffer();
        String ntemp;
        ntemp = new String("");
        for(int i =0;(i<this.numeroReg); i++){
            
           for(lerNome =0;(lerNome<10); lerNome++){ 
            
            if(lerNome<5)
            {
                c =  fr.read();
                //sb.insert(lerNome, (char) c);
                ntemp = ntemp + (char) c;
//                this.lista.get(i).setNome(sb.toString());
            this.lista.get(i).setNome(ntemp);
            }else if(lerNome<10)
            {
                temp=c;
                aChar = new Character((char)temp).toString();
                temp = Integer.parseInt(aChar) ;
                total += ((temp)*(int)Math.pow(10, 9 - lerNome));
                this.lista.get(i).setValor(total);
            }
            if(lerNome == 9)
            {
                //sb.delete(0, sb.length());
                ntemp = "";
                //lerNome = 0;
                total = 0;
                System.out.println("nome :"+this.lista.get(i).getNome()
                                    + "valor :"+ this.lista.get(i).getValor());
                
            }
           }
            //System.out.println( (char) c );
            
        }
        
    }catch(IOException ex){
      ex.printStackTrace();
    }
   }
*/   

}
