/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.rulesInConstruction;

import geneticmusic.fitness.CompositionRule;
import geneticmusic.domain.Note;
import geneticmusic.domain.Pitch;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class RegisterFilterRule implements CompositionRule {

    double weight = 0.0;

    public RegisterFilterRule(double weight) {
        this.weight = weight;
    }

    @Override
    public double evaluate(IChromosome ic) {
        double result = 0.0;
        double totalNotes = ic.size() * 1.0;

        Gene[] genes = ic.getGenes();
        for (int i = 0; i < genes.length - 1; i++) {
            Note currentNote = (Note) genes[i].getAllele();
            Note nextNote = (Note) genes[i + 1].getAllele();


            //must be a note to classify the octave
            if (!currentNote.getPitch().equals(Pitch.R)) {

                if (currentNote.getOctave() >= 3 && currentNote.getOctave() < 5) {
                    result += 0.9 * (1 / (totalNotes));
                }

                if (currentNote.getOctave() == nextNote.getOctave()) {
                    result += 0.1 * (1 / (totalNotes));
                }
            }



        }



        return weight * result;
    }
}
