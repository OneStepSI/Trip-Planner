package onestep.id.joints;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {
    private GridView gridView;
    private OverviewListAdapter adapter;
    private List<mOverview> mlist;
    private ImageView imageView;

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        gridView = (GridView)view.findViewById(R.id.gridOverview);
        imageView = (ImageView)view.findViewById(R.id.imageOverview1);
        mlist = new ArrayList<>();

        mlist.add(new mOverview(1,R.mipmap.gambar2,"Borobudur"));
        mlist.add(new mOverview(2,R.mipmap.gambar3,"Labuhan Bajo"));
        mlist.add(new mOverview(3,R.mipmap.gambar4,"Kaliurang"));
        mlist.add(new mOverview(4,R.mipmap.gambar5,"Wisata Alam"));
        mlist.add(new mOverview(5,R.mipmap.gambar6,"Garut"));

        adapter = new OverviewListAdapter(getActivity(), mlist);
        gridView.setAdapter(adapter);
        return view;
    }

}
