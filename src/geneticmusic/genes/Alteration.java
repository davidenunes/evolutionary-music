/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.genes;

/**
 *  Enumerate of musical note pitch alterations
 * 
 * @author Davide Nunes
 */
public enum Alteration {
   S(0.5),//sharp 
   F(-0.5),//flat 
   N(0);//natural 
   
   
   public final double value;
   Alteration (double value){
       this.value = value;
   }
   
   public double getValue(){
       return this.value;
   }
}
