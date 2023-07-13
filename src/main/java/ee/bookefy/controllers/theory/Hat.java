package ee.bookefy.controllers.theory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Hat {
    private Long id;
    private String colour;
    private String brand;
    private String price;
    private String style;
    private String size;

    public Hat(long l, String blue, String nike, String s, String casual, String m) {
        this.id = l;
        this.colour = blue;
        this.brand = nike;
        this.price = s;
        this.style = casual;
        this.size = m;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
