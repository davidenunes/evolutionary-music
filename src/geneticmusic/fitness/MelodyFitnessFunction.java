/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.rules.RithmContinuity;
import geneticmusic.rules.MelodyContinuity;
import geneticmusic.rules.InScaleRule;
import geneticmusic.domain.Alteration;
import geneticmusic.domain.Note;
import geneticmusic.domain.Pitch;
import jm.constants.Durations;
import jm.constants.Scales;

/**
 *
 * @author Davide Nunes
 */
public class MelodyFitnessFunction extends AbstractCompositionFitness{
    
   
    
    public MelodyFitnessFunction(){
       super();
    }
    
    @Override
    protected void configRules(){
        //Normalized rules
        Note tonic = new Note(Pitch.C, 5, Alteration.N, 4);
        InScaleRule inScale = new InScaleRule(Scales.MAJOR_SCALE, tonic, 0.5);
        MelodyContinuity melodyContinuity = new MelodyContinuity(0.5);
    
        RithmContinuity rithmVariety = new RithmContinuity(0.5);
//        RegisterFilterRule octaveFilter = new RegisterFilterRule(0.5);
//        
//        MelodicConsistency melodyContinuity = new MelodicConsistency(0.5);
//         StructureRegularity compassReg = new StructureRegularity(0);
//        
        //--------------------------------------------------------
        
        
        
      
        
        //rythmic variety rule
       
        
        //PauseRegulationRule pauseReg = new PauseRegulationRule();
        
       
        
        
        
 
        
        //add the rules
        rules.add(inScale);
        rules.add(melodyContinuity);
//        rules.add(compassReg);
//        rules.add(densityRule);
       rules.add(rithmVariety);
//        //rules.add(pauseReg);
//        rules.add(octaveFilter);
//        rules.add(melodyContinuity);
//        rules.add(compassReg);
//        rules.add(restsAfterShort);
        
    
    }
    

   
    
}
