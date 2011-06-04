/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.choraleRules.HCAvoidDissonances;
import geneticmusic.choraleRules.HCDuplicateFundamental;
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
        double weight = 1/10.0;
        
        Note tonic = new Note(Pitch.C, 5, Alteration.N, 4);
        
        VEVoiceExtension verticalExtension = new VEVoiceExtension(weight);
        VEVoiceIntervalRelation verticalIntervalRelation = new VEVoiceIntervalRelation(weight);
        HRInScale inScale = new HRInScale(Scales.MAJOR_SCALE, tonic, weight*2);
        HCValidChords validChords = new HCValidChords(weight, Scales.MAJOR_SCALE, tonic);
        VIVoiceCross voiceCross = new VIVoiceCross(weight*1);
        VLMelodyContinuity melodyConsistency = new VLMelodyContinuity(weight*2.2);
        VLMediumVoicesContinuity mediumVoices = new VLMediumVoicesContinuity(weight*1);
        HCAvoidDissonances avoidDissonances = new HCAvoidDissonances(weight*2);
        VIParallelism parallelism = new VIParallelism(weight*1);
        HCDuplicateFundamental duplicateFundamental = new HCDuplicateFundamental(weight*2, tonic, Scales.MAJOR_SCALE);
        
        addRule(verticalExtension);//add rule to rule set
        addRule(verticalIntervalRelation);
        addRule(inScale);
        addRule(validChords);
        addRule(voiceCross);
        addRule(melodyConsistency);
        addRule(mediumVoices);
        addRule(avoidDissonances);
        addRule(parallelism);
        addRule(duplicateFundamental);
    }
    
}
