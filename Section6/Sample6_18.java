/** enum */
enum MagicType {
	fire, // 炎の魔法
	hellFire; // 地獄の業火
}

/** Magicクラス */
class Magic {
	final String name;
	final int costMagicPoint;
	final int attackPower;

	Magic(final MagicType magicType, final Member member) {
		switch (magicType) {
		case fire:
			name = "ファイヤ";
			costMagicPoint = 2;
			attackPower = 20 + (int) (member.level * 0.5);
			break;
		case hellFire:
			name = "地獄の業火";
			costMagicPoint = 16;
			attackPower = 200 + (int) (member.magicAttack * 0.5 + member.vitality * 2);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
}

/** Memberクラス */
class Member {
	String name;
	int level;
	int magicAttack;
	int vitality;

	Member(String name, int level, int magicAttack, int vitality) {
		this.name = name;
		this.level = level;
		this.magicAttack = magicAttack;
		this.vitality = vitality;
	}
}

/** 実行用 エントリポイント */
class Sample6_18 {
	public static void main(String[] args) {
		Member member = new Member("つじけ", 10, 4, 20);
		Magic magic = new Magic(MagicType.hellFire, member);

		System.out.println(member.name + "は" + magic.name + "をとなえた");
		System.out.println("モンスターに" + magic.attackPower + "のダメージをあたえた");
		System.out.println("MPを" + magic.costMagicPoint + "消費した");
    //つじけは地獄の業火をとなえた, モンスターに242のダメージをあたえた, MPを16消費した
	}
}
