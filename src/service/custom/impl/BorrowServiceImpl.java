package service.custom.impl;

import java.sql.Connection;

import dao.DaoFactory;
import dao.custom.BookDao;
import dao.custom.BorrowDao;
import dao.custom.BorrowDetailDao;
import db.DBConnection;
import dto.BorrowDetailDto;
import dto.BorrowDto;
import entity.BookEntity;
import entity.BorrowDetailEntity;
import entity.BorrowEntity;
import service.custom.BorrowService;

public class BorrowServiceImpl implements BorrowService{
    private BorrowDao borrowDao = (BorrowDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROW);
    private BorrowDetailDao borrowDetailDao = (BorrowDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROWDETAIL);
    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BOOK);

    @Override
    public String placeBorrow(BorrowDto borrowDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            BorrowEntity borrowEntity = new BorrowEntity(borrowDto.getBorrowId(),borrowDto.getMemId(),borrowDto.getBorrowDate(),borrowDto.getDueDate());

            if (borrowDao.create(borrowEntity)) {
                boolean isBorrowDetailSaved = true;

                for (BorrowDetailDto borrowDetailDto: borrowDto.getBorrowDetailDtos()) {
                    BorrowDetailEntity borrowDetailEntity = new BorrowDetailEntity(
                        borrowDto.getBorrowId(),
                        borrowDetailDto.getBookId(),
                        borrowDetailDto.getDueDate()
                    );
                    if (!borrowDetailDao.create(borrowDetailEntity)) {
                        isBorrowDetailSaved = false;
                    }
                }

                if (isBorrowDetailSaved) {
                    boolean isBookUpdated = true;
                    for (BorrowDetailDto borrowDetailDto : borrowDto.getBorrowDetailDtos()) {
                        BookEntity bookEntity = bookDao.get(borrowDetailDto.getBookId());
                        if (bookEntity != null) {
                            bookEntity.setAvailability(false);
                            if (!bookDao.update(bookEntity)) {
                                isBookUpdated = false;
                            }
                        }
                    }
                    if (isBookUpdated) {
                        connection.commit();
                        return "Success";
                    } else {
                        connection.rollback();
                        return "Book Update Error";
                    }

                } else {
                    connection.rollback();
                    return "Borrow Detail save Error";
                }

            } else {
                connection.rollback();
                return "Borrow save error";
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    
}
