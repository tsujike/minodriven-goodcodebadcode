//どこかに書かれているだろう処理
class ShowArea {
	static double showArea(Shape shape) {
		return shape.area(); 
	}
}

interface Shape {
	double area();
}

/** Rectangleクラス */
class Rectangle implements Shape {
	private final double width;
	private final double height;

	Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	// オーバーライドによる実装
	public double area() {
		return width * height;
	}

}

/** Circleクラス */
class Circle implements Shape {
	private final double radius;

	Circle(double radius) {
		this.radius = radius;
	}

	// オーバーライドによる実装
	public double area() {
		return radius * radius * Math.PI;
	}

}

/** 実行用エントリポイント */
class Sample6_26 {

	public static void main(String[] args) {
		Shape shape = new Rectangle(10, 10);
		System.out.println(shape.area()); // 100

		shape = new Circle(10);
		System.out.println(shape.area()); // 314.1592653589793

		// ShowArea処理を使う
		shape = new Rectangle(10, 10);
		System.out.println(ShowArea.showArea(shape)); // 100.0

	}
}
