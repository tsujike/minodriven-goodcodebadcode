/** 実行用エントリポイント */
class Sample6_24 {

	public static void main(String[] args) {
		Shape shape = new Rectangle(10, 10); //長方形クラスのインスタンスを図形型として扱う
		System.out.println(shape.area()); // 図形型なので、shape.area()で呼び出せる。A.100

		shape = new Circle(10); //円クラスのインスタンスを図形型として扱う
		System.out.println(shape.area()); // 図形型なので、shape.area()で呼び出せる。314.1592653589793
	}
}
