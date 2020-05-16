package mynotes.dyamoon.com.github.database;

public class NoteDbSchema {

    public static final class NOTE_TABLE{

        public static final String NAME = "notes";


        public static final class COLS{
            public static final String ID = "_id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DETAILS = "details";
        }

    }
}
