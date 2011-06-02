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
 *
 * @author davide
 */
public class HCDuplicateFundamental extends AbstractCompositionRule{
    private int tonic;
    private int[] scale;
    
    public HCDuplicateFundamental(double weight, Note tonic, int[] scale){
        super(weight);
        this.tonic = ConverterUtil.getPitch(tonic);
        this.scale = scale;
    }
    

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;
        Gene[] genes = ic.getGenes();
        
        for(Gene g : genes){
            Note[] chord = (Note[]) g.getAllele();
            int[] foundChord = HarmonicUtils.findChord(chord, tonic, scale);
            if(foundChord != null)
                if(HarmonicUtils.isFundamentalDuplicated(chord, foundChord))
                    result+= 1/(genes.length*1.0);
        }
        
        
        return result;
    }
    
    

    @Override
    public String getName() {
        return "Duplicate Fundamental Rule";
    }
    
}
