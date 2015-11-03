package com.mhurd.bitcoin.data;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryMerkleTreeTest {

    private class Container {

        private Integer data;

        public Container(Integer data) {
            assert(data != null);
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            assert(data != null);
            this.data = data;
        }

        public String hash() {
            return DigestUtils.sha256Hex(data.toString());
        }

    }

    private Container block1 = new Container(1);
    private Container block2 = new Container(2);
    private Container block3 = new Container(3);
    private Container block4 = new Container(4);
    private Container block5 = new Container(5);
    private Container block6 = new Container(6);
    private Container block7 = new Container(7);
    private Container block8 = new Container(8);

    private List<Container> data = new ArrayList<Container>();
    private Queue<String> dataHashes = new LinkedList<String>();

    @Before
    public void before() {
        // setup the data
        data.clear();
        data.add(block1);
        data.add(block2);
        data.add(block3);
        data.add(block4);
        data.add(block5);
        data.add(block6);
        data.add(block7);
        data.add(block8);
        // setup the hashes
        dataHashes.clear();
        dataHashes.add(block1.hash());
        dataHashes.add(block2.hash());
        dataHashes.add(block3.hash());
        dataHashes.add(block4.hash());
        dataHashes.add(block5.hash());
        dataHashes.add(block6.hash());
        dataHashes.add(block7.hash());
        dataHashes.add(block8.hash());
    }

    @Test
    public void test() {
        BinaryMerkleTree tree = new BinaryMerkleTree(dataHashes);
        System.out.println(tree);
    }

}
