package chapter14_1;

import java.time.LocalDateTime;

class PurchasePointPayment {
	final CustomerId customerId; // 購入者のID
	final ComicId comicId; // 購入するWebコミックのID
	final PurchasePoint consumptionPoint; // 購入で消費するポイント
	final LocalDateTime paymentDateTime; // 購入日時

	PurchasePointPayment(final Customer customer, final Comic comic) {
		if (customer.isDisabled()) {
			throw new IllegalArgumentException("有効な購入者ではありません。");
		}
		if (comic.isDisabled()) {
			throw new IllegalArgumentException("現在取り扱いのできないコミックです。");
		}
		if (customer.isShortOfPoint(comic)) {
			throw new RuntimeException("所持ポイントが不足しています。");
		}

		comicId = comic.id;
		customerId = customer.id;
		consumptionPoint = comic.currentPurchasePoint;
		paymentDateTime = LocalDateTime.now();
	}
}

class PurchasePoint {
	int amount;
}

class ComicId {
}

class CustomerId {
}

class Customer {
	public final CustomerId id;
	public final PurchasePoint possessionPoint;

	public Customer() {
		id = new CustomerId();
		possessionPoint = new PurchasePoint();
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isDisabled() {
		return false;
	}

	/**
	 * @param comic 購入対象のWebコミック
	 * @return 所持ポイントが不足している場合true
	 */
	public boolean isShortOfPoint(Comic comic) {
		return possessionPoint.amount < comic.currentPurchasePoint.amount;
	}
}

class Comic {
	public ComicId id;
	public PurchasePoint currentPurchasePoint;

	public boolean isEnabled() {
		return true;
	}

	public boolean isDisabled() {
		return false;
	}
}

class Sample14_1 {

	public static void main(String[] args) {
		// 購入決済ができる条件
		// 1.購入者のアカウントが有効である
		// 2.購入対象のコミックが、現在取り扱い中であること
		// 3.購入者の所持ポイントがコミックの購入ポイント以上であること

		Customer customer = new Customer();
		customer.possessionPoint.amount = 200;

		ComicId comicId = new ComicId();
		PurchasePoint consumePoint = new PurchasePoint();
		consumePoint.amount = 100;

		Comic comic = new Comic();
		comic.id = comicId;
		comic.currentPurchasePoint = consumePoint;

		PurchasePointPayment p1 = new PurchasePointPayment(customer, comic);

		System.out.println(p1.customerId);// 購入者のID (処理は省略)
		System.out.println(p1.comicId); // 購入するWebコミックのID (処理は省略)
		System.out.println(p1.consumptionPoint.amount);// 購入で消費するポイント 100
		System.out.println(p1.paymentDateTime);// 購入日時 2022-06-27T15:48:11.665409400
	}

}
