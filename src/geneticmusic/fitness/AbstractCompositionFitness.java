/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import java.util.LinkedList;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public abstract class AbstractCompositionFitness extends FitnessFunction{
     protected LinkedList<CompositionRule> rules ;
     
     public AbstractCompositionFitness(){
          this.rules = new LinkedList<CompositionRule>();
          configRules();
     }
     
     protected void addRule(CompositionRule rule){
            rules.add(rule);
     }
     
 
    @Override
    public double evaluate(IChromosome ic) {
        double evaluation = 0.0;
        
        //apply the rules
        for(CompositionRule rule : rules){
            double currentEval = rule.evaluate(ic);
            evaluation += currentEval; //update evaluation with positive or negative value
        
           
        //  System.out.println("rule: "+ rule.getClass().toString());
        //   System.out.println("value: "+ currentEval);
        }
        return evaluation;
    }

    protected abstract void configRules();
}
