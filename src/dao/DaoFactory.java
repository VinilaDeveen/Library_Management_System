package dao;

import dao.custom.impl.BookCatagoryDaoImpl;
import dao.custom.impl.BookDaoImpl;
import dao.custom.impl.BorrowDaoImpl;
import dao.custom.impl.BorrowDetailDaoImpl;
import dao.custom.impl.MemberDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes type) {
        switch (type) {
            case MEMBER:
               return new MemberDaoImpl();
            case BOOKCATAGORY:
               return new BookCatagoryDaoImpl();   
            case BOOK:
               return new BookDaoImpl();
            case BORROW:
               return new BorrowDaoImpl();
            case BORROWDETAIL:
               return new BorrowDetailDaoImpl();      
            default:
                return null;
        }
    }

    public enum DaoTypes {
        MEMBER,BOOKCATAGORY,BOOK,BORROW,BORROWDETAIL
    }
}
