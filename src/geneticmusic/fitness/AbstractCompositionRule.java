/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public abstract class AbstractCompositionRule implements CompositionRule{

   
    protected double weight;
    
    public AbstractCompositionRule(double weight){
        this.weight = weight;
    }
    
    
    
    
    protected abstract double evaluation(IChromosome ic);
    
    
    
    @Override
    public double evaluate(IChromosome ic) {
        return evaluation(ic)*weight;
    }
    
}
