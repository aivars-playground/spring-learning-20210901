package com.example.datamongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "aircraft")
public class Aircraft {
    @Id
    private String id;
    private String model;

    @Field("tail_nr")
    @Indexed(useGeneratedName = true)
    private String tailNr;

    @Transient
    private String invisible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTailNr() {
        return tailNr;
    }

    public void setTailNr(String tailNr) {
        this.tailNr = tailNr;
    }

    public String getInvisible() {
        return invisible;
    }

    public void setInvisible(String invisible) {
        this.invisible = invisible;
    }
}

