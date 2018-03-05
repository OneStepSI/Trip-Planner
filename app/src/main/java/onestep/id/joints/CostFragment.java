package onestep.id.joints;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CostFragment extends Fragment {
    private ExpandableListView listView;
    private ExpandableListAdapter adapter;
    private List<String> list;
    private HashMap<String, List<String>> listHashMap;
    private FloatingActionButton fab;

    public CostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cost, container, false);
        listView = (ExpandableListView) view.findViewById(R.id.list_cost);
        initData();
        adapter = new CostExpandableListAdapter(getActivity(), list, listHashMap);
        listView.setAdapter(adapter);
        fab = (FloatingActionButton)view.findViewById(R.id.fabCost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_cost, null);
                Button btn_cost_dialog = (Button)view.findViewById(R.id.buttonCostDialog);
                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        listHashMap = new HashMap<>();

        list.add("Transportaion");
        list.add("Food");
        list.add("Transportaion");
        list.add("Food");
        list.add("Transportaion");
        list.add("Food");
        list.add("Transportaion");
        list.add("Food");

        List<String> list1 = new ArrayList<>();
        list1.add("Pesawat");
        list1.add("Mobil");

        List<String> list2 = new ArrayList<>();
        list2.add("Nasi");

        listHashMap.put(list.get(0),list1);
        listHashMap.put(list.get(1),list2);
        listHashMap.put(list.get(2),list1);
        listHashMap.put(list.get(3),list2);
        listHashMap.put(list.get(4),list1);
        listHashMap.put(list.get(5),list2);
        listHashMap.put(list.get(6),list1);
        listHashMap.put(list.get(7),list2);
    }

}
