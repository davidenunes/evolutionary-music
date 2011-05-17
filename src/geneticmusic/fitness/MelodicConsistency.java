/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.genes.Note;
import geneticmusic.jmusic.bridge.ConverterUtil;
import jm.music.data.Phrase;
import jm.music.tools.PhraseAnalysis;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 * Reduce the jumps in a melody
 * 
 * @author Davide Nunes
 */
public class MelodicConsistency implements CompositionRule {
    
    double weight;
    
    public MelodicConsistency(double weight){
        this.weight = weight;
    }
    

    @Override
    public double evaluate(IChromosome ic) {
        double result = 0.0;
        Gene genes[] = ic.getGenes();

        for (int i = 0; i < genes.length - 1; i++) {
            Note currentNote = (Note) genes[i].getAllele();
            Note nextNote = (Note) genes[i + 1].getAllele();

            double distance = currentNote.distance(nextNote);
            distance = Math.abs(distance);

            result += 0.9 *  ((6-distance)/6 * (1/(genes.length-1)*1.0));
        }
        
        Phrase phr = ConverterUtil.convert(ic);
        double leapCompensationTario = PhraseAnalysis.leapCompensation(phr);
        
        result += 0.01 * (1 - leapCompensationTario);
        
        
        
        return weight*result;
    }
}
