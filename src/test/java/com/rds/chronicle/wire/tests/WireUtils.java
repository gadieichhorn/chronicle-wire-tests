/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.chronicle.wire.tests;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author gadei
 */
public class WireUtils {

    public static void compareWireModel(WireModel a, WireModel b) {
        assertEquals(a.getId(), b.getId());
        assertEquals(a.getRevision(), b.getRevision());
        assertEquals(a.getKey(), b.getKey());
    }

    public static void compareWireProperty(WireProperty a, WireProperty b) {
        compareWireModel(a, b);
        assertEquals(a.getReference(), b.getReference());
        assertEquals(a.getValue(), b.getValue());
        assertEquals(a.getName(), b.getName());
        assertEquals(a.getPath(), b.getPath());
    }

    public static void compareWireCollection(WireCollection a, WireCollection b) {
        compareWireModel(a, b);
        assertEquals(a.getReference(), b.getReference());
        assertEquals(a.getName(), b.getName());
        assertEquals(a.getPath(), b.getPath());

        a.getProperties().forEach(p -> {
//            need to change for a MAP to compare
//            compareWireProperty(p, b.getProperties().get(c.getReference()));
        });

        a.getCollections().values().forEach(c -> {
            if (b.getCollections().containsKey(c.getReference())) {
                compareWireCollection(c, b.getCollections().get(c.getReference()));
            } else {
                Assert.fail("Cannot match collection child element WireCollection");
            }
        });
    }

}
