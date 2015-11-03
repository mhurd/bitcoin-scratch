package com.mhurd.bitcoin.data;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Queue;

public class BinaryMerkleTree {

    public HashPointer getRoot() {
        return root;
    }

    public static class HashPointer {

        private String hash = "";
        private HashPointer leftChild, rightChild;

        public HashPointer getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(HashPointer leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(HashPointer rightChild) {
            this.rightChild = rightChild;
        }

        public HashPointer getRightChild() {
            return rightChild;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public void generateHash() {
            setHash(DigestUtils.sha256Hex((leftChild != null ? leftChild.getHash() : "")
                                              + (rightChild != null ? rightChild.getHash() : "")));
        }

        @Override
        public String toString() {
            return "HashPointer{" +
                "hash='" + hash + '\'' +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
        }

    }

    private final HashPointer root;

    public BinaryMerkleTree(Queue<String> dataHashes) {
        double height = Math.ceil(Math.log(dataHashes.size())/Math.log(2));
        int currentLevel = 1;
        root = new HashPointer();
        recurse(dataHashes, height, currentLevel, root);
    }

    private void recurse(Queue<String> dataHashes, double height, int currentLevel, HashPointer currentNode) {
        if (currentLevel == height) {
            // at the bottom level start consuming the hashes
            // go left
            HashPointer leftChild = new HashPointer();
            leftChild.setHash(dataHashes.remove());
            currentNode.setLeftChild(leftChild);
            // go right
            if (!dataHashes.isEmpty()) { // may not be an exact log2 match
                HashPointer rightChild = new HashPointer();
                rightChild.setHash(dataHashes.remove());
                currentNode.setRightChild(rightChild);
            }
        } else {
            // go left
            HashPointer leftChild = new HashPointer();
            currentNode.setLeftChild(leftChild);
            recurse(dataHashes, height, currentLevel + 1, leftChild);
            // go right
            HashPointer rightChild = new HashPointer();
            currentNode.setRightChild(rightChild);
            recurse(dataHashes, height, currentLevel + 1, rightChild);
        }
        currentNode.generateHash();
    }

    @Override
    public String toString() {
        return "BinaryMerkleTree{" +
            "root=" + root +
            '}';
    }

}
