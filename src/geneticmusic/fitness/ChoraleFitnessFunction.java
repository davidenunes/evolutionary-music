/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.choraleRules.HRInScale;
import geneticmusic.choraleRules.VEVoiceExtension;
import geneticmusic.choraleRules.VEVoiceIntervalRelation;
import geneticmusic.domain.Alteration;
import geneticmusic.domain.Note;
import geneticmusic.domain.Pitch;
import jm.constants.Scales;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class ChoraleFitnessFunction extends AbstractCompositionFitness {

   public ChoraleFitnessFunction(){
       super();
   }

    @Override
    protected void configRules() {
        Note tonic = new Note(Pitch.C, 5, Alteration.N, 4);
        
        VEVoiceExtension verticalExtension = new VEVoiceExtension(1/3.0);
        VEVoiceIntervalRelation verticalIntervalRelation = new VEVoiceIntervalRelation(1/3.0);
        HRInScale inScale = new HRInScale(Scales.MAJOR_SCALE, tonic, 1/3.0);
        
        addRule(verticalExtension);//add rule to rule set
        addRule(verticalIntervalRelation);
        addRule(inScale);
    }
    
}
