package com.example.lap32;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class NoteMapper {

    static Gson gson = new Gson();

    // OOP -> Entity
    public static NoteEntity toEntity(Note note) {
        if (note instanceof TextNote) {
            // CORRECTED: Used the 'context' field inherited from Note, as there is no 'getTextContent()' method.
            return new NoteEntity(note.title, "text", null, note.context, note.createdDate);
        } else if (note instanceof CheckListNote) {
            // CORRECTED: Added a 'getItems()' method to ChecklistNote to access the items.
            String jsonItems = gson.toJson(((CheckListNote) note).getItems());
            return new NoteEntity(note.title, "checklist", jsonItems, null, note.createdDate);
        }
        return null;
    }

    // Entity -> OOP
    public static Note fromEntity(NoteEntity entity) {
        // This part requires hypothetical User data as the constructors for Note objects need a BaseUser.
        // Creating a dummy user for demonstration purposes.
        BaseUser dummyUser = new RegularUser("default", "default@user.com", "password");

        if (entity.type.equals("text")) {
            // CORRECTED: Matched the TextNote constructor. The 'owner' is a dummy user.
            return new TextNote(entity.title, entity.content, dummyUser);
        } else if (entity.type.equals("checklist")) {
            // CORRECTED: Matched the ChecklistNote constructor. The 'owner' is a dummy user.
            List<String> items = gson.fromJson(entity.checklistItemsJson, new TypeToken<List<String>>(){}.getType());
            // The context for a checklist note is hardcoded in ActivityAddNote, so we can use a default string here.
            return new CheckListNote(entity.title, "Checklist items:", items, dummyUser);
        }
        return null;
    }
}