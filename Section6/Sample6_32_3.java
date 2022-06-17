import java.util.HashMap;
import java.util.Map;

interface Magic {
	String name(); // 名前
	MagicPoint costMagicPoint(); // 消費魔法力
	AttackPower attackPower(); // 攻撃力
	TechnicalPoint costTechnicalPoint(); // 消費テクニカルポイント
}

/** Fireクラス */
class Fire implements Magic {
	private final Member member;

	Fire(final Member member) {
		this.member = member;
	}

	public String name() {
		return "ファイヤ";
	}

	public MagicPoint costMagicPoint() {
		return new MagicPoint(2);
	}

	public AttackPower attackPower() {
		final int value = 20 + (int) (member.level * 0.5);
		return new AttackPower(value);
	}

	public TechnicalPoint costTechnicalPoint() {
		return new TechnicalPoint(0);
	}

}

/** Shidenクラス */
class Shiden implements Magic {
	private final Member member;

	Shiden(final Member member) {
		this.member = member;
	}

	public String name() {
		return "紫電";
	}

	public MagicPoint costMagicPoint() {
		final int value = 5 + (int) (member.level * 0.2);
		return new MagicPoint(value);
	}

	public AttackPower attackPower() {
		final int value = 50 + (int) (member.agility * 1.5);
		return new AttackPower(value);
	}

	public TechnicalPoint costTechnicalPoint() {
		return new TechnicalPoint(5);
	}

}

/** HellFireクラス */
class HellFire implements Magic {
	private final Member member;

	HellFire(final Member member) {
		this.member = member;
	}

	public String name() {
		return "地獄の業火";
	}

	public MagicPoint costMagicPoint() {
		return new MagicPoint(16);
	}

	public AttackPower attackPower() {
		final int value = 200 + (int) (member.magicAttack * 0.5 + member.vitality * 2);
		return new AttackPower(value);
	}

	public TechnicalPoint costTechnicalPoint() {
		final int value = 20 + (int) (member.level * 0.4);
		return new TechnicalPoint(value);
	}

}

/** AttackPowerクラス */
class AttackPower {
	final int power;

	AttackPower(int power) {
		this.power = power;
	}
}

/** TechnicalPointクラス */
class TechnicalPoint {
	final int tp;

	TechnicalPoint(int tp) {
		this.tp = tp;
	}
}

/** MagicPointクラス */
class MagicPoint {
	final int mp;

	MagicPoint(int mp) {
		this.mp = mp;
	}
}

/** Memberクラス */
class Member {
	int hitPoint;
	int maxHitPoint;
	int level;
	int agility;
	int magicAttack;
	int vitality;

	Member(int hitPoint, int maxHitpoint, int level, int agility, int magicAttack, int vitality) {
		this.hitPoint = hitPoint;
		this.maxHitPoint = maxHitpoint;
		this.level = level;
		this.agility = agility;
		this.magicAttack = magicAttack;
		this.vitality = vitality;

	}
}

/** enum */
enum MagicType {
	fire, shiden, hellFire;
}

/** MagicSwitchクラス */
class MagicSwitch {
	private Member member;
	Map<MagicType, Magic> magics = new HashMap<>();

	MagicSwitch(Member member) {
		this.member = member;
	}

	void setMapElement() {
		final Fire fire = new Fire(member);
		final Shiden shiden = new Shiden(member);
		final HellFire hellFire = new HellFire(member);

		magics.put(MagicType.fire, fire);
		magics.put(MagicType.shiden, shiden);
		magics.put(MagicType.hellFire, hellFire);
	}

	// 魔法攻撃を実行する
	void magicAttack(final MagicType magicType) {
		final Magic usingMagic = magics.get(magicType);
		showMagicName(usingMagic);
		consumeMagicPoint(usingMagic);
		consumeTechnicalPoint(usingMagic);
		magicDamage(usingMagic);

	}

	// 魔法の名前を画面表示する
	void showMagicName(final Magic magic) {
		final String name = magic.name();
		// nameを使った表示処理
		System.out.println(name + "を使った");
	}

	// 魔法を消費する
	void consumeMagicPoint(final Magic magic) {
		final MagicPoint costMagicPoint = magic.costMagicPoint();
		// costMagicPointを使った魔法力消費処理
		System.out.println("MPが" + costMagicPoint.mp + "減った");
	}

	// テクニカルポイントを消費する
	void consumeTechnicalPoint(final Magic magic) {
		final TechnicalPoint costTechnicalPoint = magic.costTechnicalPoint();
		// consumeTechnicalPointを使ったテクニカルポイント消費処理
		System.out.println("テクニカルポイントが" + costTechnicalPoint.tp + "減った");
	}

	// ダメージ計算する
	void magicDamage(final Magic magic) {
		final AttackPower attackPower = magic.attackPower();
		// attackPowerを使ったダメージ処理
		System.out.println("モンスターに" + attackPower.power + "のダメージを与えた");
	}
}

/** 実行用エントリポイント */ 
class Sample6_32_3 {

	public static void main(String[] args) {
		Member member = new Member(100, 100, 10, 4, 5, 20);
		MagicSwitch magicSwitch = new MagicSwitch(member);

    // Mapを生成する
		magicSwitch.setMapElement();

    // 地獄の業火の攻撃
		magicSwitch.magicAttack(MagicType.hellFire);
    // 地獄の業火を使った, MPが16減った, テクニカルポイントが24減った, モンスターに242のダメージを与えた
	}
}
