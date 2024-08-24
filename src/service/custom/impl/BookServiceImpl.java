package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.BookDao;
import dto.BookDto;
import entity.BookEntity;
import service.custom.BookService;

public class BookServiceImpl implements BookService{

    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BOOK);

    @Override
    public String save(BookDto bookDto) throws Exception {
        BookEntity entity = getBookEntity(bookDto);
        return bookDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(BookDto bookDto) throws Exception {
        BookEntity entity = getBookEntity(bookDto);
        return bookDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String bookID) throws Exception {
        return bookDao.delete(bookID) ? "Success" : "Fail";
    }

    @Override
    public BookDto get(String bookID) throws Exception {
        BookEntity entity = bookDao.get(bookID);
        if (entity != null) {
            return getBookDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<BookDto> getAll() throws Exception {
        ArrayList<BookEntity> bookEntities = bookDao.getAll();
        if (bookEntities != null && !bookEntities.isEmpty()) {
            ArrayList<BookDto> bookDtos = new ArrayList<>();

            for (BookEntity bookEntity : bookEntities){
                bookDtos.add(getBookDto(bookEntity));
            }
            return bookDtos;
        }
        return null;
    }

    private BookEntity getBookEntity (BookDto bookDto) {
        return new BookEntity(
            bookDto.getBookId(),
            bookDto.getTitle(),
            bookDto.getAuthor(),
            bookDto.getIsbn(),
            bookDto.getCatagoryId(),
            bookDto.isAvailability()
        );
    }

    private BookDto getBookDto (BookEntity entity) {
        return new BookDto(
            entity.getBookId(),
            entity.getTitle(),
            entity.getAuthor(),
            entity.getIsbn(),
            entity.getCatagoryId(),
            entity.isAvailability()
        );
    }
    
}
