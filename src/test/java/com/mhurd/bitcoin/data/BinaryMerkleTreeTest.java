package com.mhurd.bitcoin.data;

import org.junit.Test;

public class BinaryMerkleTreeTest {

    @Test
    public void testTree() {
        BinaryMerkleTree<Integer> tree = new BinaryMerkleTree<Integer>();
        tree.addChild(5);
        tree.addChild(9);
        tree.addChild(3);
        tree.addChild(1);
        tree.addChild(11);
        tree.addChild(-5);
    }

}
