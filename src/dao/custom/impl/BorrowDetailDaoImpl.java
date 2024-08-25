package dao.custom.impl;

import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.BorrowDetailDao;
import entity.BorrowDetailEntity;

public class BorrowDetailDaoImpl implements BorrowDetailDao{

    @Override
    public boolean create(BorrowDetailEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO BorrowDetail VALUES(?,?,?)", t.getBorrowId(),t.getBookId(),t.getDueDate());
    }

    @Override
    public boolean update(BorrowDetailEntity t) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public BorrowDetailEntity get(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<BorrowDetailEntity> getAll() throws Exception {
        return null;
    }
    
}
