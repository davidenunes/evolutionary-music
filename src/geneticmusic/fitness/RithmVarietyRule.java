/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.jmusic.bridge.ConverterUtil;
import jm.music.data.Phrase;
import jm.music.tools.PhraseAnalysis;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class RithmVarietyRule implements CompositionRule{

    @Override
    public double evaluate(IChromosome ic) {
        Phrase chromosome = ConverterUtil.convert(ic);
        double rhythmicVariety = PhraseAnalysis.rhythmicVariety(chromosome);
        return 1-(rhythmicVariety*2);
    }
    
}
