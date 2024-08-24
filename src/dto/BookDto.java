package dto;

public class BookDto {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private String catagoryId;
    private boolean availability;
    
    public BookDto() {
    }

    public BookDto(String bookId, String title, String author, String isbn, String catagoryId, boolean availability) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.catagoryId = catagoryId;
        this.availability = availability;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "BookDto [bookId=" + bookId + ", title=" + title + ", author=" + author + ", isbn=" + isbn
                + ", catagoryId=" + catagoryId + ", availability=" + availability + "]";
    }
    
}
