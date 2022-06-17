/** enum StateType */
const STATETYPE = {
  dead: "dead"
}
Object.freeze(STATETYPE);

/** enum DamageType */
const DAMAGETYPE = {
  hitPoint: "hitPoint",
  magicPoint: "magicPoint"
}
Object.freeze(DAMAGETYPE);


/** インターフェイスチックなダメージクラス */
class Damage {
  execute() { };
}

/** HPダメージクラス */
class HitPointDamage extends Damage {
  /**
   * @constructor
   * @param{object} メンバー
   */
  constructor(member) {
    super();
    this.member = member;
  }

  /** ダメージを実行するメソッド */
  execute(damageAmount) {
    this.member.hitPoint -= damageAmount;
    if (0 < this.member.hitPoint) return;
    this.member.hitPoint = 0;
    this.member.addState(STATETYPE.dead);
  }
}

/** MPダメージクラス */
class MagicPointDamage extends Damage {
  /**
   * @constructor
   * @param{object} メンバー
   */
  constructor(member) {
    super();
    this.member = member;
  }

  /** ダメージを実行するメソッド */
  execute(damageAmount) {
    this.member.magicPoint -= damageAmount;
    if (0 < this.member.magicPoint) return;
    this.member.magicPoint = 0;
  }
}


/** メンバークラス */
class Member {
  /**
   * @constructor
   * @param{number} HP
   * @param{number} MP
   */
  constructor(hitPoint, magicPoint) {
    this.hitPoint = hitPoint;
    this.magicPoint = magicPoint;
    this.state;
  }

 /** ステータスを変更するメソッド */
  addState(stateType) {
    this.state = stateType;
  }
}

/** ダメージロジックを切り替えるクラス */
class ApplyDamageLogic {
  /**
   * @constructor
   * @param{object} メンバー
   */
  constructor(member) {
    this.damages = new Map();
    this.member = member;
  }

  /** damagesのコレクションを生成するメソッド */
  setDamages() {
    // 中略
    const hitPointDamage = new HitPointDamage(this.member);
    const magicPointDamage = new MagicPointDamage(this.member);
    this.damages.set(DAMAGETYPE.hitPoint, hitPointDamage);
    this.damages.set(DAMAGETYPE.magicPoint, magicPointDamage);
  }

  /** damagesを実行するメソッド */
  applyDamage(damageType, damageAmount) {
    this.damage = this.damages.get(damageType);
    this.damage.execute(damageAmount);
  }
}


/** 実行用関数 */
function myFunction6_7() {

  const member = new Member(100, 20);
  const applyDamageLogic = new ApplyDamageLogic(member);
  applyDamageLogic.setDamages();

  let damageAmount = 5;
  applyDamageLogic.applyDamage(DAMAGETYPE.magicPoint, damageAmount);
  applyDamageLogic.applyDamage(DAMAGETYPE.hitPoint, damageAmount);
  console.log("残りMP：" + member.magicPoint); // 残りMP：15
  console.log("残りHP：" + member.hitPoint); // 残りHP：95

  damageAmount = 95;
  applyDamageLogic.applyDamage(DAMAGETYPE.hitPoint, damageAmount);
  console.log("プレーヤーステータス：" + member.state); // プレーヤーステータス：dead
}
