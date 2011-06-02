/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.jmusic.bridge;

import geneticmusic.domain.Note;

/**
 *
 * @author davide
 */
public class HarmonicUtils {
    public static boolean isFundamentalDuplicated(Note[] notes, int[] chord){
        boolean duplicated = false;
        int count = 0;
        for(Note note: notes){
            int normPitch = ConverterUtil.getNormalizedPitch(note);
            if(normPitch == chord[0])
                count++;
            
        
        }
        if(count == 2)
            duplicated = true;
        
        
        
        return duplicated;
    
    
    }
    
    public static int[] findChord(Note[] notes, int tonic, int[] scale){
        int[][] possibleChords = possibleChords(tonic, scale);
        for(int[] chord : possibleChords){
            if(isValidChord(notes, chord))
                return chord;
        }
        return null;
    }
    
    public static int[][] possibleChords (int tonic, int[] scale){
        int [][] chords = new int[scale.length][3];
        
        for(int i = 0; i< scale.length; i++){
            for(int j = 0; j<3; j++){
                chords[i][j] = ConverterUtil.getNormalizedPitch(
                                        tonic + scale[(i+j*2) % scale.length]
                        );
            }
        }
        
        
        return chords;
    }
    
    //one of the above chords
    public static boolean belongsToChord(Note note, int [] chord){
        for(int n : chord)
            if(ConverterUtil.getNormalizedPitch(note) == n)
                return true;
        
        return false;
    }
    
    
    /*
     * Verifies if the chord passed (Note[] notes) has the notes on the chord int[] chord presented
     * TODO add the omited 5th later
     * must have all notes from the chord
     */
    public static boolean isValidChord(Note[] notes, int[] chord){
         
        for(int n : chord){//for each note in the chord verify if there is one in the note array
            boolean found = false; 
            for(Note note : notes){
                 if(ConverterUtil.getNormalizedPitch(note) == n)
                     found = true;
            }
            if(!found) //if one not of the chord is not in the note array we dont have the whole chord
                return false;
         }
        return true;
    
    }
    
    
    /**
     * Returns true if the Note[] notes represent a valid chord
     * for any of the scale chords
     * 
     * 
     * @param notes
     * @param scale
     * @param tonic
     * @return 
     */
    public static boolean isValidChord(Note[] notes, int [] scale, int tonic){
        int[][] possibleChords = possibleChords(tonic, scale);
        for(int [] chord : possibleChords){
            if(isValidChord(notes, chord))
                return true;
        }
        return false;  
    }
    
}
