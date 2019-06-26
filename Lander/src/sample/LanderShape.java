package sample;

public class LanderShape {

    private double height;
    private double width;
    private double area;

    public LanderShape(double height, double width, double area){
        this.height = height;
        this.width = width;
        this.area = area;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

}
