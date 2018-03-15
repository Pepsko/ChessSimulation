package brightone.pl.zadanie.nodes;

/**
 * Created by Lenovo on 2018-03-11.
 */
public class AttackInfo {
    Field attacker;
    Field victim;

    public AttackInfo(Field attacker, Field victim) {
        this.attacker = attacker;
        this.victim = victim;
    }

    public Field getAttacker() {
        return attacker;
    }

    public void setAttacker(Field attacker) {
        this.attacker = attacker;
    }

    public Field getVictim() {
        return victim;
    }

    public void setVictim(Field victim) {
        this.victim = victim;
    }
}
