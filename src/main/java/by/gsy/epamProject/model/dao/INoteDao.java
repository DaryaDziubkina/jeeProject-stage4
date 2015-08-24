package by.gsy.epamProject.model.dao;

import by.gsy.epamProject.model.beans.Note;
import by.gsy.epamProject.model.beans.NoteTypes;
import by.gsy.epamProject.model.exeption.DSException;

import java.util.List;


public interface INoteDao {

    void create(Note note) throws DSException;

    Note read(int authorId, int noteId) throws DSException;

    void update(Note note) throws DSException;

    void delete(Note note) throws DSException;

    //    List<Note> getAll(int authorId);
    List<Note> getAll(int authorId, NoteTypes noteTypes);
}
