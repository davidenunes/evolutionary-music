/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.genes;

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
                getRandomOctave(), 
                getRandomAlteration(),
                getRandomDuration());
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
    public static int getRandomOctave(){
        randomGenerator = new Random();
        int [] octaves = new int[(Note.MAX_OCTAVE-Note.MIN_OCTAVE) + 1];
        
        int index = 0;
        for(int i = Note.MIN_OCTAVE; i<= Note.MAX_OCTAVE; i++){
            octaves[index++] = i;
        }
        
        
        int selected = randomGenerator.nextInt(octaves.length);
        
        randomGenerator = new Random(); 
        return octaves[selected];     
    }
    
    /**
     * only generates regular durations for now
     * 
     */ 
    public static int getRandomDuration(){//TODO add augmentation later
        randomGenerator = new Random();
        int [] durations = new int[6]; //1, 2, 4, 8, 16, 32 
        
        int index = 0;
        for(int i = 0; i<= 5; i++){
            durations[index++] = (int) Math.pow(2, i);
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
    
}
