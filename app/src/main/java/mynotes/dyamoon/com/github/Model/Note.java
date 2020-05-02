package mynotes.dyamoon.com.github.Model;

import java.util.UUID;

public class Note {
    private String mTitle;
    private String mDetails;
    private UUID mId;


    public Note() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
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
