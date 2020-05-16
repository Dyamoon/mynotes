package mynotes.dyamoon.com.github.Model;

import java.util.UUID;

public class Note {
    private String mTitle;
    private String mDetails;
    private UUID mUUID;


    public Note() {
        this(UUID.randomUUID());
    }

    public Note(UUID uuid){
        mUUID = uuid;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }
}
