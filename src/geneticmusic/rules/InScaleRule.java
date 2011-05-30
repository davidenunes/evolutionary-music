package geneticmusic.rules;

import geneticmusic.fitness.CompositionRule;
import geneticmusic.domain.Note;
import geneticmusic.jmusic.bridge.ConverterUtil;
import jm.music.data.Phrase;
import jm.music.tools.PhraseAnalysis;
import org.jgap.IChromosome;

/**
 *  Notes belong to a given scale?
 * 
 * @author Davide Nunes
 */
public class InScaleRule implements CompositionRule {

    int[] scale;
    int tonic;
    double weight = 0.0;
    /**
     * 
     * @param scale
     * @param tonicP
     * @param weight Double - 0 to 1 weight
     */

    public InScaleRule(int[] scale, Note tonicP, double weight) {
        this.scale = scale;
        this.tonic = ConverterUtil.getPitch(tonicP);
        this.weight = weight;
    }

    @Override
    public double evaluate(IChromosome ic) {
        double result = 0.0;
        Phrase chromosome = ConverterUtil.convert(ic);

        jm.music.data.Note[] notes = chromosome.getNoteArray();
        for (jm.music.data.Note note : notes) {
            if (PhraseAnalysis.isScale(note, tonic, scale)) {
                result += (1 / (notes.length * 1.0));
            }
        }


        //System.out.println("evaluating: "+result);
        return weight*result;
    }
}
