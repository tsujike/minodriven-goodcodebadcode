import java.util.HashSet;
import java.util.Set;

interface ExcellentCustomerRule {
	/**
	 * @param history 購入履歴
	 * @return 条件を満たす場合true
	 */
	boolean ok(final PurchaseHistory history);
}

/** 購入金額合計クラス */
class GoldCustomerPurchaseAmountRule implements ExcellentCustomerRule {
  public boolean ok(final PurchaseHistory history) {
    return 100000 <= history.totalAmount;
  }
}

/** 購入回数合計クラス */
class PurchaseFrequencyRule implements ExcellentCustomerRule {
  public boolean ok(final PurchaseHistory history) {
    return 10 <= history.purchaseFrequencyPerMonth;
  }
}

/** 返品率クラス */
class ReturnRateRule implements ExcellentCustomerRule {
  public boolean ok(final PurchaseHistory history) {
    return history.returnRate <= 0.001;
  }
}

/** 優良顧客ポリシークラス */
class ExcellentCustomerPolicy {
  private final Set<ExcellentCustomerRule> rules;

  ExcellentCustomerPolicy() {
    rules = new HashSet();
  }

	/**
   * ルールを追加するメソッド
	 * @param rule ルール
	 */
	void add(final ExcellentCustomerRule rule) {
  rules.add(rule);
}

/**
 * ルールをすべて満たすか判定するメソッド
 * @param history 購入履歴
 * @return ルールを全て満たす場合true
 */
boolean complyWithAll(final PurchaseHistory history) {
  for (ExcellentCustomerRule each : rules) {
    if (!each.ok(history))
      return false;
  }
  return true;
}
}

/** ゴールド会員ポリシークラス */
class GoldCustomerPolicy {
  private final ExcellentCustomerPolicy policy;

  GoldCustomerPolicy() {
    policy = new ExcellentCustomerPolicy();
    policy.add(new GoldCustomerPurchaseAmountRule());
    policy.add(new PurchaseFrequencyRule());
    policy.add(new ReturnRateRule());
  }

	/**
	 * @param history 購入履歴
	 * @return ルールを全て満たす場合true
	 */
	boolean complyWithAll(final PurchaseHistory history) {
  return policy.complyWithAll(history);
}
}

/** シルバー会員ポリシークラス */
class SilverCustomerPolicy {
  private final ExcellentCustomerPolicy policy;

  SilverCustomerPolicy() {
    policy = new ExcellentCustomerPolicy();
    policy.add(new PurchaseFrequencyRule());
    policy.add(new ReturnRateRule());
  }

	/**
	 * @param history 購入履歴
	 * @return ルールを全て満たす場合true
	 */
	boolean complyWithAll(final PurchaseHistory history) {
  return policy.complyWithAll(history);
}
}

/** 購入履歴クラス */
class PurchaseHistory {
  int purchaseFrequencyPerMonth;
  double returnRate;
  int totalAmount;

  PurchaseHistory(int purchaseFrequencyPerMonth, double returnRate, int totalAmount) {
    this.purchaseFrequencyPerMonth = purchaseFrequencyPerMonth;
    this.returnRate = returnRate;
    this.totalAmount = totalAmount;
  }
}

/** 実行用エントリポイント */
class Sample6_49 {

  public static void main(String[] args) {
    GoldCustomerPolicy goldCustomerPolicy = new GoldCustomerPolicy();
    SilverCustomerPolicy silverCustomerPolicy = new SilverCustomerPolicy();

    PurchaseHistory goldMembersHistory = new PurchaseHistory(11, 0.0006, 100001);
    System.out.println(goldCustomerPolicy.complyWithAll(goldMembersHistory)); // true

    PurchaseHistory silverMembersHistory = new PurchaseHistory(11, 0.0006, 99999);
    System.out.println(silverCustomerPolicy.complyWithAll(silverMembersHistory)); // true

    PurchaseHistory silverMembersHistory2 = new PurchaseHistory(9, 0.0006, 99999);
    System.out.println(silverCustomerPolicy.complyWithAll(silverMembersHistory2)); // false

  }
}
