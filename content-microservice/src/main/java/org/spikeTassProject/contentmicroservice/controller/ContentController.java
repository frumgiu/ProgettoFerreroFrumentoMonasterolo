package org.spikeTassProject.contentmicroservice.controller;

import org.spikeTassProject.contentmicroservice.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.models.Content;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/contents")
public class ContentController {
    @Autowired
    private ContentRepository contentRepository;

    @GetMapping(value = "/getAll")
    public List<Content> getAllContents() {
        System.out.println("Get all contents");
        return contentRepository.findAll();
    }

    @GetMapping(value = "/getAllFromUser/{email}")
    public List<Content> getAllContents(@PathVariable("email") String email) {
        System.out.println("Get all contents from a specific user");
        return contentRepository.findByOwneremail(email);
    }

    /* TODO: testare funzionamento con postman */
    @GetMapping(value = "/getFromName")
    public List<Content> getContentsByName(@RequestParam("title") String title) {
        System.out.println("Get all contents with a specific title");
        return contentRepository.findByTitle(title);
    }

    @PostMapping(value = "/createContent")
    public Content postContent(@RequestBody Content param) {
        System.out.println("Create a new content... " + param.toString());
        return contentRepository.save(
                new Content(param.getOwneremail(), param.getTitle(), param.getDescription()));
    }

    @DeleteMapping(value = "/deleteContent/{id}")
    public String deleteContent(@PathVariable("id") Long id) {
        System.out.println("Elimino contenuto: " + id);
        contentRepository.deleteById(id);
        return "done";
    }
}