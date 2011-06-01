/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.choraleRules.HCAvoidDissonances;
import geneticmusic.choraleRules.HCValidChords;
import geneticmusic.choraleRules.HRInScale;
import geneticmusic.choraleRules.VEVoiceExtension;
import geneticmusic.choraleRules.VEVoiceIntervalRelation;
import geneticmusic.choraleRules.VIParallelism;
import geneticmusic.choraleRules.VIVoiceCross;
import geneticmusic.choraleRules.VLMediumVoicesContinuity;
import geneticmusic.choraleRules.VLMelodyContinuity;
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
        double weight = 1/8.0;
        
        Note tonic = new Note(Pitch.C, 5, Alteration.N, 4);
        
        VEVoiceExtension verticalExtension = new VEVoiceExtension(weight);
        VEVoiceIntervalRelation verticalIntervalRelation = new VEVoiceIntervalRelation(weight);
        HRInScale inScale = new HRInScale(Scales.MAJOR_SCALE, tonic, weight);
        HCValidChords validChords = new HCValidChords(weight, Scales.MAJOR_SCALE, tonic);
        VIVoiceCross voiceCross = new VIVoiceCross(weight);
        VLMelodyContinuity melodyConsistency = new VLMelodyContinuity(weight);
        VLMediumVoicesContinuity mediumVoices = new VLMediumVoicesContinuity(weight);
        HCAvoidDissonances avoidDissonances = new HCAvoidDissonances(weight);
        VIParallelism parallelism = new VIParallelism(weight);
        
        addRule(verticalExtension);//add rule to rule set
        addRule(verticalIntervalRelation);
        addRule(inScale);
        addRule(validChords);
        addRule(voiceCross);
        addRule(melodyConsistency);
        addRule(mediumVoices);
        addRule(avoidDissonances);
       // addRule(parallelism);
    }
    
}
