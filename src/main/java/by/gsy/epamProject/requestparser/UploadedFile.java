package by.gsy.epamProject.requestparser;

import java.util.List;

//����������� ����
public class UploadedFile {
    private String filename;
    //���������� �����
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
