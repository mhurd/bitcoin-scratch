package com.mhurd.bitcoin.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinaryMerkleTreeTest {

    private final BinaryMerkleTree<Holder<Integer>> tree = new BinaryMerkleTree<Holder<Integer>>();
    private Holder<Integer> five;
    private Holder<Integer> nine;
    private Holder<Integer> three;
    private Holder<Integer> one;
    private Holder<Integer> eleven;
    private Holder<Integer> minusFive;

    private static class Holder<T extends Comparable<T>> implements Comparable<Holder<T>> {

        private T value;

        public Holder(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int compareTo(Holder<T> obj) {
            return value.compareTo(obj.getValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Holder<?> holder = (Holder<?>) o;

            return !(value != null ? !value.equals(holder.value) : holder.value != null);

        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    @Before
    public void setUp() {
        tree.clear();
        five = new Holder<Integer>(5);
        nine = new Holder<Integer>(9);
        three = new Holder<Integer>(3);
        one = new Holder<Integer>(1);
        eleven = new Holder<Integer>(11);
        minusFive = new Holder<Integer>(-5);
        tree.addChild(five);
        tree.addChild(nine);
        tree.addChild(three);
        tree.addChild(one);
        tree.addChild(eleven);
        tree.addChild(minusFive);
    }

    @Test
    public void testTreeSorting() {
        assertEquals(new Holder<Integer>(Integer.valueOf(5)), tree.getRoot().getData());
        assertEquals(new Holder<Integer>(Integer.valueOf(3)), tree.getRoot().getLeftChild().getData());
        assertEquals(new Holder<Integer>(Integer.valueOf(9)), tree.getRoot().getRightChild().getData());
        assertEquals(new Holder<Integer>(Integer.valueOf(1)), tree.getRoot().getLeftChild().getLeftChild().getData());
        assertEquals(new Holder<Integer>(Integer.valueOf(11)), tree.getRoot().getRightChild().getRightChild().getData());
        assertEquals(new Holder<Integer>(Integer.valueOf(-5)), tree.getRoot().getLeftChild().getLeftChild().getLeftChild().getData());
    }

    @Test
    public void testTampering() {
        assertTrue(tree.verify());
        nine.setValue(99);
        assertFalse(tree.verify());
    }

}
