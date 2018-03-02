package onestep.id.joints;

/**
 * Created by Brian R on 02/03/2018.
 */

public class mOverview {
    private int id,imageOverview;
    private String titleOverview;

    public mOverview(int id, int imageOverview, String titleOverview) {
        this.id = id;
        this.imageOverview = imageOverview;
        this.titleOverview = titleOverview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageOverview() {
        return imageOverview;
    }

    public void setImageOverview(int imageOverview) {
        this.imageOverview = imageOverview;
    }

    public String getTitleOverview() {
        return titleOverview;
    }

    public void setTitleOverview(String titleOverview) {
        this.titleOverview = titleOverview;
    }
}
