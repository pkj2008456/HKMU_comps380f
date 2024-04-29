package hkmu.comps380f.project.exception;

public class BookCommentNotFound extends Exception{
    public BookCommentNotFound(long id) {
        super("BookInfo " + id + " does not exist.");
    }
}
