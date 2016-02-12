package com.rds.chronicle.wire.tests;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gadei
 */
public class WireCollection extends WireModel {

    private String reference;
    private String path;
    private String name;
    private Map<String, WireProperty> properties = new HashMap<>();
    private Map<String, WireCollection> collections = new HashMap<>();

    public WireCollection(String reference, String path, String name, long id, int revision, String key) {
        super(id, revision, key);
        this.reference = reference;
        this.path = path;
        this.name = name;
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

    public Map<String, WireProperty> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, WireProperty> properties) {
        this.properties = properties;
    }

    public void addPropertie(WireProperty property) {
        this.properties.put(property.getReference(), property);
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
