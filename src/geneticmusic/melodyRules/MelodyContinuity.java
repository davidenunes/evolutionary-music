/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.melodyRules;

import geneticmusic.fitness.CompositionRule;
import geneticmusic.domain.Note;
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
public class MelodyContinuity implements CompositionRule {
    
    double weight;
    
    public MelodyContinuity(double weight){
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

            
             //System.out.println("distance " + distance);
           
            if(distance < 2 && distance >0 && currentNote.getOctave() > 3 && currentNote.getOctave() <5){
                result +=  1/(genes.length*1.0);
                //System.out.println("bonus");
            }
          
        }
        
        
        
      
        
        return weight*result;
    }

    @Override
    public String getName() {
        return "Melody Continuity Rule";
    }
}
