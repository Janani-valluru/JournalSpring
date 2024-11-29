package com.journal.journal.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.journal.journal.entity.JournalEntry;
import com.journal.journal.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.journal.journal.entity.JounalEntry;

@RestController
@RequestMapping("/journal")
public class   JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;



    @GetMapping
    public ResponseEntity<?> getAll() {
       List<JournalEntry> all = journalEntryService.getAll();
       if(all != null &&!all.isEmpty()){
           return  new ResponseEntity<>(all,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) { // SPRING TAKE THE DATA FROM THE REQUEST AND TURN IT
        try{
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    }catch (Exception e) {
            return new ResponseEntity<>(myEntry , HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("id/{myId}")

    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return  new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deletJounalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}


    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJounalEntry(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() !=null && !newEntry.equals("")?newEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old , HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

