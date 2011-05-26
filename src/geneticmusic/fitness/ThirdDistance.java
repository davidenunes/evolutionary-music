/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.genes.Note;
import geneticmusic.genes.Pitch;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author daviden
 */
public class ThirdDistance implements CompositionRule{
    private IChromosome melody;
    
    
    public ThirdDistance(IChromosome melody){
        this.melody = melody;
    }
    
    @Override
    public double evaluate(IChromosome ic) {
        double value = 0.0;
        
        Gene[] genes = ic.getGenes();
        for (int i = 0; i < genes.length - 1; i++) {
            Note currentNote = (Note) genes[i].getAllele();
           
                        
         }
    }
    
}
