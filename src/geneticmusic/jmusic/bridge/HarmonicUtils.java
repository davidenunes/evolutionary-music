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
    
}
