package dto;

import java.util.ArrayList;

public class BorrowDto {
    private String borrowId;
    private String memId;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private double fines;

    private ArrayList<BorrowDetailDto> borrowDetailDtos;

    public BorrowDto() {
    }

    public BorrowDto(String borrowId, String memId, String borrowDate, String dueDate,
            ArrayList<BorrowDetailDto> borrowDetailDtos) {
        this.borrowId = borrowId;
        this.memId = memId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.borrowDetailDtos = borrowDetailDtos;
    }

    public BorrowDto(String borrowId, String memId, String borrowDate, String dueDate, String returnDate, double fines,
            ArrayList<BorrowDetailDto> borrowDetailDtos) {
        this.borrowId = borrowId;
        this.memId = memId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fines = fines;
        this.borrowDetailDtos = borrowDetailDtos;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getFines() {
        return fines;
    }

    public void setFines(double fines) {
        this.fines = fines;
    }

    public ArrayList<BorrowDetailDto> getBorrowDetailDtos() {
        return borrowDetailDtos;
    }

    public void setBorrowDetailDtos(ArrayList<BorrowDetailDto> borrowDetailDtos) {
        this.borrowDetailDtos = borrowDetailDtos;
    }

    @Override
    public String toString() {
        return "BorrowDto [borrowId=" + borrowId + ", memId=" + memId + ", borrowDate=" + borrowDate + ", dueDate="
                + dueDate + ", returnDate=" + returnDate + ", fines=" + fines + ", borrowDetailDtos=" + borrowDetailDtos
                + "]";
    }

    
    
}
