package onestep.id.joints;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {
    private GridView gridView;
    private OverviewListAdapter adapter;
    private List<mOverview> mlist;

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        gridView = (GridView)view.findViewById(R.id.gridOverview);
        mlist = new ArrayList<>();

        mlist.add(new mOverview(1,R.drawable.rectangle,"banyuwasdasd"));
        mlist.add(new mOverview(2,R.drawable.rectangle,"banyuwasdasd"));
        mlist.add(new mOverview(3,R.drawable.rectangle,"banyuwasdasd"));
        mlist.add(new mOverview(4,R.drawable.rectangle,"banyuwasdasd"));
        mlist.add(new mOverview(5,R.drawable.rectangle,"banyuwasdasd"));

        adapter = new OverviewListAdapter(getActivity(), mlist);
        gridView.setAdapter(adapter);
        return view;
    }

}
