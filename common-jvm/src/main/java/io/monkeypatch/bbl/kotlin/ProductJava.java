package io.monkeypatch.bbl.kotlin;

public class ProductJava {


    private String title;
    private Double price;
    private Integer quantity;

    public ProductJava(String title, Double price, Integer quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductJava that = (ProductJava) o;

        if (!title.equals(that.title)) return false;
        if (!price.equals(that.price)) return false;
        return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
