/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.rulesInConstruction;

import geneticmusic.fitness.CompositionRule;
import geneticmusic.domain.Note;
import geneticmusic.domain.Pitch;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author daviden
 */
public class PausesAfterShortNotes implements CompositionRule{

    @Override
    public double evaluate(IChromosome ic) {
        double result = 0.0;
         Gene [] genes = ic.getGenes();
         for (int i = 0; i < genes.length - 1; i++) {
            Note currentNote = (Note) genes[i].getAllele();
            Note nextNote = (Note) genes[i + 1].getAllele();
            
            if(nextNote.getPitch().equals(Pitch.R) &&
               !currentNote.getPitch().equals(Pitch.R) &&
                    currentNote.getDuration() > 8)
                result = -10.0;
            
         }
         
         return result;
    }
    
}
