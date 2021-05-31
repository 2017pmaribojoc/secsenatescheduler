package com.senate.transactions.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "listBucketFileMap")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ListBucketFileMap {

    @XmlElement(name="Contents")
    private List<Contents> contents;
}
