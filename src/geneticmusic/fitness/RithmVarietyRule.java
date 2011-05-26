/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.fitness;

import geneticmusic.genes.Note;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class RithmVarietyRule implements CompositionRule{
    private double weight;
    
    public RithmVarietyRule(double weight){
        this.weight = weight;
    
    }
    
    
    @Override
    public double evaluate(IChromosome ic) {
       double result = 0.0;
        Gene genes[] = ic.getGenes();
        double numNotes = ic.size() * 1.0;

        for (int i = 0; i < genes.length - 1; i++) {
            Note currentNote = (Note) genes[i].getAllele();
            Note nextNote = (Note) genes[i + 1].getAllele();

            if(currentNote.getDuration() == nextNote.getDuration())
                result+= 1/numNotes;
            
            
             //System.out.println("distance " + distance);
           
        }
        
        
        
      
        
        return weight*result;
    }
    
}
