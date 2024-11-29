
package com.journal.journal.repository;

import com.journal.journal.entity.JournalEntry; // Ensure this is the correct import
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    // Additional custom query methods can be defined here if needed
}
