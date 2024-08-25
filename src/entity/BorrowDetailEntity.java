package entity;

public class BorrowDetailEntity {
    private String borrowId;
    private String bookId;
    private String dueDate;
    
    public BorrowDetailEntity() {
    }

    public BorrowDetailEntity(String borrowId, String bookId, String dueDate) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.dueDate = dueDate;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "BorrowDetailEntity [borrowId=" + borrowId + ", bookId=" + bookId + ", dueDate=" + dueDate + "]";
    }

    
    
}
