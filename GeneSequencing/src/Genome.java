import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Genome {
    public static void main(String[] args) {
        // check if a command line argument is provided
        if (args.length < 1) {
            System.out.println("Please provide a file-path!");
            return;
        }

        // read "ecoli.txt" from common line argument
        String filePath = args[0];
        String ecoli = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ecoli = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading the file" + e.getMessage());
        }

        // finding genes
        int geneCounter = 0;
        int codonIndex = 0;

        while (codonIndex < ecoli.length()) {
            String gene = "";

            // finding initial codon
            if (ecoli.charAt(codonIndex) == 'a' && ecoli.charAt(codonIndex +1) == 't' && ecoli.charAt(codonIndex +2) == 'g') {
                // finding codon at the end
                int base1 = codonIndex +3;
                while (ecoli.charAt(base1) != 't'){
                    base1++;
                }
                // finding 'tag' and 'taa' codons
                if (ecoli.charAt(base1) == 't') {
                    int base2 = base1 + 1;
                    int base3 = base1 + 2;

                    if (ecoli.charAt(base2) == 'a') {
                        if (ecoli.charAt(base3) == 'a' || ecoli.charAt(base3) == 'g') {
                            geneCounter++;
                            // loop to add all the gene characters into string
                            // the condonIndex is now the first char of the new gene
                            // the base3 is last char of the gene
                            while (codonIndex <= base3) {
                                gene += ecoli.charAt(codonIndex);
                                codonIndex++;
                            }
                            System.out.println("Gene No. " + geneCounter + " : " + gene);
                        }
                    }
                    
                    //finding 'tga' codon
                    if (ecoli.charAt(base2) == 'g') {
                        if (ecoli.charAt(base3) == 'a') {
                            geneCounter++;
                            // loop to add all the gene characters into sting
                            // the condonIndex is now the first char of the new gene 
                            // the base3 is last char of the gene 
                            while (codonIndex <= base3) {
                                gene += ecoli.charAt(codonIndex);
                                codonIndex++;
                            }
                            System.out.println("Gene No. " + geneCounter + " : " + gene);
                        }
                    }
                }
            }
            codonIndex++;
        }
    }
}