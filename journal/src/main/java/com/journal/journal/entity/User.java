package com.journal.journal.entity;
import lombok.Data;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)  // it wont create automatically go to application properties
    @NonNull
    private String UserName;

    @NonNull
    private String password;

    @DBRef
    //creacte reference of user collection of journalEntries of journal entries from JournalEntry file(@Document(collection = "journal_entries") // )
    //
    //{
     //   _id: ObjectId("65197b3ea33ac23e14a716ef"),
    //}
    //userName: 'ram',
    //password: '$2a$10$0RJY6d7of 1DdK/NPBmG4Y.xCS@aQuvm. 5H4N6. JLLXE0iazBl3HPC',
    // journalEntries: [
    //DBRef("journal_entries",
      //    ObjectId("65197b6ba33ac23e14a716f1")),
    //ObjectId("65197b71a33ac23e14a716f2"))
    //]
    //_class: 'net.engineeringdigest.journalApp.entity.User'
    //journalEntries have reference of journal_entries
    private List<JournalEntry> journalEntries = new ArrayList<>();


}
