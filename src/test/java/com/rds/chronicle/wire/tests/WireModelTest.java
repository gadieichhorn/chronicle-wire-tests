/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.chronicle.wire.tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.wire.BinaryWire;
import net.openhft.chronicle.wire.Wire;
import net.openhft.chronicle.wire.WireType;
import net.openhft.chronicle.wire.Wires;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gadei
 */
@RunWith(value = Parameterized.class)
public class WireModelTest {

    private static final Logger logger = LoggerFactory.getLogger(WireModelTest.class);
    private WireModel model1;// = new WireModel();

    private final Function<Bytes, Wire> wireType;

    public WireModelTest(Function<Bytes, Wire> wireType) {
        this.wireType = wireType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> combinations() {
        return Arrays.asList(
                new Object[]{(Function<Bytes, Wire>) bytes -> new BinaryWire(bytes, false, true, false, 128, "binary")},
                new Object[]{WireType.TEXT},
                new Object[]{WireType.BINARY},
                new Object[]{WireType.FIELDLESS_BINARY}
//                new Object[]{WireType.JSON}
        );
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        model1 = new WireModel();
        model1.setId(312313424);
        model1.setRevision(12);
        model1.setKey(UUID.randomUUID().toString());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readMarshallable method, of class WireModel.
     */
    @Test
    public void testMultipleReads() {
        logger.info("Type: {}", this.wireType);
                
        Bytes bytes = Bytes.elasticByteBuffer();
        Wire wire = wireType.apply(bytes);

        wire.writeDocument(true, model1);
        System.out.println(Wires.fromSizePrefixedBlobs(bytes));

        WireModel results1 = new WireModel();
        wire.readDocument(results1, null);

        assertEquals(model1.getId(), results1.getId());
        assertEquals(model1.getRevision(), results1.getRevision());
        assertEquals(model1.getKey(), results1.getKey());
    }

}
