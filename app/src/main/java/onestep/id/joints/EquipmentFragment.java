package onestep.id.joints;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquipmentFragment extends Fragment {
    private GridView gridView;
    private EquipmentGridAdapter adapter;
    private List<mEquipment> mEquipments;
    private FloatingActionButton fab;

    public EquipmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        gridView = (GridView) view.findViewById(R.id.gridEquipment);
        mEquipments = new ArrayList<>();

        mEquipments.add(new mEquipment(1,"Jember"));
        mEquipments.add(new mEquipment(2,"Banyuwangi"));
        mEquipments.add(new mEquipment(3,"Jember"));
        mEquipments.add(new mEquipment(4,"Banyuwangi"));
        mEquipments.add(new mEquipment(5,"Jember"));


        adapter = new EquipmentGridAdapter(getActivity(), mEquipments);
        gridView.setAdapter(adapter);
        fab = (FloatingActionButton)view.findViewById(R.id.fabCost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_equipment, null);
                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }

}
