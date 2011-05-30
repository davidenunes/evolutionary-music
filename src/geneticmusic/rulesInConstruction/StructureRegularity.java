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
 * @author davide
 */
public class StructureRegularity implements CompositionRule {

    double weight;
    
    public StructureRegularity(double weight) {
        this.weight = weight;
    }

    
    
    @Override
    public double evaluate(IChromosome ic) {
        double result = 0.0;
        double totalDuration = 0.0;
        
        for (Gene current : ic.getGenes()) {
            Note currentNote = (Note) current.getAllele();
            totalDuration += currentNote.getDuration();
            
            if ((currentNote.getDuration() == 4) && !currentNote.getPitch().equals(Pitch.R)) {
                result += 0.5 * (1/ (ic.size()*1.0));
            }
        }
        
        if(totalDuration % 4 == 0)
            result += 0.5;
        
        return weight*result;


    }
}
