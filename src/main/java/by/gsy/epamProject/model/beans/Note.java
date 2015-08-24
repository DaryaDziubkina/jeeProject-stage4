package by.gsy.epamProject.model.beans;


import by.gsy.epamProject.model.constans.Constants;

import javax.xml.bind.ValidationException;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Note extends Entity implements Serializable {
    private int author;
    private String topic;
    private String text;
    private Date dateExecution;
    private boolean isFixed;
    private boolean isDeleted;
    private String filename;

    public Note() {
        setIsFixed(false);
        setIsDeleted(false);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }

    public void setDateExecution(String dateExecution) {
        try {
            this.dateExecution = new Date((new SimpleDateFormat(Constants.Date.DATE_PARSE_PATTERN).parse(dateExecution))
                    .getTime());
        } catch (ParseException e) {
            new ValidationException(dateExecution, e);
        }
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
