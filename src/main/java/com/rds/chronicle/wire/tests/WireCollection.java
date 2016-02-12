package com.rds.chronicle.wire.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.openhft.chronicle.core.annotation.NotNull;
import net.openhft.chronicle.wire.Marshallable;
import net.openhft.chronicle.wire.WireIn;
import net.openhft.chronicle.wire.WireKey;
import net.openhft.chronicle.wire.WireOut;

/**
 *
 * @author gadei
 */
public class WireCollection extends WireModel implements Marshallable {

    enum Values implements WireKey {
        REFERENCE, PATH, NAME, PROPERTIES, COLLECTIONS
    };

    private String reference;
    private String path;
    private String name;
    private List<WireProperty> properties = new ArrayList<>();
    private Map<String, WireCollection> collections = new HashMap<>();

    public WireCollection() {
    }

    public WireCollection(String reference, String path, String name, long id, int revision, String key) {
        super(id, revision, key);
        this.reference = reference;
        this.path = path;
        this.name = name;
    }

    @Override
    public void readMarshallable(@NotNull WireIn wire) throws IllegalStateException {
        super.readMarshallable(wire);
        this.reference = wire.read(WireCollection.Values.REFERENCE).text();
        this.path = wire.read(WireCollection.Values.PATH).text();
        this.name = wire.read(WireCollection.Values.NAME).text();
        //this.properties = wire.read(WireCollection.Values.PROPERTIES).;
    }

    @Override

    public void writeMarshallable(WireOut wire) {
        super.writeMarshallable(wire);
        wire
                .write(WireCollection.Values.REFERENCE).text(reference)
                .write(WireCollection.Values.PATH).text(path)
                .write(WireCollection.Values.NAME).text(name);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clearProperties() {
        properties.clear();
    }

    public List<WireProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<WireProperty> properties) {
        this.properties = properties;
    }

    public void addPropertie(WireProperty property) {
        this.properties.add(property);
    }

    public void setCollections(Map<String, WireCollection> collections) {
        this.collections = collections;
    }

    public Map<String, WireCollection> getCollections() {
        return collections;
    }

    public void addCollection(WireCollection collection) {
        this.collections.put(collection.getReference(), collection);
    }

}
