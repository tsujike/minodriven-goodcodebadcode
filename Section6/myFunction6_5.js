/** インターフェイスチックな魔法クラス */
class Magic {
  name() { } // 名前
  costMagicPoint() { } // 消費魔法力
  attackPower() { } // 攻撃力
  costTechnicalPoint() { } // 消費テクニカルポイント
}

/** 
 * ファイヤクラス
 * @extends
 */
class Fire extends Magic {
  /**
    * @constructor
    * @param{object} メンバー
    */
  constructor(member) {
    super();
    this.member = member;
  }
  /** 名前を返すメソッド */
  name() {
    return "ファイヤ";
  }
  /** 消費魔法力を返すメソッド */
  costMagicPoint() {
    return 2;
  }
  /** 攻撃力を返すメソッド */
  attackPower() {
    return 20 + this.member.level * 0.5;
  }
  /** 消費テクニカルポイントを返すメソッド */
  costTechnicalPoint() {
    return 0;
  }

}

/** 
 * 紫電クラス
 * @extends
 */
class Shiden extends Magic {
  /**
  * @constructor
  * @param{object} メンバー
  */
  constructor(member) {
    super();
    this.member = member;
  }
  /** 名前を返すメソッド */
  name() {
    return "紫電";
  }
  /** 消費魔法力を返すメソッド */
  costMagicPoint() {
    return 5 + (this.member.level * 0.2);
  }
  /** 攻撃力を返すメソッド */
  attackPower() {
    return 50 + (this.member.agility * 1.5);
  }
  /** 消費テクニカルポイントを返すメソッド */
  costTechnicalPoint() {
    return 5;
  }

}

/** 
 * 地獄の業火クラス
 * @extends
 */
class HellFire extends Magic {
  /**
  * @constructor
  * @param{object} メンバー
  */
  constructor(member) {
    super();
    this.member = member;
  }
  /** 名前を返すメソッド */
  name() {
    return "地獄の業火";
  }
  /** 消費魔法力を返すメソッド */
  costMagicPoint() {
    return 16;
  }
  /** 攻撃力を返すメソッド */
  attackPower() {
    return 200 + (this.member.magicAttack * 0.5 + this.member.vitality * 2);
  }
  /** 消費テクニカルポイントを返すメソッド */
  costTechnicalPoint() {
    return 20 + (this.member.level * 0.4);
  }

}

/** メンバークラス */
class Member {
  /**
    * @constructor
    * @param{number} width
    * @param{number} height
    */
  constructor(hitPoint, maxHitpoint, level, agility, magicAttack, vitality) {
    this.hitPoint = hitPoint;
    this.maxHitPoint = maxHitpoint;
    this.level = level;
    this.agility = agility;
    this.magicAttack = magicAttack;
    this.vitality = vitality;
  }
}

/** @enum {string} */
const MAGICTYPE = {
  fire: "fire",
  shiden: "shiden",
  hellFire: "hellFire"
};

Object.freeze(MAGICTYPE);

/** 魔法切替クラス */
class MagicSwitch {
  /**
    * @constructor
    * @param{number} width
    * @param{number} height
    */
  constructor(member) {
    this.magics = new Map();
    this.member = member;
  }
  /** Mapオブジェクトに値をセットするメソッド */
  setMapElement() {
    const fire = new Fire(this.member);
    const shiden = new Shiden(this.member);
    const hellFire = new HellFire(this.member);

    this.magics.set(MAGICTYPE.fire, fire);
    this.magics.set(MAGICTYPE.shiden, shiden);
    this.magics.set(MAGICTYPE.hellFire, hellFire);
  }

  /** 魔法攻撃を実行するメソッド */
  magicAttack(MAGICTYPE) {
    const usingMagic = this.magics.get(MAGICTYPE);
    this.showMagicName(usingMagic);
    this.consumeMagicPoint(usingMagic);
    this.consumeTechnicalPoint(usingMagic);
    this.magicDamage(usingMagic);

  }

  /** 魔法の名前を画面表示するメソッド */
  showMagicName(magic) {
    const name = magic.name();
    // nameを使った表示処理
    console.log(name + "を使った");
  }

  /** 魔法を消費するメソッド */
  consumeMagicPoint(magic) {
    const costMagicPoint = magic.costMagicPoint();
    // costMagicPointを使った魔法力消費処理
    console.log("MPが" + costMagicPoint.mp + "減った");
  }

  /** テクニカルポイントを消費するメソッド */
  consumeTechnicalPoint(magic) {
    const costTechnicalPoint = magic.costTechnicalPoint();
    // consumeTechnicalPointを使ったテクニカルポイント消費処理
    console.log("テクニカルポイントが" + costTechnicalPoint.tp + "減った");
  }

  /** ダメージ計算するメソッド */
  magicDamage(magic) {
    const attackPower = magic.attackPower();
    // attackPowerを使ったダメージ処理
    console.log("モンスターに" + attackPower.power + "のダメージを与えた");
  }
}

/** 実行用関数 */
function myFunction6_5() {
  const member = new Member(100, 100, 10, 4, 5, 20);
  const magicSwitch = new MagicSwitch(member);

  // Mapを生成する
  magicSwitch.setMapElement();

  // 地獄の業火の攻撃
  magicSwitch.magicAttack(MAGICTYPE.hellFire);
  //地獄の業火を使った, MPが16減った, テクニカルポイントが24減った, モンスターに242.5のダメージを与えた
}
