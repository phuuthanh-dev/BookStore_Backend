package vn.bookstore.backend.dto;

public class WishlistDTO {
    private Integer wishlistId;
    private Integer bookId;
    private Integer userId;

    public Integer getWishlistId() {
        return this.wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Integer getBookId() {
        return this.bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
