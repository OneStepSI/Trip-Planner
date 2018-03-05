package onestep.id.joints;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {
    private ListView listView;
    private BerandaListAdapter adapter;
    private List<mBeranda> mBerandas;
    private FloatingActionButton fab;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        listView = (ListView) view.findViewById(R.id.list_beranda);
        mBerandas = new ArrayList<>();

        mBerandas.add(new mBeranda(1,"Jember"));

        adapter = new BerandaListAdapter(getActivity(), mBerandas);
        listView.setAdapter(adapter);

        fab = (FloatingActionButton)view.findViewById(R.id.fabBeranda);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CreateTrip.class);
                startActivity(i);
            }
        });
        return view;
    }

}
