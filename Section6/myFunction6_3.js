/** @enum {string} */
const MAGICTYPE = {
  fire: "fire", // 炎の魔法
  hellFire: "hellFire"// 地獄の業火
};
Object.freeze(MAGICTYPE);

/** 魔法の条件分岐クラス */
class Magic {
  /**
    * @constructor
    * @param{object} MAGICTYPE - enum
    * @param{object} Member
    */
  constructor(magicType, member) {
    switch (magicType) {
      case "fire":
        this.name = "ファイヤ";
        this.costMagicPoint = 2;
        this.attackPower = 20 + (member.level * 0.5);
        break;
      case "hellFire":
        this.name = "地獄の業火";
        this.costMagicPoint = 16;
        this.attackPower = 200 + (member.magicAttack * 0.5) + (member.vitality * 2);
        break;
      default:
        throw new Error("該当する魔法がありません");
    }
  }
}


/** Memberクラス */
class Member {
  /** 
    * @constructor
    * @param{string} name
    * @param{number} level
    * @param{number} magicAttack
    * @param{number} vitality
    */
  constructor(name, level, magicAttack, vitality) {
    this.name = name;
    this.level = level;
    this.magicAttack = magicAttack;
    this.vitality = vitality;
  }
}


/** 実行用関数 */
function myFunction6_3() {

  const member = new Member("つじけ", 10, 4, 20);
  const magic = new Magic(MAGICTYPE.hellFire, member);

  console.log(member.name + "は" + magic.name + "をとなえた");
  console.log("モンスターに" + magic.attackPower + "のダメージをあたえた");
  console.log("MPを" + magic.costMagicPoint + "消費した");
  //	つじけは地獄の業火をとなえた
  //	モンスターに242のダメージをあたえた
  //	MPを16消費した
}
