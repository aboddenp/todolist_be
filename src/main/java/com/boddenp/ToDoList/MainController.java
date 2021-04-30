package com.boddenp.ToDoList;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@CrossOrigin(origins = "*")
public class MainController {

    // access to database
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private FolderRepository folderRepository;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/todos/{id}")
    public @ResponseBody Integer addItem(@RequestBody ToDoItem incompleteItem, @PathVariable(value= "id")  Integer folderid ) throws NotFoundException {
        ToDoItem newItem = new ToDoItem();
        newItem.setDescription(incompleteItem.getDescription());
        newItem.setDone(false);
        Optional<Folder> folder = folderRepository.findById(folderid);
        if(folder.isPresent()){
            newItem.setFolder(folder.get());
        }else{
            throw new NotFoundException("folder does not exist");
        }
        itemRepository.save(newItem);
        return newItem.getId();
    }


    @PutMapping(path = "/todos/{id}")
    public @ResponseBody String editItem(@RequestBody ToDoItem repItem,@PathVariable("id") Integer id) {
            Optional<ToDoItem> susItem = itemRepository.findById(id);
            if(susItem.isPresent()){
                // modify the description
                ToDoItem item = susItem.get();
                repItem.setFolder(item.getFolder());
                itemRepository.delete(item);
                itemRepository.save(repItem);
                return "saved";
            }
            else{
                return "Item does not exist";
            }
    }

    @DeleteMapping(path = "/todos/{id}")
    public @ResponseBody String deleteItem(@PathVariable(value = "id") Integer todoId){
        Optional<ToDoItem> susItem = itemRepository.findById(todoId);
        if(susItem.isPresent()){
            // modify the description
            ToDoItem item = susItem.get();
            itemRepository.delete(item);
            return "item deleted ";
        }
        else{
            return "Item does not exist";
        }
    }

//    @PatchMapping(path = "/todos")
//    public @ResponseBody String toggleItem(@RequestParam Integer id) {
//        Optional<ToDoItem> susItem = itemRepository.findById(id);
//        if(susItem.isPresent()){
//            // modify the description
//            ToDoItem item = susItem.get();
//            item.setDone(!item.isDone());
//            itemRepository.save(item);
//            return "item done toggled";
//        }
//        else{
//            return "Item does not exist";
//        }
//    }

    @GetMapping(path="/todos/{id}")
    public @ResponseBody Iterable<ToDoItem> getFolderTodos(@PathVariable(value = "id") Integer folderId) throws NotFoundException {
        // This returns a JSON or XML with the users
        Optional<Folder> folder = folderRepository.findById(folderId) ;
        if(folder.isPresent()){
            return folder.get().getItems();
        }else{
            throw new NotFoundException("folder not found");
        }
    }

}
