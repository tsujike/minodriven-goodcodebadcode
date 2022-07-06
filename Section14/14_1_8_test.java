package chapter14_2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Sample14_2_test {

//	@Test
//	void test() {
//		fail("まだ実装されていません");
//	}

	// 商品の合計金額が2000円未満のばあい、配送料は500円
	@Test
	void payCharge() {
		ShoppingCart emptyCart = new ShoppingCart();
		ShoppingCart oneProductAdded = emptyCart.add(new Product(1, "商品A", 500));
		ShoppingCart twoProductAdded = oneProductAdded.add(new Product(2, "商品B", 1499));
		DeliveryCharge charge = new DeliveryCharge(twoProductAdded);

		assertEquals(500, charge.amount);
	}

	@Test
	void chargeFree() {
		ShoppingCart emptyCart = new ShoppingCart();
		ShoppingCart oneProductAdded = emptyCart.add(new Product(1, "商品A", 500));
		ShoppingCart twoProductAdded = oneProductAdded.add(new Product(2, "商品B", 1500));
		DeliveryCharge charge = new DeliveryCharge(twoProductAdded);

		assertEquals(0, charge.amount);
	}

}

