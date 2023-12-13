package student;

import provided.BinarySequence;

public class HuffmanCodeTree {

    /**
     * Implemented to store huffman nodes as a tree like structure.
     * root has to be given on initialization or should be initialized using a huffman codebook.
     * used to decode binary represented strings.
     */
    private HuffmanNode root;

    //creating the root node
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    //creating a Huffman code tree using a given huffman code book
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        this.root = new HuffmanNode(null, null);
        for (char character : codebook) {
            BinarySequence sequence = codebook.getSequence(character);
            put(sequence, character);
        }
    }

    //checks if this is a valid tree
    public boolean isValid() {
        return root.isValidTree();
    }

    //used to put new letters to the huffman code tree
    public void put(BinarySequence seq, char letter) {

        HuffmanNode node = root;

        //going through the bit sequence provided
        for (boolean bit: seq) {
            if (bit) { //if the bit is '1'
                if (node.getOne() == null) {
                    //if there is no right nodes for this node create a new node
                    node.setOne(new HuffmanNode(null, null));
                }
                //if there is right node, access that node
                node = node.getOne();
            } else { //if the bit is '0'
                if (node.getZero() == null) {
                    //if there is no left node for this node creates a new node
                    node.setZero(new HuffmanNode(null, null));
                }
                //if there is left node, access that node.
                node = node.getZero();
            }
        }
        //when reached the last node according to the binary sequence. put the letter at that node
        node.setData(letter);
    }

    //retrieving the string represented by a binary sequence
    public String decode(BinarySequence s) {

        StringBuilder decodedString = new StringBuilder();
        HuffmanNode node = root;

        //going through the bit sequence
        for (boolean bit: s) {
            if (bit) { //if bit is '1'
                node = node.getOne(); //go to right node
            } else { //if bit is '0'
                node = node.getZero(); // go to left node
            }

            //if the current node is a leaf node retrieve the data stored in the node
            if (node.isLeaf()) {
                decodedString.append(node.getData());
                node = root;
            }
        }
        return decodedString.toString();
    }


}
