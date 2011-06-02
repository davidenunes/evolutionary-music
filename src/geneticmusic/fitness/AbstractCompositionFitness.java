/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public abstract class AbstractCompositionFitness extends FitnessFunction{
     protected LinkedList<CompositionRule> rules ;
     protected XYSeriesCollection rulesDataset;
     protected static int chromosomesEvaluated = 0;
     protected static int currentGeneration = 1;
     protected HashMap<String,Double> ruleValues;
   
     
     public AbstractCompositionFitness(){
         this.ruleValues = new HashMap<String, Double>();
                 
          this.rules = new LinkedList<CompositionRule>();
          rulesDataset = new XYSeriesCollection();
          configRules();
     }
     
     protected void addRule(CompositionRule rule){
            rulesDataset.addSeries(new XYSeries(rule.getName()));
            rules.add(rule);
     }
     
     public XYSeriesCollection getRuleDataset(){
         return rulesDataset;
     }
     
 
    @Override
    public double evaluate(IChromosome ic) {
        chromosomesEvaluated++;
        
        double evaluation = 0.0;
       
        
        //apply the rules
        for(CompositionRule rule : rules){
            double currentEval = rule.evaluate(ic);
            
            /*** sum the values , calculate the average at the end of the population ***/
            double storedEval = 0.0;
            if(ruleValues.containsKey(rule.getName()))
                storedEval = ruleValues.get(rule.getName());
            ruleValues.put(rule.getName(), storedEval+=currentEval);
            /****************************************************************************/
            
            evaluation += currentEval; //update evaluation with positive or negative value
        
           
        //  System.out.println("rule: "+ rule.getClass().toString());
        //   System.out.println("value: "+ currentEval);
        }
        
        
        int populationSize = ic.getConfiguration().getPopulationSize();
     
        if(chromosomesEvaluated == populationSize){//calculate the average
            
           
            for(String rule : ruleValues.keySet())//for each rule;
                rulesDataset.getSeries(rule).add(
                            currentGeneration, 
                            (ruleValues.get(rule) / chromosomesEvaluated) //average classif
                        );
            
            
            
            ruleValues = new HashMap<String, Double>(); 
            
            chromosomesEvaluated = 0;
            currentGeneration++;
        
        }
        
        
        return evaluation;
    }

    protected abstract void configRules();
}
