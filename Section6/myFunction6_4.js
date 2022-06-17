/** 面積を返すクラス */
class ShowArea {
  /** 
   * 面積を返すメソッド
   * @return{number} 面積
   */
  static showArea(shape) {
    return shape.area();
  }
}

/** インターフェイスチックな図形クラス */
class Shape {
  area() { };
}

/** 
 * 長方形クラス
 * @extends
 */
class Rectangle extends Shape {

  /**
    * @constructor
    * @param{number} width
    * @param{number} height
    */
  constructor(width, height) {
    super();
    this.width = width;
    this.height = height;
  }

  /** 面積を求めるメソッド */
  area() {
    return this.width * this.height;
  }

}


/** 
 * 円クラス
 * @extends
 */
class Circle extends Shape {

  /**
    * @constructor
    * @param{number} radius
    */
  constructor(radius) {
    super();
    this.radius = radius;
  }

  /** 面積を求めるメソッド */
  area() {
    return this.radius * this.radius * Math.PI;
  }

}

/** 実行用関数 */
function myFunction6_4() {

  let shape = new Rectangle(10, 10);
  console.log(shape.area()); // 100

  shape = new Circle(10);
  console.log(shape.area()); // 314.1592653589793

  // ShowArea処理を使う
  shape = new Rectangle(10, 10);
  console.log(ShowArea.showArea(shape)); // 100.0

}
