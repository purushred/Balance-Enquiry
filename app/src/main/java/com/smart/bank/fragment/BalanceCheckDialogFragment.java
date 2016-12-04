package com.smart.bank.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.bank.R;
import com.smart.bank.vo.BankVO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Purushotham on 09-09-2014.
 */
public class BalanceCheckDialogFragment extends DialogFragment implements View.OnClickListener {

    private Button closeButton;
    private Button balanceCheckButton;
    private BankVO bankVO;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.call_dialog, null);
        bankVO = (BankVO) getArguments().getSerializable("bankVO");
        getDialog().setTitle(bankVO.getName());

        ImageView bankLogo = (ImageView) rootView.findViewById(R.id.imageView);
        TextView bankNameView = (TextView) rootView.findViewById(R.id.bankNameView);
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getAssets().open(bankVO.getBankLogo());
        } catch (IOException e) {
            Log.e("Exception ", e.toString());
        }
        bankLogo.setImageDrawable(Drawable.createFromStream(inputStream, null));
        bankNameView.setText(bankVO.getName());
        balanceCheckButton = (Button) rootView.findViewById(R.id.balanceCheckButton);
        closeButton = (Button) rootView.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(this);
        balanceCheckButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == closeButton.getId()) {
            getDialog().dismiss();
        } else if (v.getId() == balanceCheckButton.getId()) {
            makeCall();
        }
    }

    private void makeCall() {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + bankVO.getMobileCode() + Uri.encode("#")));
        startActivity(phoneIntent);
    }
}