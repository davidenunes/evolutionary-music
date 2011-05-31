/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.choraleRules;

import geneticmusic.domain.Note;
import geneticmusic.fitness.AbstractCompositionRule;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class VEVoiceIntervalRelation extends AbstractCompositionRule{
    private static final int SOPRANO = 0;
    private static final int ALTO = 1;
    private static final int TENOR = 2;
    private static final int BASS = 3;
    
    
    public VEVoiceIntervalRelation(double weight){
        super(weight);
    }
    
    
    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;


        //for each note within range, the note gets a score
        Gene[] genes = ic.getGenes();
        double totalChords = genes.length;


        for (int i = 0; i < genes.length; i++) {
            Note[] currentNotes = (Note[]) genes[i].getAllele(); //get the current chord
            if(withinRange(SOPRANO, ALTO, currentNotes[0], currentNotes[1]))
                 result += 1 / (totalChords * 3.0);
            if(withinRange(ALTO, TENOR, currentNotes[1],currentNotes[2]))
                 result += 1 / (totalChords * 3.0);
            if(withinRange(TENOR, BASS, currentNotes[2],currentNotes[3]))
                 result += 1 / (totalChords * 3.0);
        
        }
        
        
        
        return result;
    }
    
    /**
     * Interval between S A / A T <= 8
     * Interval between T B <= 12
     * 
     * @param position
     * @param note
     * @return 
     */
     private static boolean withinRange(int note1, int note2, Note firstNote, Note secondNote) {
         boolean result = false;
         if(note1 == TENOR && note2 == BASS){
             result = Math.abs(firstNote.distance(secondNote)) <= 12;
         }
         else
             result = Math.abs(firstNote.distance(secondNote)) <= 8;
         return result;
     }
    
}
