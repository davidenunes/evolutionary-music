/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.choraleRules;

import geneticmusic.fitness.AbstractCompositionRule;
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
        
        return result;
    }
    
}
