package controller;

import java.util.ArrayList;

import dto.BookCatagoryDto;
import service.ServiceFactory;
import service.custom.BookCatagoryService;

public class BookCatagoryController {
    private BookCatagoryService bookCatagoryService = (BookCatagoryService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BOOKCATAGORY);

     public String save(BookCatagoryDto bookCatagoryDto) throws Exception{
        return bookCatagoryService.save(bookCatagoryDto);
    }

    public String update(BookCatagoryDto bookCatagoryDto) throws Exception{
        return bookCatagoryService.update(bookCatagoryDto);
    }

    public String delete(String catagoryID) throws Exception{
        return bookCatagoryService.delete(catagoryID);
    }

    public ArrayList<BookCatagoryDto> getAll() throws Exception{
        return bookCatagoryService.getAll();
    }

    public BookCatagoryDto get(String catagoryID) throws Exception{
        return bookCatagoryService.get(catagoryID);
    }
}
