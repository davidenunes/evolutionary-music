/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.genes;

import geneticmusic.domain.Pitch;
import geneticmusic.domain.Alteration;
import geneticmusic.domain.Note;
import java.io.Serializable;
import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

/**
 * Each chorale gene represents a quarcher note in terms of rithm
 * each gene represents a part set of notes for the current tempo in the measure
 * 
 * the notes can be quarcher 4 or 
 * semiquarcher 8, so each gene can have 2 notes maximum 
 * 
 * //NOTE for simplicity reasons the notes are all quarchers for now
 * 
 * @author Davide Nunes
 */
public class ChoraleGene extends BaseGene implements Gene, Serializable{
  
    
    private Note  soprano;
    private Note  alto;
    private Note  tenor;
    private Note  bass;
  
    
    
        
    //constructor, creates a random note randomizer over all the parameters
    public ChoraleGene(Configuration conf) throws InvalidConfigurationException{
      
        
        this(conf, 
                NoteGenerator.getRandomNote(4, 5, 4, 4), 
                NoteGenerator.getRandomNote(4, 5, 4, 4), 
                NoteGenerator.getRandomNote(4, 5, 4, 4), 
                NoteGenerator.getRandomNote(4, 5, 4, 4));  
    }
    
    
    /**
     * Setup a new note withe the given values
     *  conf - Configuration - default configuration object
     *  pitch - Note - note pitch must be one from the enumerate Note
     *  octave - Integer - the octave in which the note is set mid = 0
     *  alt - Alteration - alterations on the note (SHARP or FLAT), can be
     *              null if you want to represent a natural note(without alterations)
     *  duration  Integer - the duration of the note, starting at 1
     * 
     * @throws InvalidConfigurationException 
     */
    public ChoraleGene(Configuration conf, Note soprano, 
                                           Note alto,
                                           Note tenor,
                                           Note bass    ) throws InvalidConfigurationException{
        super(conf);
        this.soprano = soprano;
        this.alto = alto;
        this.tenor = tenor;
        this.bass = bass;
    }
    
    

    @Override
    protected Object getInternalValue() {
        return new Note[] {soprano, alto, tenor, bass};
    }

    
     /**
     * Provides an implementation-independent means for creating new Gene
     * instances
    */
    @Override
    protected Gene newGeneInternal() {
        try {
        // Construct a NoteGene with the values from the currently created gene
        // -------------------------------------------------------------
        return new ChoraleGene(getConfiguration(),soprano,alto,tenor,bass); 
              
      } catch (InvalidConfigurationException ex) {
            throw new IllegalStateException(ex.getMessage());
      }
        
        
        
    }

    @Override
    public void setAllele(Object o) {
        Note[] notes = (Note []) o;
        this.soprano = notes[0];
        this.alto = notes[1];
        this.tenor = notes[2];
        this.bass = notes[3];
        
    }

    
    //for persistence in XML 
    @Override
    public String getPersistentRepresentation() throws UnsupportedOperationException {
        return "( "+soprano.toString()+" | "+
                   alto.toString()+" | "+
                   tenor.toString()+" | "+
                   bass.toString()+
                ") ";
    }

    //note parse from XML persistence
    @Override
    public void setValueFromPersistentRepresentation(String string) throws UnsupportedOperationException, UnsupportedRepresentationException {
      //TODO implements this if needed
    }

    @Override
    public void setToRandomValue(RandomGenerator rg) {
        if(!(rg instanceof NoteGenerator))
            throw new IllegalArgumentException("needs a Note generator in the configuration");
    
        NoteGenerator generator = (NoteGenerator) rg;
        this.soprano = generator.nextNote(4, 5, 4, 4); 
        this.alto = generator.nextNote(4, 5, 4, 4); 
        this.tenor = generator.nextNote(4, 5, 4, 4); 
        this.bass = generator.nextNote(4, 5, 4, 4);  
        
        
        
    }

    @Override
    public void applyMutation(int i, double d) {
       setToRandomValue(new NoteGenerator());
    }

    @Override
    public int compareTo(Object t) {
        ChoraleGene other = (ChoraleGene) t;
        
        Note[] otherNotes = (Note[]) other.getAllele();
        
        
        return (int) soprano.distance(otherNotes[0]);
    }
    
}
