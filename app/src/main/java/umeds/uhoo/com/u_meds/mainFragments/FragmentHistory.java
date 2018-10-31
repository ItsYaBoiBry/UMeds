package umeds.uhoo.com.u_meds.mainFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import umeds.uhoo.com.u_meds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHistory extends Fragment {


    public FragmentHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_history, container, false);
    }

}
