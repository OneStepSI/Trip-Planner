package onestep.id.joints;

/**
 * Created by Brian R on 28/02/2018.
 */

public class mEquipment {
    private int id;
    private String equip;

    public mEquipment(int id, String equip) {
        this.id = id;
        this.equip = equip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }
}
