/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.rulesInConstruction;

import geneticmusic.fitness.CompositionRule;
import geneticmusic.domain.Note;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author davide nunes
 */
public class ThirdDistance implements CompositionRule{
    private IChromosome melody;
    
    
    public ThirdDistance(IChromosome melody){
        this.melody = melody;
    }
    
    @Override
    public double evaluate(IChromosome ic) {
        double value = 0.0;
        return value;
//        Gene[] genes = ic.getGenes();
//        for (int i = 0; i < genes.length - 1; i++) {
//            Note currentNote = (Note) genes[i].getAllele();
//           
//                        
//         }
    }
    
}
