/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.genes.Note;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class RegisterFilterRule implements CompositionRule {

    @Override
    public double evaluate(IChromosome ic) {
        double result = 0.0;

         Gene [] genes = ic.getGenes();
         for (int i = 0; i < genes.length - 1; i++) {
            Note currentNote = (Note) genes[i].getAllele();
            Note nextNote = (Note) genes[i + 1].getAllele();

            
            

            if (currentNote.getOctave() > 3 && currentNote.getOctave() < 5) {
                result +=100;
            }
            
            if(currentNote.getOctave() == nextNote.getOctave()){
                result +=20;
            }
            
            

        }
        
        
       
        return result;
    }
}
