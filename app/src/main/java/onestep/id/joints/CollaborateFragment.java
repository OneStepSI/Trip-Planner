package onestep.id.joints;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollaborateFragment extends Fragment {
    private EditText nameCollab, emailCollab;
    private Button sendCollab, shareCollab;

    public CollaborateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collaborate, container, false);

        nameCollab = (EditText)view.findViewById(R.id.nameCollab);
        emailCollab = (EditText)view.findViewById(R.id.emailCollab);
        sendCollab = (Button)view.findViewById(R.id.sendCollab);
        shareCollab = (Button)view.findViewById(R.id.shareCollab);

        return view;
    }

}
