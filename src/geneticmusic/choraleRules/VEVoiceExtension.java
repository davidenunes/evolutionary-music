/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.choraleRules;

import geneticmusic.domain.Alteration;
import geneticmusic.domain.Note;
import geneticmusic.domain.Pitch;
import geneticmusic.fitness.AbstractCompositionRule;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 * Vertical Equilibrium Rule
 * 
 * each voice has 1.5 octaves of range aproximatly 
 * 
 * 
 * @author Davide Nunes
 */
public class VEVoiceExtension extends AbstractCompositionRule {
    //create reference notes

    private static final Note MIN_SOPRANO = new Note(Pitch.C, 4, Alteration.N, 4);
    private static final Note MAX_SOPRANO = new Note(Pitch.G, 5, Alteration.N, 4);
    private static final Note MIN_ALTO = new Note(Pitch.G, 3, Alteration.N, 4);
    private static final Note MAX_ALTO = new Note(Pitch.C, 5, Alteration.N, 4);
    private static final Note MIN_TENOR = new Note(Pitch.A, 3, Alteration.N, 4);
    private static final Note MAX_TENOR = new Note(Pitch.G, 4, Alteration.N, 4);
    private static final Note MIN_BASS = new Note(Pitch.D, 2, Alteration.N, 4);
    private static final Note MAX_BASS = MIN_SOPRANO;

    public VEVoiceExtension(double weight) {
        super(weight);
    }

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;


        //for each note within range, the note gets a score
        Gene[] genes = ic.getGenes();
        double totalChords = genes.length;


        for (int i = 0; i < genes.length; i++) {
            Note[] currentNotes = (Note[]) genes[i].getAllele(); //get the current chord
            if (withinRange(0, currentNotes[0])){
                result += 1 / (totalChords * 4.0);
            }
            if (withinRange(0, currentNotes[0])) {
                result += 1 / (totalChords * 4.0);
            }
            if (withinRange(0, currentNotes[0])) {
                result += 1 / (totalChords * 4.0);
            }
            if (withinRange(0, currentNotes[0])) {
                result += 1 / (totalChords * 4.0);
            }

        }
       
        
        return result;
    }
   

    

    

    private static boolean withinRange(int position, Note note) {
        boolean result = false;
        switch (position) {
            case 0:
                result = (note.distance(MIN_SOPRANO) <= 0 && //soprano witin range? 
                        note.distance(MAX_SOPRANO) >= 0);
                break;
            case 1:
                result = (note.distance(MIN_ALTO) <= 0 && //soprano witin range? 
                        note.distance(MAX_ALTO) >= 0);
                break;
            case 2:
                result = (note.distance(MIN_TENOR) <= 0 && //soprano witin range? 
                        note.distance(MAX_TENOR) >= 0);
                break;
            case 3:
                result = (note.distance(MIN_BASS) <= 0 && //soprano witin range? 
                        note.distance(MAX_BASS) >= 0);
                break;


        }
        return result;
    }
}
