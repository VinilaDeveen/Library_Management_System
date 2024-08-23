package service.custom;

import java.util.ArrayList;

import dto.BookCatagoryDto;
import service.SuperService;

public interface BookCatagoryService extends SuperService{
    String save(BookCatagoryDto bookCatagoryDto) throws Exception;
    String update(BookCatagoryDto bookCatagoryDt) throws Exception;
    String delete(String catagoryID) throws Exception;
    BookCatagoryDto get(String catagoryID) throws Exception;
    ArrayList<BookCatagoryDto> getAll() throws Exception;
}
