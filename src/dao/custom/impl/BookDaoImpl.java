package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.BookDao;
import entity.BookEntity;

public class BookDaoImpl implements BookDao{

    @Override
    public boolean create(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO book VALUES (?,?,?,?,?,?)", t.getBookId(),t.getTitle(),t.getAuthor(),t.getIsbn(),t.getCatagoryId(),t.isAvailability());
    }

    @Override
    public boolean update(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE book SET BookTitle = ?, Author = ?, Isbn = ?, CategoryID = ?, Availability = ? WHERE BookID = ?", t.getTitle(),t.getAuthor(),t.getIsbn(),t.getCatagoryId(),t.isAvailability(),t.getBookId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM book WHERE BookID = ?", id);
    }

    @Override
    public BookEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM book WHERE BookID = ?", id);
        if (rst.next()) {
            BookEntity entity = new BookEntity(rst.getString("BookID"),rst.getString("BookTitle"),rst.getString("Author"),rst.getString("Isbn"),rst.getString("CategoryID"),rst.getBoolean("Availability"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BookEntity> getAll() throws Exception {
        ArrayList<BookEntity> bookEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM book");
        while (rst.next()) {
            BookEntity entity = new BookEntity(rst.getString("BookID"),rst.getString("BookTitle"),rst.getString("Author"),rst.getString("Isbn"),rst.getString("CategoryID"),rst.getBoolean("Availability"));
            bookEntities.add(entity);
        }
        return bookEntities; 
    }
    
}
