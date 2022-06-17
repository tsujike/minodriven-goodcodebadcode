/** 金額クラス */
class Money {
  int amount;

  Money(int amount) {
    this.amount = amount;
  }

  //料金を加算するメソッド
  Money add(int other) {
    return new Money(other + this.amount);
  }
}

interface HotelRates {
	Money fee(); // 金額
Money busySeasonFee(); // ハイシーズン料金
}

/** プレミアム料金クラス */
class PremiumRates implements HotelRates {
  public Money fee() {
    return new Money(12000);
  }

  public Money busySeasonFee() {
    return fee().add(5000);
  }
}

/** 通常料金クラス */
class RegularRates implements HotelRates {
  public Money fee() {
    return new Money(7000);
  }

  public Money busySeasonFee() {
    return fee().add(3000);
  }
}

/** 料金判定クラス */
class CheckRate {
  Money showRate(HotelRates hotelRates) {
    return hotelRates.fee();
  }
}

/** 実行用エントリポイント */
class Sample6_4_3 {
  public static void main(String[] args) {
    HotelRates hotelRatesPremium = new PremiumRates();
    System.out.println(hotelRatesPremium.fee().amount); // 12000

    HotelRates hotelRatesRegular = new RegularRates();
    System.out.println(hotelRatesRegular.fee().amount); // 7000

    // 料金判定クラスを使う
    CheckRate checkRate = new CheckRate();
    System.out.println(checkRate.showRate(hotelRatesPremium).amount); // 12000

    // ハイシーズン料金
    System.out.println(hotelRatesPremium.busySeasonFee().amount); // 17000
    System.out.println(hotelRatesRegular.busySeasonFee().amount); // 10000
  }
}
