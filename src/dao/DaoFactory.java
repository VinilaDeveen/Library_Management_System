package dao;

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
            default:
                return null;
        }
    }

    public enum DaoTypes {
        MEMBER;
    }
}
