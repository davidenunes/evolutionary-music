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
 * Voice Independence Rule
 * 
 * parallelism of 8 , 5  or unisson is forbiden 
 * between se same pair of voices
 * 
 * 
 * @author davide
 */
public class VIParallelism extends AbstractCompositionRule{
    
    public VIParallelism(double weight){
        super(weight);
    }

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;
        
        Gene[] genes = ic.getGenes();
        
        for(int i = 0; i<genes.length-1; i++){
             Note[] currentChord = (Note[]) genes[i].getAllele(); //get the current chord
             Note[] nextChord = (Note[]) genes[i+1].getAllele(); //get the next chord
             
        }
        
        
        return result;
    }
    
}
