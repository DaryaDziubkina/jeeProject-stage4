package by.gsy.epamProject.model.daoImpl;

import by.gsy.epamProject.model.beans.Note;
import by.gsy.epamProject.model.beans.NoteTypes;
import by.gsy.epamProject.model.connection.ConnectionDataBase;
import by.gsy.epamProject.model.constans.Constants;
import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.exeption.DSException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NoteDaoDataBase implements INoteDao {

    public void create(Note note) throws DSException {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(Constants.Query.INSERT_INTO_NOTES);
            preparedStatement.setInt(1, note.getAuthor());
            preparedStatement.setString(2, note.getTopic());
            preparedStatement.setString(3, note.getText());
            preparedStatement.setDate(4, note.getDateExecution());
            preparedStatement.setBoolean(5, note.isFixed());
            preparedStatement.setBoolean(6, note.isDeleted());
            preparedStatement.setString(7, note.getFilename());
            synchronized (preparedStatement) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.RECORD_CREATE_ERROR);
        } finally {
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(con);
        }
    }

    public Note read(int authorId, int noteId) throws DSException {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = con.prepareStatement(Constants.Query.SELECT_FROM_NOTES);
            preparedStatement.setInt(1, authorId);
            preparedStatement.setInt(2, noteId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Note note = new Note();
                note.setId(resultSet.getInt(Constants.TablesDB.ID));
                note.setAuthor(resultSet.getInt(Constants.TablesDB.ID_AUTHOR));
                note.setTopic(resultSet.getString(Constants.TablesDB.TOPIC));
                note.setText(resultSet.getString(Constants.TablesDB.TEXT));
                note.setDateExecution(resultSet.getDate(Constants.TablesDB.DATE_EXECUTION));
                note.setIsFixed(resultSet.getBoolean(Constants.TablesDB.FIXED));
                note.setIsDeleted(resultSet.getBoolean(Constants.TablesDB.DELETED));
                note.setFilename(resultSet.getString(Constants.TablesDB.FILE_NAME));
                resultSet.close();
                return note;
            } else {
                throw new DSException(Constants.ERRORS.RECORD_NOT_EXIST);
            }
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.RECORD_NOT_EXIST);
        } finally {
            ConnectionDataBase.closeResultSet(resultSet);
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(con);
        }
    }


    public void update(Note note) throws DSException {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(Constants.Query.UPDATE_NOTE);
            preparedStatement.setString(1, note.getTopic());
            preparedStatement.setString(2, note.getText());
            preparedStatement.setDate(3, note.getDateExecution());
            preparedStatement.setBoolean(4, note.isFixed());
            preparedStatement.setBoolean(5, note.isDeleted());
            preparedStatement.setString(6, note.getFilename());
            preparedStatement.setInt(7, note.getAuthor());
            preparedStatement.setInt(8, note.getId());
            synchronized (preparedStatement) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.RECORD_UPDATE_ERROR);
        } finally {
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(con);
        }
    }

    public void delete(Note note) throws DSException {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = con.prepareStatement(Constants.Query.DELETE_NOTE);
            preparedStatement.setInt(1, note.getAuthor());
            preparedStatement.setInt(2, note.getId());
            synchronized (preparedStatement) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.RECORD_DELETE_ERROR);
        } finally {
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(con);
        }
    }

    public List<Note> getAll(int authorId, NoteTypes noteTypes) throws DSException {
        List<Note> notes = new ArrayList<Note>();
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = con.prepareStatement(Constants.Query.SELECT_NOTES_BY_TYPES_AND_DATA);
            preparedStatement.setInt(1, authorId);
            preparedStatement.setBoolean(2, noteTypes.isFixed());
            preparedStatement.setBoolean(3, noteTypes.isDeleted());
            preparedStatement.setDate(4, noteTypes.getDateMax());
            preparedStatement.setDate(5, noteTypes.getDateMin());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Note note = new Note();
                note.setId(resultSet.getInt(Constants.TablesDB.ID));
                note.setAuthor(resultSet.getInt(Constants.TablesDB.ID_AUTHOR));
                note.setTopic(resultSet.getString(Constants.TablesDB.TOPIC));
                note.setText(resultSet.getString(Constants.TablesDB.TEXT));
                note.setDateExecution(resultSet.getDate(Constants.TablesDB.DATE_EXECUTION));
                note.setIsFixed(resultSet.getBoolean(Constants.TablesDB.FIXED));
                note.setIsDeleted(resultSet.getBoolean(Constants.TablesDB.DELETED));
                note.setFilename(resultSet.getString(Constants.TablesDB.FILE_NAME));
                notes.add(note);
            }
            resultSet.close();
            return notes;
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.RECORD_READING_ERROR);
        } finally {
            ConnectionDataBase.closeResultSet(resultSet);
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(con);
        }
    }

    public List<Note> getAll(int authorId) {
        return getAll(authorId, NoteTypes.ALL);
    }
}

