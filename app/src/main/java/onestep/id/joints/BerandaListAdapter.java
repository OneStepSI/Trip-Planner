package onestep.id.joints;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Brian R on 27/02/2018.
 */

public class BerandaListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<mBeranda> list;

    public BerandaListAdapter(Context context, List<mBeranda> list) {
        this.context = context;
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
        View view = View.inflate(context,R.layout.list_beranda,null);
        TextView destination = (TextView)view.findViewById(R.id.destiantion);

        mBeranda m = list.get(position);
        destination.setText(m.getDestination());
        view.setTag(m.getId());
        return view;
    }
}
