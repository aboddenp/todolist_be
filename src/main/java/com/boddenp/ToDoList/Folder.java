package com.boddenp.ToDoList;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "folder")
public class Folder {
    private String  name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy="folder" ,cascade = CascadeType.REMOVE)
    private Set<ToDoItem> items;

    public Set<ToDoItem> getItems() {
        return items;
    }

    public void setItems(Set<ToDoItem> items) {
        this.items = items;
    }


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
