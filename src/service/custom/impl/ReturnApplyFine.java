package service.custom.impl;

import dao.DaoFactory;
import dao.custom.BorrowDao;
import dto.BorrowDto;

public class ReturnApplyFine {
    private BorrowDao borrowDao = (BorrowDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROW);
    
    public ReturnApplyFine() {
    }

    public void processReturn(BorrowDto borrowDto) {  
    }
}
