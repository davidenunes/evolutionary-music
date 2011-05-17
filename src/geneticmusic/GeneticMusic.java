/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic;

import geneticmusic.fitness.MelodyFitnessFunction;
import geneticmusic.genes.NoteGene;
import geneticmusic.genes.NoteGenerator;
import geneticmusic.jmusic.bridge.ConverterUtil;
import jm.JMC;
import jm.util.Write;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.UnsupportedRepresentationException;
import org.jgap.impl.DefaultConfiguration;

/**
 *
 * @author daviden
 */
public class GeneticMusic implements JMC {
    private static final int FIELD1 = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidConfigurationException, UnsupportedOperationException, UnsupportedRepresentationException {

        //configuration object
        Configuration cfg = new DefaultConfiguration();

        //fet fitness function
        FitnessFunction fitnessF = new MelodyFitnessFunction();


        //setup fitness function in the configuration object
        cfg.setFitnessFunction(fitnessF);


        
        //**************create a sample cromossome************************
        
        Gene[] sampleGenes = new Gene[10];
        for(int i=0; i< sampleGenes.length; i++)
            sampleGenes[i] = new NoteGene(cfg);

        Chromosome sampleChromosome = new Chromosome(cfg, sampleGenes);

        cfg.setSampleChromosome(sampleChromosome);
        //***************************************************************
        
        //set population size
        cfg.setPopulationSize( 500 );
        
        //set note generator
        cfg.setRandomGenerator(new NoteGenerator());
        
        
        //construct a population genotype
        Genotype population = Genotype.randomInitialGenotype( cfg );
        
        
        
        
        
        //// evolve and evaluate
        double currentFitness = 0.0;
        
        int i = 0;
        
        while(i<1000){
            population.evolve();
            currentFitness = population.getFittestChromosome().getFitnessValue();
            //System.out.println("Current fitness: "+currentFitness);
            i++;
        }
        
        
        
        
        
        
        IChromosome chm = population.getFittestChromosome();
        
        System.out.println(chm.toString());
        Write.midi(ConverterUtil.getScore(chm), "test.mid");
        
//        
//        
//        int[] pitchSop = {C5, G4, E4, D4, G4, A4, C4, D4, E4, D4, F4, E4, A4, G4, E4};
//        double[] rhythmSop = {C, C, DC, Q, C, C, C, C, C, C, C, C, M, C, C};
//        int[] pitchAlto = {E4, D4, C4, A3, B3, C4, D4, C4, D4, CS4};
//        double[] rhythmAlto = {C, C, SB, C, C, C, C, SB, C, C};
//        int[] pitchTenor = {G3, C4, B3, A3, G3, F3, E3, G3, G3, C4, B3, A3, B3, A3};
//        double[] rhythmTenor = {M, C, Q, Q, C, C, C, C, M, C, C, M, C, C};
//        int[] pitchBass = {C3, B2, A2, G2, F2, E2, F2, A2, G2, C3, B2, A2, G2, F2, F3, E3, A3};
//        double[] rhythmBass = {C, C, C, Q, Q, C, C, C, C, C, C, C, C, C, C, C, C};
//
//
//
//        Phrase soprano = new Phrase();
//        Phrase alto = new Phrase();
//        Phrase tenor = new Phrase();
//        Phrase bass = new Phrase();
//
//
//        soprano.addNoteList(pitchSop, rhythmSop);
//        alto.addNoteList(pitchAlto, rhythmAlto);
//        tenor.addNoteList(pitchTenor, rhythmTenor);
//        bass.addNoteList(pitchBass, rhythmBass);
//
//
//
//        Part s = new Part("Soprano", ACOUSTIC_GUITAR, 1);
//        Part a = new Part("Alto", ACOUSTIC_GUITAR, 2);
//        Part t = new Part("Tenor", ACOUSTIC_GUITAR, 3);
//        Part b = new Part("Bass", ACOUSTIC_GUITAR, 4);
//
//
//        // add the phrases to the parts
//        s.addPhrase(soprano);
//        a.addPhrase(alto);
//        t.addPhrase(tenor);
//        b.addPhrase(bass);
//
//
//        //create a score
//        Score score = new Score("Chorale");
//
//        //add the parts to the score
//        score.addPart(s);
//        score.addPart(a);
//        score.addPart(t);
//        score.addPart(b);
//
//
//        //display the result for the world to see
//        //View.show(score);
//
//        // save score as a MIDI file
//        Write.midi(score, "Chorale.mid");
//
////        for (int i = 0; i < 100; i++) {
////            System.out.println(NoteGenerator.nextNote().toString());
////        }
//        
////        Note randNote = NoteGenerator.nextNote();
////       
////        Configuration cfg = new DefaultConfiguration();
////        NoteGene gene = new NoteGene(cfg, randNote);
////        String representation = gene.getPersistentRepresentation();
////        System.out.println(representation);
////         
////        NoteGene gene2 = new NoteGene(cfg);
////        gene2.setValueFromPersistentRepresentation(representation);
////        
////        System.out.print(gene2.getAllele().toString());
//            
//
//        Note note1 = new Note(Pitch.A, 3, Alteration.S, 4);
//        Note note2 = new Note(Pitch.C, 2, Alteration.S, 4);
//        
//        System.out.println("Note1: "+note1.toString());
//        System.out.println("Note2: "+note2.toString());
//        
//        System.out.println("tone distance: "+note1.distance(note2));








    }
}
