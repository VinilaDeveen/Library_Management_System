package service.custom;

import dto.BorrowDto;
import service.SuperService;

public interface BorrowService extends SuperService{
    public String placeBorrow(BorrowDto borrowDto) throws Exception;

    public String processReturn(String borrowId, String returnDate) throws Exception;

    public double getFines(String borrowId) throws Exception;
}
