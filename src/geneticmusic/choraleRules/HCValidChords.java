/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.choraleRules;

import geneticmusic.fitness.AbstractCompositionRule;
import org.jgap.IChromosome;

/**
 * Harmonic consistency rule
 * 
 * gives a bonus to correclty constructed chords
 * 
 * @author davide nunes
 */
public class HCValidChords extends AbstractCompositionRule{
    public HCValidChords(double weight){
        super(weight);
    }
    
    
    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;
        
        return result;
    }
    
}
