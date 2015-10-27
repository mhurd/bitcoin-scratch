package com.mhurd.bitcoin.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryMerkleTreeTest {

    private final BinaryMerkleTree<Integer> tree = new BinaryMerkleTree<Integer>();

    public BinaryMerkleTreeTest() {
        tree.addChild(5);
        tree.addChild(9);
        tree.addChild(3);
        tree.addChild(1);
        tree.addChild(11);
        tree.addChild(-5);
    }

    @Test
    public void testTreeSorting() {
        assertEquals(Integer.valueOf(5), tree.getRoot().getData());
        assertEquals(Integer.valueOf(3), tree.getRoot().getLeftChild().getData());
        assertEquals(Integer.valueOf(9), tree.getRoot().getRightChild().getData());
        assertEquals(Integer.valueOf(1), tree.getRoot().getLeftChild().getLeftChild().getData());
        assertEquals(Integer.valueOf(11), tree.getRoot().getRightChild().getRightChild().getData());
        assertEquals(Integer.valueOf(-5), tree.getRoot().getLeftChild().getLeftChild().getLeftChild().getData());
    }

}
