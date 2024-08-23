package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.BookCatagoryDao;
import entity.BookCatagoryEntity;

public class BookCatagoryDaoImpl implements BookCatagoryDao {

    @Override
    public boolean create(BookCatagoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO BookCategory VALUES(?,?)", t.getCatagoryId(), t.getCatagoryName());
    }

    @Override
    public boolean update(BookCatagoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE BookCategory SET CategoryName = ? WHERE CategoryID = ?", t.getCatagoryName(), t.getCatagoryId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM BookCategory WHERE CategoryID = ?",id);
    }

    @Override
    public BookCatagoryEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM BookCategory WHERE CategoryID = ?", id);
        if (rst.next()) {
            BookCatagoryEntity entity = new BookCatagoryEntity(rst.getString("CategoryID"),rst.getString("CategoryName"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BookCatagoryEntity> getAll() throws Exception {
        ArrayList<BookCatagoryEntity> bookCatagoryEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM BookCategory");
        while (rst.next()) {
            BookCatagoryEntity entity = new BookCatagoryEntity(rst.getString("CategoryID"),rst.getString("CategoryName"));
            bookCatagoryEntities.add(entity);
        }
        return bookCatagoryEntities;
    }
    
}
