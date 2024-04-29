package hkmu.comps380f.project.exception;

public class EmptyContentException extends Exception {
    public EmptyContentException() {
        super("Content does not exist.");
    }
}
