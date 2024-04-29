package hkmu.comps380f.project.exception;

public class BookInfoNotFound extends Exception {
    public BookInfoNotFound(long id) {
        super("BookInfo " + id + " does not exist.");
    }
}

