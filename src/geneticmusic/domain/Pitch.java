/*
 * Enum with possible note pitches
 */
package geneticmusic.domain;

/**
 *
 * @author Davide Nunes
 */
public enum Pitch {
    A(0),
    B(1),
    C(1.5),
    D(2.5),
    E(3.5),
    F(4),
    G(5),
    R(-1); //for pauses;
    
    public static final int OCTAVE_DISTANCE = 6;
    
   private final double value;
   
   Pitch(double value){
       this.value = value;
   }
   
   public double getValue(){
       return value;
   }
   
    
   
   
 
}
