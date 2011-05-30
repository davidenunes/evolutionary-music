/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.genes;

import geneticmusic.domain.Pitch;
import geneticmusic.domain.Alteration;
import geneticmusic.domain.Note;
import java.util.Random;
import org.jgap.RandomGenerator;

/**
 *
 * @author davide nunes
 */
public class NoteGenerator implements RandomGenerator{
     private static Random randomGenerator;
     
     
    public Note nextNote(){
        return new Note(
                getRandomPitch(), 
                getRandomOctave(3,5), 
                getRandomAlteration(),
                getRandomDuration(2,4));
    }
    
    public Note nextNote(int minOctave, int maxOctave, int minRithm, int maxRithm){
         return new Note(
                getRandomPitch(), 
                getRandomOctave(3,5), 
                getRandomAlteration(),
                getRandomDuration(2,4));
    
    
    }
    
    public static Note getRandomNote(int minOctave, int maxOctave, int minRithm, int maxRithm){
         return new Note(
                getRandomPitch(), 
                getRandomOctave(3,5), 
                getRandomAlteration(),
                getRandomDuration(2,4));
    
    
    }
    
    
    
    
    /**
     * Get a random note from the available pitches
     * or a pause
     * 
     * TODO REENABLE THE RESTS
     * 
     * @return note Note a random note 
     */
    public static Pitch getRandomPitch(){   
         Pitch[] possibleNotes = Pitch.values();
         int index = 0;
        do{
            randomGenerator = new Random();  
            index = randomGenerator.nextInt(possibleNotes.length);
        }while(possibleNotes[index] == Pitch.R);
        
        return possibleNotes[index];
    }
    
     /**
     * Get a random Alteration from the available alterations
     * or a pause
     * 
     * @return note Note a random note 
     */
    public static Alteration getRandomAlteration(){
        randomGenerator = new Random();  
        Alteration[] possibleAlterations = Alteration.values();
        int index = randomGenerator.nextInt(possibleAlterations.length);
           
        return possibleAlterations[index];
    }
    
    /**
     * return a randomOctave
     * 
     */ 
    public static int getRandomOctave(int minOctave, int maxOctave){
        if(minOctave < Note.MIN_OCTAVE || maxOctave > Note.MAX_OCTAVE)
            //do nothing for now
        
        
        randomGenerator = new Random();
        int [] octaves = new int[maxOctave-minOctave + 1];
        
        int index = 0;
        for(int i = minOctave; i<= maxOctave; i++){
            octaves[index++] = i;
        }
        
        int selected = 0;
      
        
            selected = randomGenerator.nextInt(octaves.length);
        
       
        return octaves[selected];     
    }
    
    /**
     * Regenerates durations between min and max
     * 
     */ 
    public static int getRandomDuration(int min, int max){//TODO add augmentation points later
        if(min % 2 != 0 || max % 2 != 0 || min < 1 || max > 32)
           //do nothing for now
            
        
        
        randomGenerator = new Random();
        
        int numDurations = (int) (log2(max) - log2(min) + 1);
        int [] durations = new int[numDurations]; //1, 2, 4, 8, 16, 32 
        
        int index = 0;
        for(int i = min; i<= max; i=i*2){
            durations[index++] = i;
        }
        
        
        int selected = randomGenerator.nextInt(durations.length);
        
        randomGenerator = new Random(); 
        
        
        
        
        return durations[selected];     
    }
    
    
    

    @Override
    public int nextInt() {
        return randomGenerator.nextInt();
    }

    @Override
    public int nextInt(int i) {
        return randomGenerator.nextInt(i);
    }

    @Override
    public long nextLong() {
       return randomGenerator.nextLong();
    }

    @Override
    public double nextDouble() {
        return randomGenerator.nextDouble();
    }

    @Override
    public float nextFloat() {
       return randomGenerator.nextFloat();
    }

    @Override
    public boolean nextBoolean() {
        return randomGenerator.nextBoolean();
    }
    
    
    //util log2
    private static double log2(double n){
        return Math.log(n)/Math.log(2);
    }
    
}
