/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic;

import geneticmusic.domain.Alteration;
import geneticmusic.domain.Note;
import geneticmusic.domain.Pitch;
import geneticmusic.jmusic.bridge.ConverterUtil;
import geneticmusic.jmusic.bridge.HarmonicUtils;
import geneticmusic.rules.RithmContinuity;
import javax.xml.datatype.Duration;
import jm.JMC;
import jm.constants.Durations;
import jm.constants.Scales;
import jm.music.data.Tempo;
import jm.music.tools.PhraseAnalysis;
import jm.util.Write;
import org.jgap.Chromosome;
import org.jgap.ChromosomeForTesting;

/**
 *
 * @author davide
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Note n1 = new Note(Pitch.C, 3, Alteration.N, 4);
        
        
        Note c1 = new Note(Pitch.B, 4, Alteration.S, 4);
        Note c2 = new Note(Pitch.D, 3, Alteration.F, 4);
        Note c3 = new Note(Pitch.C, 3, Alteration.N, 4);
        Note c4 = new Note(Pitch.F, 2, Alteration.N, 4);
      
        Note[] chord = new Note[]{c1,c2,c3,c4};
        
        
        
        jm.music.data.Note note1 = new jm.music.data.Note(ConverterUtil.getPitch(n1), Durations.CROTCHET);
        
        
        
        int[][] chords = HarmonicUtils.possibleChords(ConverterUtil.getPitch(n1), Scales.MAJOR_SCALE);
        
        System.out.println(HarmonicUtils.isValidChord(chord, Scales.MAJOR_SCALE, ConverterUtil.getNormalizedPitch(n1)));
           
        
        Write.midi(ConverterUtil.getChordScore(ConverterUtil.concertChord(chord)), "test_chord.mid");
    }
}
