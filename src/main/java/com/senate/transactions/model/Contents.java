package com.senate.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Contents")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Contents {

    @JsonProperty("Key")
    private String key;
}
