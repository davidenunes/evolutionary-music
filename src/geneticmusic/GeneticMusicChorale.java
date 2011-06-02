/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic;

import geneticmusic.fitness.ChoraleFitnessFunction;
import geneticmusic.genes.ChoraleGene;
import geneticmusic.genes.NoteGenerator;
import geneticmusic.jmusic.bridge.ConverterUtil;
import javax.swing.JFrame;
import jm.JMC;
import jm.util.Write;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.UnsupportedRepresentationException;
import org.jgap.audit.EvolutionMonitor;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.TournamentSelector;

/**
 *
 * @author daviden
 */
public class GeneticMusicChorale implements JMC {
    private static final int FIELD1 = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidConfigurationException, UnsupportedOperationException, UnsupportedRepresentationException {

        //configuration object
        Configuration cfg = new DefaultConfiguration();
        ChoraleFitnessFunction fitnessF = new ChoraleFitnessFunction();
        
        
         
        
//          EvolutionMonitor monitor = new EvolutionMonitor();
//          cfg.setMonitor(monitor);
          
          
        cfg.setFitnessFunction(fitnessF);


        
        //**************create a sample cromossome************************
        
        Gene[] sampleGenes = new Gene[8];
        for(int i=0; i< sampleGenes.length; i++)
            sampleGenes[i] = new ChoraleGene(cfg);

      
        
        
        Chromosome sampleChromosome = new Chromosome(cfg, sampleGenes);

        cfg.setSampleChromosome(sampleChromosome);
        //***************************************************************
        
        System.out.println("GA configuration:");
        System.out.println("Percentage selected from previous generations:"+cfg.getSelectFromPrevGen());
        //cfg.setNaturalSelector(new BestChromosomesSelector(cfg, 1));
        cfg.setNaturalSelector(new TournamentSelector(cfg, 20, 0.7));
        System.out.println("Selection Operator: " + cfg.getNaturalSelector());
        
        
        //set population size
        cfg.setPopulationSize( 40 );
        
        //set note generator
        cfg.setRandomGenerator(new NoteGenerator());
        
        
        //construct a population genotype
        Genotype population = Genotype.randomInitialGenotype( cfg );
        
        
        
        
        
        //// evolve and evaluate
        double currentFitness = 0.0;
        
        int i = 0;
        double lastFitness = 0.0;
        
        /***********************PRESENT DATA ON A CHART************************/
        XYSeries fitnessSeries = new XYSeries("Fittest Fitness");
        
       
         XYSeriesCollection dataset = new XYSeriesCollection(fitnessSeries);
         XYSeriesCollection rulesDataset = fitnessF.getRuleDataset();
        
         
         JFreeChart fitnessChart = ChartFactory.createXYLineChart("Fittest Fitness Evolution", 
                                                                "Generation", 
                                                                "Fitness", dataset, 
                                                                PlotOrientation.VERTICAL,
                                                                true, //legend
                                                                true, //tooltips
                                                                false); //url
         
         JFreeChart rulesChart = ChartFactory.createXYLineChart("Rule Average Evaluation", 
                                                                "Generation", 
                                                                "Fitness", rulesDataset, 
                                                                PlotOrientation.VERTICAL,
                                                                true, //legend
                                                                true, //tooltips
                                                                false); //url
         
        
        //add chart to panel
       ChartPanel chartPanel = new ChartPanel(fitnessChart);
       JFrame chartFrame = new JFrame("Fittests Fitness");
       chartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       chartFrame.setContentPane(chartPanel);
       chartFrame.pack();
       chartFrame.setVisible(true);
       
       
          //add chart to panel
       ChartPanel rulesPanel = new ChartPanel(rulesChart);
       JFrame rulesFrame = new JFrame("Rule Fitness");
       rulesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       rulesFrame.setContentPane(rulesPanel);
       rulesFrame.pack();
       rulesFrame.setVisible(true);
        
        do{
            lastFitness = currentFitness;
            population.evolve();
            currentFitness = population.getFittestChromosome().getFitnessValue();
            
            fitnessSeries.add(i, currentFitness);//update series
            //System.out.println("Current fitness: "+currentFitness);
            i++;
            
            //System.out.println(i);
        }while(i<500);
        
       
       
        
       
        IChromosome chm = population.getFittestChromosome();
        
        
        System.out.println(chm.toString());
        Write.midi(ConverterUtil.getChoraleScore(chm), "test_chorale.mid");
        


    }
}
