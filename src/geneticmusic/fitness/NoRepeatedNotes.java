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
 * @author davide
 */
public class NoRepeatedNotes implements CompositionRule {

    double weight = 0.0;

    public NoRepeatedNotes(double weight) {
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

               if(currentNote.distance(nextNote) == 0)
                    result +=  (-1 / (totalNotes));
                }
               

               
            }



        

     
        return weight * result;
    }
}
