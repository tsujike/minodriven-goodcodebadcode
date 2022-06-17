/** MagicSwitchクラス */
class MagicSwitch {
//中略

final Map<MagicType, Magic> magics = new HashMap<>();

// Mapを生成する
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
		usingMagic.attackPower();
	}

//中略

}

class Sample6_32_2 {
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
