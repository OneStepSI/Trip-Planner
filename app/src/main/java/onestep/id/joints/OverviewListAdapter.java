package onestep.id.joints;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Brian R on 02/03/2018.
 */

public class OverviewListAdapter extends BaseAdapter {
    private Activity activity;
    private Inflater inflater;
    private List<mOverview> list;

    public OverviewListAdapter(Activity activity, List<mOverview> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.grid_overview, null);
        ImageView imageOverview = (ImageView)view.findViewById(R.id.imageOverview);
        TextView titleOverview = (TextView)view.findViewById(R.id.titleOverview);

        mOverview m = list.get(position);
        imageOverview.setImageResource(m.getImageOverview());
        titleOverview.setText(m.getTitleOverview());
        view.setTag(m.getId());
        return view;
    }
}
