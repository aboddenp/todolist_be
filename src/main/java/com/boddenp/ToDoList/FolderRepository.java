package com.boddenp.ToDoList;

import org.springframework.data.repository.CrudRepository;
import com.boddenp.ToDoList.ToDoItem;
import org.springframework.web.bind.annotation.*;
// Auto implemented by Spring into a Bean called itemRepository
public interface FolderRepository extends CrudRepository<Folder,Integer> {

}

