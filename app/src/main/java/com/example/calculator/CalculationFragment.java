package com.example.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculationFragment extends Fragment {

    MyViewModel viewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculationFragment newInstance(String param1, String param2) {
        CalculationFragment fragment = new CalculationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculation, container, false);
    }

    TextView tvCalc,tvRes,tvHint;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCalc = getActivity().findViewById(R.id.tv_calculations);
        tvRes = getActivity().findViewById(R.id.tv_results);
        tvHint = getActivity().findViewById(R.id.tv_hints);

        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        viewModel.getmessege().observe(getViewLifecycleOwner(), item -> {
            tvCalc.setText(viewModel.getmessege().getValue().get(0));
            tvHint.setText(viewModel.getmessege().getValue().get(1));
            tvRes.setText(viewModel.getmessege().getValue().get(2));
            Log.d("Calc_tag","From frag : the live Numbers =====  "+ viewModel.getmessege().getValue());
        });

    }
}