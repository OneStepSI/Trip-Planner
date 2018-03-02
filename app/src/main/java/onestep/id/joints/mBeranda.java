package onestep.id.joints;

/**
 * Created by Brian R on 27/02/2018.
 */

public class mBeranda {
    private int id;
    private String destination;

    public mBeranda(int id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
