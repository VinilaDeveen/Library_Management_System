package dao.custom;

import java.util.List;

import dao.CrudDao;
import entity.BorrowDetailEntity;

public interface BorrowDetailDao extends CrudDao<BorrowDetailEntity, String> {

    List<BorrowDetailEntity> getBorrowDetailsByBorrowId(String borrowId) throws Exception;

    
}
