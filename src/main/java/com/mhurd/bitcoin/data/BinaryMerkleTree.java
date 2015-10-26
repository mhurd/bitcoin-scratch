package com.mhurd.bitcoin.data;

import org.apache.commons.codec.digest.DigestUtils;

public class BinaryMerkleTree<T extends Comparable<T>> {

    public static class HashPointer<T extends Comparable<T>> {

        private String hash;
        private final T data;
        private HashPointer<T> leftChild, rightChild;

        public HashPointer(T data) {
            this.data = data;
            this.hash = recalculateHash();
        }

        public void addChild(T data) {
            assert data != null;
            if (this.data.compareTo(data) > 0) {
                if (leftChild == null) {
                    leftChild = new HashPointer<T>(data);
                } else {
                    leftChild.addChild(data);
                }
            } else {
                if (rightChild == null) {
                    rightChild = new HashPointer<T>(data);
                } else {
                    rightChild.addChild(data);
                }
            }
           this.hash = recalculateHash();
        }

        private String recalculateHash() {
            return DigestUtils.sha256Hex(toString());
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            b.append(data);
            if (leftChild != null) {
                b.append(leftChild.hash);
            }
            if (rightChild != null) {
                b.append(rightChild.hash);
            }
            return b.toString();
        }

    }

    private HashPointer<T> root;

    public void addChild(T data) {
        assert data != null;
        if (root == null) {
            root = new HashPointer<T>(data);
        } else {
            root.addChild(data);
        }
    }

}
