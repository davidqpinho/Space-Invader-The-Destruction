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
   public class registros
{
       protected String nome;
       protected int valor;
 
   public registros(String nome, int valor)
       {
       this.nome=nome;
       this.valor=valor;
       }
   public String getNome(){
   return this.nome;
   }
   public void setNome(String nome)
   {
   this.nome=nome;
   }
   public int getValor(){
   return this.valor;
   }
   public void setValor(int valor)
   {
   this.valor=valor;
   }
   
}
