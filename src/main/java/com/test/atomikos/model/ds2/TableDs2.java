package com.test.atomikos.model.ds2;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jdimayuga on 30/08/2017.
 */
@Entity
@Table(name = "TABLE2")
public class TableDs2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
