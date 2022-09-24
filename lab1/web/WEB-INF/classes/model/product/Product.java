package model.product;

public class Product {
    private String name;
    private int count;
    private int userId;
    private int id;

    public Product(int userId, String name, int count, int id){
        this.userId = userId;
        this.name = name;
        this.count = count;
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
