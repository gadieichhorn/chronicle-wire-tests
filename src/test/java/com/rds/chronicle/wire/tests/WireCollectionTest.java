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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gadei
 */
@RunWith(value = Parameterized.class)
public class WireCollectionTest {

    private static final Logger logger = LoggerFactory.getLogger(WirePropertyTest.class);
    private WireCollection collection;// = new WireModel();

    private final Function<Bytes, Wire> wireType;

    public WireCollectionTest(Function<Bytes, Wire> wireType) {
        this.wireType = wireType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> combinations() {
        return Arrays.asList(
                new Object[]{(Function<Bytes, Wire>) bytes -> new BinaryWire(bytes, false, true, false, 128, "binary")},
                new Object[]{WireType.TEXT},
                new Object[]{WireType.BINARY},
                new Object[]{WireType.FIELDLESS_BINARY}
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
        collection = new WireCollection("reference", "@", "name", 234234234, 23, UUID.randomUUID().toString());
        WireProperty property = new WireProperty("reference", "path", "name", "value", 5345, 32432, UUID.randomUUID().toString());
        collection.addPropertie(property);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        logger.info("Type: {}", this.wireType);
                
        Bytes bytes = Bytes.elasticByteBuffer();
        Wire wire = wireType.apply(bytes);

        wire.writeDocument(true, collection);
        System.out.println(Wires.fromSizePrefixedBlobs(bytes));

        WireCollection results = new WireCollection();
        wire.readDocument(results, null);

        assertEquals(collection.getId(), results.getId());
        assertEquals(collection.getRevision(), results.getRevision());
        assertEquals(collection.getKey(), results.getKey());
        assertEquals(collection.getName(), results.getName());
        assertEquals(collection.getPath(), results.getPath());
        assertEquals(collection.getReference(), results.getReference());
    }

}
