/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.chronicle.wire.tests;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.wire.BinaryWire;
import net.openhft.chronicle.wire.Wire;
import net.openhft.chronicle.wire.Wires;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gadei
 */
public class BinaryToTextTest {
    
    static private final Logger logger = LoggerFactory.getLogger(BinaryToTextTest.class);

    public BinaryToTextTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
         Bytes tbytes = Bytes.elasticByteBuffer();
        Wire tw = new BinaryWire(tbytes);
        tw.writeDocument(false, w->w.write(() -> "key").text("hello"));
        logger.debug(Wires.fromSizePrefixedBlobs(tbytes));
    }
    
}
