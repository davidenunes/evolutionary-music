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
 * Voices must not cross each other
 * @author davide
 */
public class VIVoiceCross extends AbstractCompositionRule{
    
    public VIVoiceCross(double weight){
        super(weight);
    }

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;
        
        Gene[] genes = ic.getGenes();
        
        for(int i = 0; i< genes.length; i++){
            Note[] currentChord = (Note[]) genes[i].getAllele();
            
            if(currentChord[1].distance(currentChord[0]) > 0)//not crossed
                result+= 1/(genes.length*3.0);
            if(currentChord[2].distance(currentChord[1]) > 0)
                result+= 1/(genes.length*3.0);
            if(currentChord[3].distance(currentChord[2]) > 0)
                result+= 1/(genes.length*3.0);
            
        
        }
            
        
        
        return result;
    }

    @Override
    public String getName() {
        return "No Voice Cross Rule";
    }
    
}
