/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.choraleRules;

import geneticmusic.fitness.AbstractCompositionRule;
import org.jgap.IChromosome;

/**
 * Voice Logic Rule
 * 
 * melody must be concistence
 * 
 * 
 * @author Davide Nunes
 */
public class VLMelodyConsistency extends AbstractCompositionRule{

    public VLMelodyConsistency(double weight){
        super(weight);
    }
    @Override
    protected double evaluation(IChromosome ic) {
       double result = 0.0;
       
       
       return result;
    }
    
}
