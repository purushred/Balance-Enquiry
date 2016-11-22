package com.smart.bank.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smart.bank.AppUtil;
import com.smart.bank.MainActivity;
import com.smart.bank.R;
import com.smart.bank.adapter.BankAdapter;
import com.smart.bank.vo.BankVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by purushoy on 11/21/2016.
 */

public class FavouritesFragment extends CustomFragment {

    private static final int MY_PERMISSIONS_REQUEST_CALL = 1;
    private BankAdapter mAdapter;
    private List<BankVO> bankList = new ArrayList<>();
    private static Map<String, Fragment> fragmentMap = new HashMap<>(2);
    private String type;
    private BankVO bankVO;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, null);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        linearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
        Button selectFavButton = (Button) rootView.findViewById(R.id.selectFavButton);
        selectFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setSelectedTab(1);
            }
        });

        mAdapter = new BankAdapter(bankList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        type = getArguments().getString("type");
        prepareBankData();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void prepareBankData() {
        bankList = AppUtil.prepareBankData();
        List<BankVO> tempList = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(AppUtil.BALANCECHECK_PREF, MainActivity.MODE_PRIVATE);
        if (type.equals("fav")) {
            for (BankVO vo : bankList) {
                if (prefs.getBoolean(vo.getMobileCode(), false)) {
                    tempList.add(vo);
                }
            }
            bankList = tempList;
            mAdapter.setData(bankList);

        } else {
            mAdapter.setData(bankList);
        }
        if (bankList.size() == 0) {
            linearLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        } else {
            linearLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }

    public static Fragment newInstance(String type) {
        Fragment fragment;
        if (fragmentMap.get(type) == null) {
            fragment = new FavouritesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            fragment.setArguments(bundle);
            fragmentMap.put(type, fragment);
        } else {
            fragment = fragmentMap.get(type);
        }
        return fragment;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Call Permission Granted in Fragment", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            showCallDialog(bankVO);
                        }
                    });
                } else {
                    Log.e("Request Grant", "Failed!!!!!!!!");
                }
        }
    }

    /**
     * This method is to check the app has required permissions to behave properly.
     */
    public void checkRuntimePermission(BankVO bankVO) {
        this.bankVO = bankVO;
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Display a dialog to show the reason for requesting the persmission.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.CALL_PHONE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Phone call permission is required to call bank number. Do you want to grant permission?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                        MY_PERMISSIONS_REQUEST_CALL);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                // No explanation needed, we can request the permission.
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL);
            }
        } else {
            // Condition checks if the permission already granted earlier.
            showCallDialog(bankVO);
        }
    }

    private void showCallDialog(BankVO bankVO) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment prevFragment = manager.findFragmentByTag("Dialog");
        FragmentTransaction ft = manager.beginTransaction();
        if (prevFragment != null) {
            ft.remove(prevFragment);
        }
        BalanceCheckDialogFragment fragment = new BalanceCheckDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bankVO", bankVO);
        fragment.setArguments(bundle);
        fragment.show(ft, "Dialog");

    }
}
