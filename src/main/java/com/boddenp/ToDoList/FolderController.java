package com.boddenp.ToDoList;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@CrossOrigin(origins = "*")
public class FolderController {

    // access to database
    @Autowired
    private FolderRepository folderRepository;

    @PostMapping(path = "/folders")
    public @ResponseBody Integer addFolder(@RequestBody Folder incompleteFolder) {
        Folder newFolder = new Folder();
        newFolder.setName(incompleteFolder.getName());
        folderRepository.save(newFolder);
        return newFolder.getId();
    }


    @DeleteMapping(path = "/folders/{id}")
    public @ResponseBody String deleteFolder(@PathVariable(value = "id") Integer folderId){
        Optional<Folder> susFolder = folderRepository.findById(folderId);
        if(susFolder.isPresent()){
            // modify the description
            Folder folder= susFolder.get();
            folderRepository.delete(folder);
            return "folder deleted ";
        }
        else{
            return "folder does not exist";
        }
    }

    @GetMapping(path="/folders")
    public @ResponseBody Iterable<Folder> getAllFolders() {
        // This returns a JSON or XML with the users
        return folderRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/folders/{id}")
    public @ResponseBody Folder getFolder(@PathVariable(value = "id") Integer id ) throws NotFoundException {
        // This returns a JSON or XML with the users
        Optional<Folder> folder = folderRepository.findById(id) ;
        if(folder.isPresent()){
            return folder.get();
        }else{
            throw new NotFoundException("folder not found");
        }


    }
}
