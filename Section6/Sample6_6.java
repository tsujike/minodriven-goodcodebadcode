import java.util.HashMap;
import java.util.Map;

interface Damage {
	void execute(final int damageAmount);
}

/** HPダメージクラス */
class HitPointDamage implements Damage {
	Member member;

	HitPointDamage(Member member) {
		this.member = member;
	}

	public void execute(final int damageAmount) {
		member.hitPoint -= damageAmount;
		if (0 < member.hitPoint)
			return;

		member.hitPoint = 0;
		member.addState(StateType.dead);
	}
}

/** MPダメージクラス */
class MagicPointDamage implements Damage {
	Member member;

	MagicPointDamage(Member member) {
		this.member = member;
	}

	// 中略
	public void execute(final int damageAmount) {
		member.magicPoint -= damageAmount;
		if (0 < member.magicPoint)
			return;

		member.magicPoint = 0;
	}
}

/** enum StateType */
enum StateType {
	dead
}

/** enum DamageType */
enum DamageType {
	hitPoint, magicPoint
}

/** メンバークラス */
class Member {
	int hitPoint;
	int magicPoint;
	StateType state;

	Member(int hitPoint, int magicPoint) {
		this.hitPoint = hitPoint;
		this.magicPoint = magicPoint;
	}

	void addState(StateType stateType) {
		this.state = stateType;
	}
}

/** ダメージロジックを切り替えるクラス */
class ApplyDamageLogic {
	private Member member;
	private final Map<DamageType, Damage> damages;

	ApplyDamageLogic(Member member) {
		damages = new HashMap<>();
		this.member = member;
	}

	/** damagesのコレクションを生成するメソッド */
	void setDamages() {
		// 中略
		final HitPointDamage hitPointDamage = new HitPointDamage(member);
		final MagicPointDamage magicPointDamage = new MagicPointDamage(member);
		damages.put(DamageType.hitPoint, hitPointDamage);
		damages.put(DamageType.magicPoint, magicPointDamage);
	}

	/** damagesを実行するメソッド */
	void applyDamage(final DamageType damageType, final int damageAmount) {
		final Damage damage = damages.get(damageType);
		damage.execute(damageAmount);
	}
}

/** 実行用エントリポイント */
class Sample6_6 {
	public static void main(String[] args) {
		Member member = new Member(100, 20);
		ApplyDamageLogic applyDamageLogic = new ApplyDamageLogic(member);
		applyDamageLogic.setDamages();

		int damageAmount = 5;
		applyDamageLogic.applyDamage(DamageType.magicPoint, damageAmount);
		applyDamageLogic.applyDamage(DamageType.hitPoint, damageAmount);
		System.out.println("残りMP：" + member.magicPoint); // 残りMP：15
		System.out.println("残りHP：" + member.hitPoint); // 残りHP：95

		damageAmount = 95;
		applyDamageLogic.applyDamage(DamageType.hitPoint, damageAmount);
		System.out.println("プレーヤーステータス：" + member.state); // プレーヤーステータス：dead
	}
}
