package service;

import service.custom.impl.BookCatagoryServiceImpl;
import service.custom.impl.BookServiceImpl;
import service.custom.impl.BorrowServiceImpl;
import service.custom.impl.MemberServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getService(ServiceType serviceType){
        switch (serviceType) {
            case MEMBER:
                return new MemberServiceImpl();
            case BOOKCATAGORY:
                return new BookCatagoryServiceImpl();
            case BOOK:
                return new BookServiceImpl();  
            case BORROW:
                return new BorrowServiceImpl(); 
            default:
                return null;
        }
    }

    public enum ServiceType{
        MEMBER,BOOKCATAGORY,BOOK,BORROW
    }
}
