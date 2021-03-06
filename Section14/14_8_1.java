package chapter14_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 配送管理クラス
 */
class DeliveryManager {
	/**
	 * 配送料を返す。
	 * 
	 * @param products 配送対象の商品リスト
	 * @return 配送料
	 */
	public static int deliveryCharge(List<Product> products) {
		int charge = 0;
		int totalPrice = 0;
		for (Product each : products) {
			totalPrice += each.price;
		}
		if (totalPrice < 2000) {
			charge = 500;
		} else {
			charge = 0;
		}
		return charge;
	}
}

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

//買い物かご
class ShoppingCart {
	final List<Product> products;

	ShoppingCart() {
		products = new ArrayList<Product>();
	}

	private ShoppingCart(List<Product> products) {
		this.products = products;
	}

	ShoppingCart add(final Product product) {
		final List<Product> adding = new ArrayList<>(products);
		adding.add(product);
		return new ShoppingCart(adding);
	}
}

class DeliveryCharge {
	final int amount;

	DeliveryCharge(final ShoppingCart shoppingCart) {
		int totalPrice = shoppingCart.products.get(0).price + shoppingCart.products.get(1).price;
		if (totalPrice < 2000) {
			amount = 500;
		} else {
			amount = 0;
		}
	}
}

class Sample14_8 {

	public static void main(String[] args) {
//		System.out.println("hoge");

		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1, "Apple", 100));
		list.add(new Product(2, "Banana", 200));
		list.add(new Product(3, "Orange", 10000));

//		DeliveryManager deliberyManager = new DeliveryManager();
//		System.out.println(deliberyManager.deliveryCharge(list));

		// 違った、Staticやん
		System.out.println(DeliveryManager.deliveryCharge(list));
	}
}
