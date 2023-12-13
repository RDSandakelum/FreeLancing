package student;



public class HuffmanNode {
    /**
     * Implemented to represent a node in huffman tree.
     * Has a data if a leaf node, if not only a left and right branches.
     * used in huffman code tree class
     * Represents the right branch of the node.
     */
    private HuffmanNode one;
    /**
     * Represents the left branch of the node.
     */
    private HuffmanNode zero;
    /**
     * Represents the character stored in the node.
     */
    private Character data;

    //Creates a internal node
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.one = one;
        this.zero = zero;
        this.data = null;
    }

    //creates a leaf node
    public HuffmanNode(char data) {
        this.one = null;
        this.zero = null;
        this.data = data;
    }

    //gets the right node of the current node
    public HuffmanNode getOne() {
        return one;
    }

    //set the right node of the current node
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    //get the left node of the current node
    public HuffmanNode getZero() {
        return zero;
    }

    //set the left node of the current node
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    //gets the data(character) contains in the current node
    public Character getData() {
        return data;
    }

    //set the data in the current node
    public void setData(Character c) {
        this.data = c;
    }

    //checks if the current node is a leaf node
    public boolean isLeaf() {
        return one == null && zero == null;
    }

    //checks whether the current node is a valid node
    public boolean isValidNode() {

        if (isLeaf() && data != null) {
            return true;
        } else {
            if (data == null && zero != null && one != null) {
                return true;
            }
        }

        return false;
    }

    //checks if the current node and its descendant nodes are all valid nodes and forms a valid tree
    public boolean isValidTree() {
        //checks if the current node is a valid node
        if (!isValidNode()) {
            return false;
        }

        //going through the tree using recursion
        boolean validLeft = zero == null || zero.isValidTree(); //if there is a left node checks if left is valid
        boolean validRight = one == null || one.isValidTree(); // if there is a right node checks if right is valid

        boolean validTree = validLeft && validRight;

        return validTree;
    }
}
