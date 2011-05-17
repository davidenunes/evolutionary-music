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

        for (Gene current : ic.getGenes()) {
            Note currentNote = (Note) current.getAllele();
            if (currentNote.getOctave() > 3 && currentNote.getOctave() < 5) {
                result +=3;
            }
        }
        return result;
    }
}
