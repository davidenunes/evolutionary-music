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
public class VLMediumVoicesContinuity extends AbstractCompositionRule {

    public VLMediumVoicesContinuity(double weight) {
        super(weight);
    }

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;

        Gene genes[] = ic.getGenes();

        for (int i = 0; i < genes.length - 1; i++) {
            Note[] currentChord = (Note[]) genes[i].getAllele();
            Note[] nextChord = (Note[]) genes[i + 1].getAllele();

            double distancea = currentChord[1].distance(nextChord[1]);
            distancea = Math.abs(distancea);
            
            double distancet = currentChord[2].distance(nextChord[2]);
            distancet = Math.abs(distancet);
            
            double distanceb = currentChord[2].distance(nextChord[2]);
            distanceb = Math.abs(distanceb);

            if (distancea < 2 && distancea > 0) {
                result += 1 / ((genes.length - 1) * 2.0); //proporcional to the number of intervals
            }
            
             if (distancet < 2 && distancet > 0) {
                result += 1 / ((genes.length - 1) * 2.0); //proporcional to the number of intervals
            }
             
              if (distanceb < 4 && distanceb > 0) {
                result += 1 / ((genes.length - 1) * 2.0); //proporcional to the number of intervals
            }
            

        }
        return result;


    }

    @Override
    public String getName() {
        return "Other Voices Continuity Rule";
    }
}
