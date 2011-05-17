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
 *
 * @author davide
 */
public class MelodicConsistency implements CompositionRule {

    @Override
    public double evaluate(IChromosome ic) {
        double result = 0.0;
        Gene genes[] = ic.getGenes();

        for (int i = 0; i < genes.length - 1; i++) {
            Note currentNote = (Note) genes[i].getAllele();
            Note nextNote = (Note) genes[i + 1].getAllele();

            double distance = currentNote.distance(nextNote);
            distance = Math.abs(distance);

            result += distance < 2 ? 40 : -1;
            
            

        }
        
        Phrase phr = ConverterUtil.convert(ic);
        double leapCompensationTario = PhraseAnalysis.leapCompensation(phr);
        
        result += leapCompensationTario * -10.0;
        
        
        
        return result;
    }
}
