package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.MemberDao;
import entity.MemberEntity;

public class MemberDaoImpl implements MemberDao{

    @Override
    public boolean create(MemberEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Member VALUES(?,?,?,?,?)", t.getMemId(), t.getName(), 
        t.getPhone(), t.getUserName(), t.getPassword());
    }

    @Override
    public boolean update(MemberEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Member SET MemName = ?, Phone = ?, UserName = ?, UserPassword = ? WHERE MemID = ?", 
        t.getName(), t.getPhone(), t.getUserName(), t.getPassword(), t.getMemId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Member WHERE MemID = ?", id);
    }

    @Override
    public MemberEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Member WHERE MemID = ?", id);
        if (rst.next()) {
            MemberEntity entity = new MemberEntity(rst.getString("MemID"),rst.getString("MemName"),
            rst.getString("Phone"),rst.getString("UserName"),rst.getString("UserPassword"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<MemberEntity> getAll() throws Exception {
        ArrayList<MemberEntity> memberEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Memeber");
        while (rst.next()) {
            MemberEntity entity = new MemberEntity(rst.getString("MemID"),rst.getString("MemName"),
            rst.getString("Phone"),rst.getString("UserName"),rst.getString("UserPassword"));
            memberEntities.add(entity);
        }
        return memberEntities;
    }
    
    @Override
public MemberEntity getByUsernameAndPassword(String username, String password) throws Exception {
    ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Member WHERE UserName = ? AND UserPassword = ?", username, password);
    if (rst.next()) {
        return new MemberEntity(
            rst.getString("MemID"),
            rst.getString("MemName"),
            rst.getString("Phone"),
            rst.getString("UserName"),
            rst.getString("UserPassword")
        );
    }
    return null;
}
}
