/** MagicSwitchクラス */
class MagicSwitch {
	private Member member;

	MagicSwitch(Member member) {
		this.member = member;
	}

  //魔法を判定し、処理を切り替えるメソッド
	void magicAttack(MagicType magicType) {
		switch (magicType) {
		case fire:
			Magic magic = new Fire(member);
			showMagicName(magic);
			consumeMagicPoint(magic);
			consumeTechnicalPoint(magic);
			magicDamage(magic);
			break;
		case shiden:
			magic = new Shiden(member);
			showMagicName(magic);
			consumeMagicPoint(magic);
			consumeTechnicalPoint(magic);
			magicDamage(magic);
			break;
		case hellFire:
			magic = new HellFire(member);
			showMagicName(magic);
			consumeMagicPoint(magic);
			consumeTechnicalPoint(magic);
			magicDamage(magic);
			break;
		}
	}

	// 魔法の名前を画面表示する
	void showMagicName(final Magic magic) {
    //中略
	}

	// 魔法を消費する
	void consumeMagicPoint(final Magic magic) {
    //中略
	}

	// テクニカルポイントを消費する
	void consumeTechnicalPoint(final Magic magic) {
    //中略
	}

	// ダメージ計算する
	void magicDamage(final Magic magic) {
   //中略
	}
}

/** 実行用エントリポイント */ 
class Sample6_32_1 {
	public static void main(String[] args) {
		Member member = new Member(100, 100, 10, 4, 5, 20);
		MagicSwitch magicSwitch = new MagicSwitch(member);

    //ファイヤの攻撃
		magicSwitch.magicAttack(MagicType.fire);
		// ファイヤを使った, MPが2減った, テクニカルポイントが0減った, モンスターに25のダメージを与えた

    //紫電の攻撃
		magicSwitch.magicAttack(MagicType.shiden);
		// 紫電を使った, MPが7減った, テクニカルポイントが5減った, モンスターに56のダメージを与えた

    //地獄の業火の攻撃
		magicSwitch.magicAttack(MagicType.hellFire);
		// 地獄の業火を使った, MPが16減った, テクニカルポイントが24減った, モンスターに242のダメージを与えた
	}
}
