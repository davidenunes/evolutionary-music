/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.jmusic.bridge;

import geneticmusic.genes.Note;
import geneticmusic.genes.Pitch;
import java.lang.reflect.Field;
import jm.JMC;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import org.jgap.IChromosome;

/**
 *
 * @author davide
 */
public class ConverterUtil implements JMC {

    private static final int CURRENT_INSTRUMENT = ACOUSTIC_GRAND;

    public static Phrase convert(IChromosome chrom) {
        Phrase result = new Phrase();


        //get pitches and duration
        int[] notes = new int[chrom.size()];
        double[] rithm = new double[chrom.size()];
        for (int i = 0; i < notes.length; i++) {
            Note currentNote = (Note) chrom.getGene(i).getAllele();
            notes[i] = getPitch(currentNote);
            rithm[i] = getRythm(currentNote);
        }
        result.addNoteList(notes, rithm);



        return result;
    }

    public static Score getScore(IChromosome chrm) {
        Phrase ph = convert(chrm);
        Part prt = new Part("instrument 1", VOICE, 1);
        prt.addPhrase(ph);

        Score result = new Score("converted score");
        result.addPart(prt);

        return result;

    }

    public static int getPitch(Note note) {
        try {
            String pitch = note.getPitch().toString();
            String octave = new Integer(note.getOctave()).toString();

            String target = null;

            if (note.getPitch().equals(Pitch.R)) {
                target = "REST";
            } else {
                target = pitch.concat(octave);
            }


            Class cClass = ConverterUtil.class;
            Field field = cClass.getField(target);
            Integer value = (Integer) field.get(null);

            return value.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getRythm(Note note) {
        double result = 0.0;

        switch (note.getDuration()) {
            case 1:
                result = SB;//semibreve
                break;
            case 2:
                result = M;//minima
                break;
            case 4:
                result = C;//seminima
                break;
            case 8:
                result = EN; //colcheia
                break;
            case 16:
                result = SN; //semi-colcheia
                break;
            case 32:
                result = TN;
                break;


        }


        return result;
    }

    
    /***
     * Converts a chromosome to a list of int 
     * maping to the Jmusic pitch constants
     * 
     * @param chrom
     * @return 
     */
    public static int[] getPitches(IChromosome chrom) {
        int[] notes = new int[chrom.size()];
        for (int i = 0; i < notes.length; i++) {
            Note currentNote = (Note) chrom.getGene(i).getAllele();
            notes[i] = getPitch(currentNote);
        }
        return notes;
    }
}
