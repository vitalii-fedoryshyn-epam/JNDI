import java.io.Serializable;

public class StockInfo implements Serializable {

	@Override
	public String toString() {
		return "StockInfo [brand=" + brand + ", price=" + price + "]";
	}

	private static final long serialVersionUID = 1L;

	private String brand;

	private double price;

	public StockInfo(String brand, double price) {
		super();
		this.brand = brand;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
