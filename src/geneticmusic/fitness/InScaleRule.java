/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.genes.Note;
import geneticmusic.jmusic.bridge.ConverterUtil;
import jm.music.data.Phrase;
import jm.music.tools.PhraseAnalysis;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class InScaleRule implements CompositionRule{
    int [] scale;
    int tonic;
    
    public InScaleRule(int [] scale, Note tonicP){
        this.scale = scale;
        this.tonic = ConverterUtil.getPitch(tonicP);
    }
    
    
    @Override
    public double evaluate(IChromosome ic) {
       double result = 0.0;
       Phrase chromosome = ConverterUtil.convert(ic);
       
       jm.music.data.Note [] notes = chromosome.getNoteArray();
       for(jm.music.data.Note note: notes)
        if(PhraseAnalysis.isScale(note, tonic, scale))
                result+= (10 / (notes.length*1.0));
        else
                result -= (1/ (notes.length*1.0));
       
       
       //System.out.println("evaluating: "+result);
       return result;
    }
    
}
