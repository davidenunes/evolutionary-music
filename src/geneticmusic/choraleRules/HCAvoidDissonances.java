/*
 * 
 * 
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
public class HCAvoidDissonances extends AbstractCompositionRule{
    
    private static final double MINORSECOND = 0.5;
    private static final double MAJOR_SEVEN = 5.5;
    private static final double AUGMENTED_FORTH = 3;
    
    public HCAvoidDissonances(double weight){
        super(weight);
    }

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;
        
        Gene[] genes = ic.getGenes();
        for(int i = 0; i< genes.length; i++){
            Note[] currentChord = (Note[]) genes[i].getAllele();
            
            
            
            double distanceSA = Math.abs(currentChord[0].distance(currentChord[1]));
            double distanceAT = Math.abs(currentChord[1].distance(currentChord[2]));
            double distanceTB = Math.abs(currentChord[2].distance(currentChord[3]));
            
            if(distanceSA != MINORSECOND && 
               distanceSA != MAJOR_SEVEN &&
               distanceSA != AUGMENTED_FORTH)
                    result+= 1/(genes.length*3.0);
                
             if(distanceAT != MINORSECOND && 
               distanceAT != MAJOR_SEVEN &&
               distanceAT != AUGMENTED_FORTH)
                    result+= 1/(genes.length*3.0);
                 
                  if(distanceTB != MINORSECOND && 
               distanceTB != MAJOR_SEVEN &&
               distanceTB != AUGMENTED_FORTH)
                    result+= 1/(genes.length*3.0);
        
        }
        
        
        
        return result;
    }

    @Override
    public String getName() {
        return "Avoid Dissonances Rule";
    }
    
}
