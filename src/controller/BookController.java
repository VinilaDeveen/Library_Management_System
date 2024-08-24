package controller;

import java.util.ArrayList;

import dto.BookDto;
import service.ServiceFactory;
import service.custom.BookService;

public class BookController {
    private BookService bookService = (BookService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BOOK);

     public String save(BookDto bookDto) throws Exception{
        return bookService.save(bookDto);
    }

    public String update(BookDto bookDto) throws Exception{
        return bookService.update(bookDto);
    }

    public String delete(String bookID) throws Exception{
        return bookService.delete(bookID);
    }

    public ArrayList<BookDto> getAll() throws Exception{
        return bookService.getAll();
    }

    public BookDto get(String bookID) throws Exception{
        return bookService.get(bookID);
    }
}
