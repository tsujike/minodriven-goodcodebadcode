

package chapter14_2;

import java.util.ArrayList;
import java.util.List;

class Product {
	final int id;
	final String name;
	final int price;

	Product(final int id, final String name, final int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}

/**
 * 買い物かご
 */
class ShoppingCart {
	private final List<Product> products;

	ShoppingCart() {
		products = new ArrayList<Product>();
	}

	private ShoppingCart(List<Product> products) {
		this.products = products;
	}

	/**
	 * 買い物かごに商品を追加する
	 * 
	 * @param product 商品
	 * @return 商品が追加された買い物かご
	 */
	ShoppingCart add(final Product product) {
		final List<Product> adding = new ArrayList<>(products);
		adding.add(product);
		return new ShoppingCart(adding);
	}

	/**
	 * @return 商品の合計金額
	 */
	int totalPrice() {
		int amount = 0;
		for (Product each : products) {
			amount += each.price;
		}
		return amount;
	}
}

/**
 * 配送料
 */
class DeliveryCharge {
	private static final int CHARGE_FREE_THRESHOLD = 2000;
	private static final int PAY_CHARGE = 500;
	private static final int CHARGE_FREE = 0;
	final int amount;

	/**
	 * @param shoppingCart 買い物かご
	 */
	DeliveryCharge(final ShoppingCart shoppingCart) {
		amount = (shoppingCart.totalPrice() < CHARGE_FREE_THRESHOLD) ? PAY_CHARGE : CHARGE_FREE;
	}
}

class Sample14_8 {

	public static void main(String[] args) {
		System.out.println("hoge");

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(new Product(1, "Apple", 100));
		shoppingCart.add(new Product(2, "Banana", 200));
		shoppingCart.add(new Product(3, "Orange", 10000));

		DeliveryCharge deliberyCharge = new DeliveryCharge(shoppingCart);
		System.out.println(deliberyCharge.amount);

	}
}
