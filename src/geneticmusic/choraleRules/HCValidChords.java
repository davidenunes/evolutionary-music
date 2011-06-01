/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.choraleRules;

import geneticmusic.domain.Note;
import geneticmusic.fitness.AbstractCompositionRule;
import geneticmusic.jmusic.bridge.ConverterUtil;
import geneticmusic.jmusic.bridge.HarmonicUtils;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 * Harmonic consistency rule
 * 
 * gives a bonus to correclty constructed chords
 * 
 * @author davide nunes
 */
public class HCValidChords extends AbstractCompositionRule{
    private int[] scale;
    private int tonic;
    
    public HCValidChords(double weight, int[] scale, Note tonic){
        super(weight);
        this.scale = scale;
        this.tonic = ConverterUtil.getPitch(tonic);
    }
    
    
    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;
        
        Gene[] genes = ic.getGenes();
        for(int i = 0; i < genes.length; i++){
            Note[] currentChord = (Note[]) genes[i].getAllele();
            if(HarmonicUtils.isValidChord(currentChord, scale, tonic))
                result+= 1/(genes.length*1.0);
        }
            
        
        return result;
    }
    
}
