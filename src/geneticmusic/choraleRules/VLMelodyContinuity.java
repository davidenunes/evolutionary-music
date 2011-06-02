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
 * Voice Logic Rule
 * 
 * melody must be concistence
 * 
 * 
 * @author Davide Nunes
 */
public class VLMelodyContinuity extends AbstractCompositionRule {

    public VLMelodyContinuity(double weight) {
        super(weight);
    }

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;

        Gene genes[] = ic.getGenes();

        for (int i = 0; i < genes.length - 1; i++) {
            Note[] currentChord = (Note[]) genes[i].getAllele();
            Note[] nextChord = (Note[]) genes[i + 1].getAllele();

            double distance = currentChord[0].distance(nextChord[0]);
            distance = Math.abs(distance);

            if (distance < 2 && distance >= 0) {
                result += 1 / ((genes.length - 1) * 1.0); //proporcional to the number of intervals
            }

        }
        return result;


    }

    @Override
    public String getName() {
        return "Melody Continuity Rule";
    }
}
