/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.chronicle.wire.tests;

import java.nio.ByteBuffer;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.wire.BinaryWire;
import net.openhft.chronicle.wire.Wire;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gadei
 */
public class WireCollectionTest {

    private WireCollection data;

    public WireCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        data = new WireCollection("100", "@", "root", 1000, 0, "sdf34trsdf84sdfvewrsdvd4r345345345sdf");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {

        Bytes<ByteBuffer> bytes = Bytes.elasticByteBuffer();
        Wire wire = new BinaryWire(bytes);

//        wire2.writeDocument(false, w -> w.write(() -> "mydata")
//                .sequence(v -> Stream.of(data).forEach(v::object)));
//        System.out.println(Wires.fromSizePrefixedBlobs(bytes));
//
//        List<Data> dataList2 = new ArrayList<>();
//        assertTrue(wire.readDocument(null, w -> w.read(() -> "mydata")
//                .sequence(v -> {
//                    while (v.hasNextSequenceItem()) {
//                        dataList2.add(v.object(Data.class));
//                    }
//                })));
//        dataList2.forEach(System.out::println);
    }

}
