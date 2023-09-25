package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Library;
import com.example.demo.LibraryRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    @GetMapping("/libraries")
    public ResponseEntity<List<Library>> getAllLibraries(@RequestParam(required = false) String title) {
        try {
            List<Library> libraries = new ArrayList<Library>();

            if (title == null)
                libraries.addAll(libraryRepository.findAll());
            else
                libraries.addAll(libraryRepository.findByTitleContaining(title));

            if (libraries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(libraries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/libraries/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable("id") long id) {
        Library library = libraryRepository.findById(id);

        if (library != null) {
            return new ResponseEntity<>(library, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/libraries")
    public ResponseEntity<String> createLibrary(@RequestBody Library library) {
        try {
            libraryRepository.save(new Library(library.getTitle(), library.getDescription(), false));
            return new ResponseEntity<>("Library was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/libraries/{id}")
    public ResponseEntity<String> updateLibrary(@PathVariable("id") long id, @RequestBody Library library) {
        Library _library = libraryRepository.findById(id);

        if (_library != null) {
            _library.setId(id);
            _library.setTitle(library.getTitle());
            _library.setDescription(library.getDescription());
            _library.setPublished(library.isPublished());

            libraryRepository.update(_library);
            return new ResponseEntity<>("Library was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Library with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/libraries/{id}")
    public ResponseEntity<String> deleteibrary(@PathVariable("id") long id) {
        try {
            int result = libraryRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Library with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Library was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete library.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/libraries")
    public ResponseEntity<String> deleteAllLibraries() {
        try {
            int numRows = libraryRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Library(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete libraries.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/libraries/published")
    public ResponseEntity<List<Library>> findByPublished() {
        try {
            List<Library> libraries = libraryRepository.findByPublished(true);

            if (libraries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}