package controller;

import dto.BorrowDto;
import service.ServiceFactory;
import service.custom.BorrowService;

public class BorrowController {
    private BorrowService borrowService = (BorrowService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BORROW);

    public String placeBorrow(BorrowDto borrowDto) throws Exception {
        return borrowService.placeBorrow(borrowDto);
    }
}
