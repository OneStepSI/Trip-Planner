package onestep.id.joints;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Brian R on 28/02/2018.
 */

public class EquipmentGridAdapter extends BaseAdapter {
    private Activity activity;
    private Inflater inflater;
    private List<mEquipment> list;

    public EquipmentGridAdapter(Activity activity, List<mEquipment> list) {
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
        View view = View.inflate(activity, R.layout.grid_equipment, null);
        mEquipment m = list.get(position);
        TextView titleEquip = (TextView)view.findViewById(R.id.titleEquip);
        titleEquip.setText(m.getEquip());
        view.setTag(m.getId());
        return view;
    }
}
