package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.BorrowDao;
import entity.BorrowEntity;

public class BorrowDaoImpl implements BorrowDao {

    @Override
    public boolean create(BorrowEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Borrow (BorrowID, MemberID, BorrowDate, DueDate, ReturnDate, Fine) VALUES(?,?,?,?,?,?)", t.getBorrowId(),t.getMemId(),t.getBorrowDate(),t.getDueDate(),t.getReturnDate(),t.getFines());
    }

    @Override
    public boolean update(BorrowEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Borrow SET ReturnDate = ?, Fine = ? WHERE BorrowID = ?", t.getReturnDate(), t.getFines(), t.getBorrowId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public BorrowEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Borrow WHERE BorrowID = ?", id);
        if (rst.next()) {
            BorrowEntity entity = new BorrowEntity(rst.getString("BorrowID"),rst.getString("MemberID"),
            rst.getString("BorrowDate"),rst.getString("DueDate"),rst.getString("ReturnDate"),rst.getDouble("Fine"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BorrowEntity> getAll() throws Exception {
        return null;
    }

    
}
