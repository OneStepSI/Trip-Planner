package onestep.id.joints;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Brian R on 27/02/2018.
 */

public class CostExpandableListAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    private List<String>list;
    private HashMap<String, List<String>> listHashMap;

    public CostExpandableListAdapter(Activity activity, List<String> list, HashMap<String, List<String>> listHashMap) {
        this.activity = activity;
        this.list = list;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(list.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String titleGroup = (String)getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_cost_group, null);
        }
        TextView keperluan = (TextView)convertView.findViewById(R.id.keperluanCostGroup);
        TextView danaG = (TextView)convertView.findViewById(R.id.danaCostGroup);
        keperluan.setText(titleGroup);
        danaG.setText("1000000");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String titleItem = (String)getChild(groupPosition,childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_cost_item, null);
        }
        TextView details = (TextView)convertView.findViewById(R.id.detailCostItem);
        TextView dana = (TextView)convertView.findViewById(R.id.danaCostItem);
        details.setText(titleItem);
        dana.setText("50000");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
