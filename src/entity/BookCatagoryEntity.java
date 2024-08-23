package entity;

public class BookCatagoryEntity {
    private String catagoryId;
    private String catagoryName;
    
    public BookCatagoryEntity() {
    }

    public BookCatagoryEntity(String catagoryId, String catagoryName) {
        this.catagoryId = catagoryId;
        this.catagoryName = catagoryName;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    @Override
    public String toString() {
        return "BookCatagoryEntity [catagoryId=" + catagoryId + ", catagoryName=" + catagoryName + "]";
    }

    
}
