package it.unisa.razzolo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Background implements Runnable{
    private final char[][] matrix;
    private Set<String> foundWords;

    public Background(char[][] matrix){
        this.matrix = matrix;
    }

    public Set<String> getFoundWords() {
        return foundWords;
    }

    @Override
    public void run() {
        this.foundWords = new TreeSet<>(compareStringByLength.thenComparing(String::compareTo));
        final var wordList = new HashSet<>(Arrays.asList("GLEBA","AFELIO","FELPI","FELPO","ABCD","POLI","LEI","FLIP","FLOP","ABZ","AAA","ABA","LOLI"));
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                for(String s : wordList)
                    if(matrix[i][j] == s.charAt(0) && Algorithms.bfs(matrix, i, j, s))
                        this.foundWords.add(s);
    }

    Comparator<String> compareStringByLength = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    };
}
