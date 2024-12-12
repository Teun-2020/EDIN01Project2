import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 4;

        DeBruijnSequence sequence = new DeBruijnSequence();

        List<Integer> z5 = sequence.generateDeBruijn(5, n);
        List<Integer> z2 = sequence.generateDeBruijn(2, n);

        System.out.println(z5.size());
        System.out.println(z2.size());

        List<Integer> deBruijnZ10 = sequence.combineSequences(z2, z5);

        System.out.println(deBruijnZ10.size());

        try (FileWriter writer = new FileWriter("debruijn_z10.txt")) {
            for (Integer digit : deBruijnZ10) {
                writer.write(Integer.toString(digit));
            }
            System.out.println("De Bruijn sequence written to debruijn_z10.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}