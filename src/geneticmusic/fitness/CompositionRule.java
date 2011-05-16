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
public interface CompositionRule {
    public double evaluate(IChromosome ic);    
}
