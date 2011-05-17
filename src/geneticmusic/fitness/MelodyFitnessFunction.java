/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.genes.Alteration;
import geneticmusic.genes.Note;
import geneticmusic.genes.Pitch;
import java.util.LinkedList;
import jm.constants.Durations;
import jm.constants.Scales;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Davide Nunes
 */
public class MelodyFitnessFunction extends FitnessFunction{
    
    LinkedList<CompositionRule> rules ;
    
    public MelodyFitnessFunction(){
        //init rule list
        this.rules = new LinkedList<CompositionRule>();
        
        //configure all the rules 
        configRules();
    }
    
    private void configRules(){
        //Normalized rules
        Note tonic = new Note(Pitch.C, 5, Alteration.N, 4);
        CompositionRule inScale = new InScaleRule(Scales.PENTATONIC_SCALE, tonic, 0.2);
        
        RegisterFilterRule octaveFilter = new RegisterFilterRule(0.6);
        
        MelodicConsistency melodyContinuity = new MelodicConsistency(0.6);
         StructureRegularity compassReg = new StructureRegularity(0.2);
        
        //--------------------------------------------------------
        
        
        
        //config note density rule
        CompositionRule densityRule = new NoteDensityRule(0.9, Durations.C); 
        
        //rythmic variety rule
        RithmVarietyRule rithmVariety = new RithmVarietyRule();
        
        //PauseRegulationRule pauseReg = new PauseRegulationRule();
        
       
        
        
        
 
        
        PausesAfterShortNotes restsAfterShort = new PausesAfterShortNotes();
        
        //add the rules
        rules.add(inScale);
        rules.add(octaveFilter);
        rules.add(melodyContinuity);
        rules.add(compassReg);
//        rules.add(densityRule);
//        rules.add(rithmVariety);
//        //rules.add(pauseReg);
//        rules.add(octaveFilter);
//        rules.add(melodyContinuity);
//        rules.add(compassReg);
//        rules.add(restsAfterShort);
        
    
    }
    

    @Override
    protected double evaluate(IChromosome ic) {
        double evaluation = 0.0;
        
        //apply the rules
        for(CompositionRule rule : rules)
            evaluation += rule.evaluate(ic); //update evaluation with positive or negative value
        
        
        return evaluation;
    }
    
}
