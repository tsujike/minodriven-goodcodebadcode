function myFunction6_2() {
  /**
   * 税込み金額を返す関数 
   * @param {number} price
   * @return {number} 税込み金額
   */
  const getIncludeTax = price => {
    //priceがマイナスであることはあり得ない
    if (price < 0) return;
    //priceが1000を超えることはあり得ない
    if (1000 < price) return;
    //priceを使った様々な処理
    //中略
    const result = price * 1.1;
    return result;
  }

  console.log(getIncludeTax(100)); //110.0000
}
