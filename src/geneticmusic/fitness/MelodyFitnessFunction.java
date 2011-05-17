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
        //Config in scale rule
        Note tonic = new Note(Pitch.C, 5, Alteration.N, 4);
        CompositionRule inScale = new InScaleRule(Scales.PENTATONIC_SCALE, tonic);
        
        //config note density rule
        CompositionRule densityRule = new NoteDensityRule(0.9, Durations.EIGHTH_NOTE); 
        
        //rythmic variety rule
        RithmVarietyRule rithmVariety = new RithmVarietyRule();
        
        //PauseRegulationRule pauseReg = new PauseRegulationRule();
        
        RegisterFilterRule octaveFilter = new RegisterFilterRule();
        
        MelodicConsistency melodyContinuity = new MelodicConsistency();
        
        CompassRegularity compassReg = new CompassRegularity();
        
        //add the rules
        rules.add(inScale);
        rules.add(densityRule);
        rules.add(rithmVariety);
        //rules.add(pauseReg);
        rules.add(octaveFilter);
        rules.add(melodyContinuity);
        rules.add(compassReg);
        
    
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
