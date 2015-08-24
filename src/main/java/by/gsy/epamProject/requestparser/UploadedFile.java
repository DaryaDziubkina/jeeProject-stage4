package by.gsy.epamProject.requestparser;

import java.util.List;

//загруженный файл
public class UploadedFile {
    private String filename;
    //содержимое файла
    private List<byte[]> content;

    public UploadedFile() {
        super();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<byte[]> getContent() {
        return content;
    }

    public void setContent(List<byte[]> content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return "".equals(filename);
    }
}
