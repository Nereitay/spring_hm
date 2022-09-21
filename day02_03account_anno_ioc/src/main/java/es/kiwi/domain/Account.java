package es.kiwi.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class Account implements Serializable {

    private int id;
    private String name;
    private Float money;
}
