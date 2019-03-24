package io.unmock.core;


import java.util.Collection;

public final class Save {
    private final Collection<String> collection;
    private final Boolean bool;
    public enum Kind {BOOLEAN, COLLECTION}
    public Kind getKind() {
        return this.bool != null ? Kind.BOOLEAN : Kind.COLLECTION;
    }

    public Collection<String> collection() {
        return this.collection;
    }

    public Boolean bool() {
        return this.bool;
    }

    private Save(Collection<String> collection) {
        this.collection = collection;
        this.bool = null;
    }

    private Save(Boolean bool) {
        this.bool = bool;
        this.collection = null;
    }


    public static Save ofCollection(Collection<String> collection) {
        return new Save(collection);
    }

    public static Save ofBoolean(Boolean bool) {
        return new Save(bool);
    }
}
