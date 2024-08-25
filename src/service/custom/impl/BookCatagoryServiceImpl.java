package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.BookCatagoryDao;
import dto.BookCatagoryDto;
import entity.BookCatagoryEntity;
import service.custom.BookCatagoryService;

public class BookCatagoryServiceImpl implements BookCatagoryService{

    private BookCatagoryDao bookCatagoryDao = (BookCatagoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BOOKCATAGORY);

    @Override
    public String save(BookCatagoryDto bookCatagoryDto) throws Exception {
        BookCatagoryEntity entity = getBookCatagoryEntity(bookCatagoryDto);
        return bookCatagoryDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(BookCatagoryDto bookCatagoryDto) throws Exception {
        BookCatagoryEntity entity = getBookCatagoryEntity(bookCatagoryDto);
        return bookCatagoryDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String catagoryID) throws Exception {
        return bookCatagoryDao.delete(catagoryID) ? "Success" : "Fail";
    }

    @Override
    public BookCatagoryDto get(String catagoryID) throws Exception {
        BookCatagoryEntity entity = bookCatagoryDao.get(catagoryID);
        if (entity != null) {
            return getBookCatagoryDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<BookCatagoryDto> getAll() throws Exception {
        ArrayList<BookCatagoryEntity> bookCatagoryEntities = bookCatagoryDao.getAll();
        if (bookCatagoryEntities != null && !bookCatagoryEntities.isEmpty()) {
            ArrayList<BookCatagoryDto> bookCatagoryDtos = new ArrayList<>();

            for (BookCatagoryEntity bookCatagoryEntity : bookCatagoryEntities) {
                bookCatagoryDtos.add(getBookCatagoryDto(bookCatagoryEntity));
            }
            return bookCatagoryDtos;
        }
        return null;
    }

    private BookCatagoryEntity getBookCatagoryEntity(BookCatagoryDto bookCatagoryDto){
        return new BookCatagoryEntity(
            bookCatagoryDto.getCatagoryId(),
            bookCatagoryDto.getCatagoryName()
        );
    }

    private BookCatagoryDto getBookCatagoryDto(BookCatagoryEntity entity) {
        return new BookCatagoryDto(
            entity.getCatagoryId(),
            entity.getCatagoryName()
        );
    }
    
}
