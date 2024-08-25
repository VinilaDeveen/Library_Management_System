package service.custom.impl;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

public class BorrowServiceImpl implements BorrowService {
    private BorrowDao borrowDao = (BorrowDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROW);
    private BorrowDetailDao borrowDetailDao = (BorrowDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROWDETAIL);
    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BOOK);

    @Override
    public String placeBorrow(BorrowDto borrowDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            BorrowEntity borrowEntity = new BorrowEntity(borrowDto.getBorrowId(), borrowDto.getMemId(), borrowDto.getBorrowDate(), borrowDto.getDueDate());

            if (borrowDao.create(borrowEntity)) {
                boolean isBorrowDetailSaved = true;

                for (BorrowDetailDto borrowDetailDto : borrowDto.getBorrowDetailDtos()) {
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

    @Override
    public String processReturn(String borrowId, String returnDate) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            BorrowEntity borrowEntity = borrowDao.get(borrowId);
            if (borrowEntity != null) {
                // Calculate fines
                LocalDate dueDate = LocalDate.parse(borrowEntity.getDueDate());
                LocalDate returnedDate = LocalDate.parse(returnDate);
                long daysLate = ChronoUnit.DAYS.between(dueDate, returnedDate);
                double fines = daysLate > 0 ? daysLate * 5.0 : 0.0; // Assuming fine is 5.0 per day

                borrowEntity.setReturnDate(returnDate);
                borrowEntity.setFines(fines);

                if (borrowDao.update(borrowEntity)) {
                    boolean isBookUpdated = true;

                    List<BorrowDetailEntity> borrowDetailEntities = borrowDetailDao.getBorrowDetailsByBorrowId(borrowId);
                    for (BorrowDetailEntity borrowDetailEntity : borrowDetailEntities) {
                        BookEntity bookEntity = bookDao.get(borrowDetailEntity.getBookId());
                        if (bookEntity != null) {
                            bookEntity.setAvailability(true);
                            if (!bookDao.update(bookEntity)) {
                                isBookUpdated = false;
                            }
                        }
                    }

                    if (isBookUpdated) {
                        connection.commit();
                        return "Return Processed Successfully!";
                    } else {
                        connection.rollback();
                        return "Book Update Error";
                    }
                } else {
                    connection.rollback();
                    return "Return Save Error";
                }
            } else {
                return "Borrow Record Not Found!";
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public double getFines(String borrowId) throws Exception {
        BorrowEntity borrowEntity = borrowDao.get(borrowId);
    if (borrowEntity != null) {
        return borrowEntity.getFines();
    } else {
        throw new Exception("Borrow Record Not Found!");
    }
    }
}