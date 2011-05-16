/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Davide Nunes
 */
public class MelodyFitnessFunction extends FitnessFunction{

    @Override
    protected double evaluate(IChromosome ic) {
        return 100;
    }
    
}
