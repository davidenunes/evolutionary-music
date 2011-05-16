/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.genes;

import java.io.Serializable;
import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

/**
 * Class that represents a musical note as a gene
 * to build a melody chromosome 
 * 
 * 
 * @author Davide Nunes
 */
public class NoteGene extends BaseGene implements Gene, Serializable{
    private Note note;
  
    
    
        
    //constructor, creates a random note randomizer over all the parameters
    public NoteGene(Configuration conf) throws InvalidConfigurationException{
        this(conf,new NoteGenerator().nextNote());   
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
    public NoteGene(Configuration conf, Note note) throws InvalidConfigurationException{
        super(conf);
        this.note = note;
    }
    
    

    @Override
    protected Object getInternalValue() {
        return this.note;
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
        return new NoteGene(getConfiguration(),this.note); 
              
      } catch (InvalidConfigurationException ex) {
            throw new IllegalStateException(ex.getMessage());
      }
        
        
        
    }

    @Override
    public void setAllele(Object o) {
        this.note = (Note) o;
    }

    
    //for persistence in XML 
    @Override
    public String getPersistentRepresentation() throws UnsupportedOperationException {
        return note.toString();
    }

    //note parse from XML persistence
    @Override
    public void setValueFromPersistentRepresentation(String string) throws UnsupportedOperationException, UnsupportedRepresentationException {
        String retrieved = string;
        
        //retrieve pitch
        String pitchS = retrieved.substring(0, 1);
        Pitch pitch = Pitch.valueOf(pitchS);
        
     
        Alteration alteration = null;
        int octave = 0;
        
        
        if(!pitch.equals(Pitch.R)){
            //retrieve alteration
            String alterationS =  retrieved.substring(2,3);
            alteration = Alteration.valueOf(alterationS);
            
            //retrieve octave
            String octaveS = retrieved.substring(4,5);
            octave = Integer.parseInt(octaveS);
        }
        
        //retrieve duration
        String durationS = retrieved.substring(6);
        int duration = Integer.parseInt(durationS);
        
        this.note = new Note(pitch, octave, alteration, duration);
        
    }

    @Override
    public void setToRandomValue(RandomGenerator rg) {
        if(!(rg instanceof NoteGenerator))
            throw new IllegalArgumentException("needs a Note generator in the configuration");
    
        NoteGenerator generator = (NoteGenerator) rg;
        this.note = generator.nextNote();
    }

    @Override
    public void applyMutation(int i, double d) {
        //DO NOTHING
    }

    @Override
    public int compareTo(Object t) {
        NoteGene other = (NoteGene) t;
        
        return (int) this.note.distance((Note) other.getAllele());
    }
    
}
