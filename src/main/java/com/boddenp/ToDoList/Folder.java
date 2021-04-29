package com.boddenp.ToDoList;

import javax.persistence.*;

@Entity
@Table(name = "folder")
public class Folder {
    private String  name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
