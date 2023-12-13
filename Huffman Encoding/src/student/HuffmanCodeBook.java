package student;

import provided.BinarySequence;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HuffmanCodeBook implements Iterable<Character> {
    /**
     * Implements a huffman code book to store binary sequences of characters.
     * Can put characters and there binary sequences one by one.
     * Used to encode strings.
     * Maximum size assumption.
     */
    private static final int MAX_SIZE = 256;
    /**
     * Stores the size of the codebook.
     */
    private int size;
    /**
     * Stores the characters in the order of the insertion.
     */
    private char[] characters;
    /**
     * Stores the binary sequences of the characters in the order of the insertion.
     */
    private BinarySequence[] bitSequences;

    //create an empty Huffman code book
    public HuffmanCodeBook() {
        this.bitSequences = new BinarySequence[MAX_SIZE];
        this.characters = new char[MAX_SIZE];
        this.size = 0;
    }

    //adding a character and relevant binary sequence of the character to the code book
    public void addSequence(char c, BinarySequence seq) {
        if (!contains(c)) {
            characters[size] = c;
            bitSequences[size] = seq;
            size++;
        }
    }

    //checking whether a letter provided is in the code book
    public boolean contains(char letter) {
        for (int i = 0; i < size; i++) {
            if (characters[i] == letter) {
                return true;
            }
        }
        return false;
    }

    //when a string is provided, checks if all the characters in the string are in the code book
    public boolean containsAll(String letters) {
        for (char letter : letters.toCharArray()) {
            if (!contains(letter)) {
                return false;
            }
        }
        return true;
    }

    //gets the binary sequence of a given character
    public BinarySequence getSequence(char c) {
        for (int i = 0; i < size; i++) {
            if (characters[i] == c) {
                return bitSequences[i];
            }
        }
        return null;
    }

    //encodes a string to the corresponding binary sequence
    public BinarySequence encode(String s) {
        StringBuilder encodedString = new StringBuilder();
        for (char c : s.toCharArray()) {
            BinarySequence sequence = getSequence(c);
            encodedString.append(sequence);
        }

        return new BinarySequence(encodedString.toString());
    }

    // method to iterate through the code book
    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Character next() {
                if (hasNext()) {
                    return characters[currentIndex++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
