/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.chronicle.wire.tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;
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
        Random rand = new Random();

        collection = new WireCollection("reference", "@", "name", 234234234, 23, UUID.randomUUID().toString());

        Stream.of(1, 10).forEach((i) -> {
            collection.addProperty(new WireProperty("reference" + i, "@:" + i, "name" + i, UUID.randomUUID().toString().replace("-", ""), rand.nextLong(), rand.nextInt(), UUID.randomUUID().toString()));
        });

        Stream.of(1, 3).forEach((i) -> {
            WireCollection c = new WireCollection("reference" + i, "@:" + i, "name" + i, rand.nextLong(), rand.nextInt(), UUID.randomUUID().toString());
            collection.addCollection(c);
            Stream.of(1, 3).forEach((k) -> {
                collection.addProperty(new WireProperty("reference" + k, "@:" + i + "-" + k, "name" + k, UUID.randomUUID().toString().replace("-", ""), rand.nextLong(), rand.nextInt(), UUID.randomUUID().toString()));
            });
        });
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
        logger.info(Wires.fromSizePrefixedBlobs(bytes));

        WireCollection results = new WireCollection();
        wire.readDocument(results, null);

        WireUtils.compareWireCollection(collection, results);
    }

}
