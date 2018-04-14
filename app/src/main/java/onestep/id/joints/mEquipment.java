package onestep.id.joints;

/**
 * Created by Brian R on 28/02/2018.
 */

public class mEquipment {
    private int id;
    private String equip;

    public String getPembawa() {
        return pembawa;
    }

    public void setPembawa(String pembawa) {
        this.pembawa = pembawa;
    }

    private String pembawa;

    public mEquipment(int id, String equip) {
        this.id = id;
        this.equip = equip;
    }
    public mEquipment(){}

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
