/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.genes.Note;
import geneticmusic.genes.Pitch;
import geneticmusic.jmusic.bridge.ConverterUtil;
import jm.music.data.Phrase;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class PauseRegulationRule implements CompositionRule{

    @Override
    public double evaluate(IChromosome ic) {
        int restCount = 0;
        
        for(Gene current: ic.getGenes()){
            Note currentNote = (Note) current.getAllele();
            restCount = currentNote.getPitch().equals(Pitch.R) ? 1 : 0;
        } 
        
        return -restCount;
    }
    
}
