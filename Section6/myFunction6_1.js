function myFunction6_1() {
  /**
   * 税込み金額を返す関数 
   * @param {number} price
   * @return {number} 税込み金額
   */
  const getIncludeTax = price => {
    if (price < 0) throw new Error("priceが0以下です"); //ガード節
    return price * 1.1;
  }
  console.log(getIncludeTax(-10)); //Error: priceが0以下です
}
