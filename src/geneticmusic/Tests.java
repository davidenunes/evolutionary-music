/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic;

import geneticmusic.domain.Alteration;
import geneticmusic.domain.Note;
import geneticmusic.domain.Pitch;

/**
 *
 * @author davide
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Note n1 = new Note(Pitch.A, 5, Alteration.N, 4);
        Note n2 = new Note(Pitch.G, 5, Alteration.N, 4);
        
        System.out.println(n1.distance(n2));
    }
}
