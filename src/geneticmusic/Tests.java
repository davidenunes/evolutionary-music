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
        Note n2 = new Note(Pitch.E, 8, Alteration.N, 4);
        
        jm.music.data.Note note1 = new jm.music.data.Note(ConverterUtil.getPitch(n1), Durations.CROTCHET);
        jm.music.data.Note note2 = new jm.music.data.Note(ConverterUtil.getPitch(n2), Durations.CROTCHET);
        
        
        int[][] chords = HarmonicUtils.possibleChords(ConverterUtil.getPitch(n1), Scales.MAJOR_SCALE);
        
        System.out.println(HarmonicUtils.belongsToChord(n2, chords[0]));
           
    }
}
