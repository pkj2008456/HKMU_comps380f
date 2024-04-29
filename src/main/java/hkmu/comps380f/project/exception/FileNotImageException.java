package hkmu.comps380f.project.exception;

public class FileNotImageException extends Exception {
    public FileNotImageException(String filename, String mimeContentType) {
        super("File : " + filename + " | MimeContentType : " + mimeContentType + " is not image.");
    }
}

