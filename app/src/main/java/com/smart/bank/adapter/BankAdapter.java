package com.smart.bank.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.bank.AppUtil;
import com.smart.bank.R;
import com.smart.bank.fragment.FavouritesFragment;
import com.smart.bank.vo.BankVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by purushoy on 11/20/2016.
 */

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.MyViewHolder> {

    private final FavouritesFragment fragment;
    private List<BankVO> banksList;

    public void setData(List<BankVO> data) {
        this.banksList = data;
    }

    public BankAdapter(List<BankVO> banksList, FavouritesFragment fragment) {
        this.banksList = banksList;
        this.fragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final BankVO bankVO = banksList.get(position);
        holder.bankName.setText(bankVO.getName());
        try {
            InputStream ims = fragment.getActivity().getAssets().open(bankVO.getBankLogo());
            holder.bankLogo.setImageDrawable(Drawable.createFromStream(ims, null));
            bankVO.setFavourite(getFromCache(bankVO.getMobileCode()));
            if (bankVO.isFavourite()) {
                holder.bankFavIcon.setImageResource(R.drawable.ic_favorite_black_24dp);
            } else {
                holder.bankFavIcon.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
            holder.bankFavIcon.setTag(holder.bankFavIcon.getId(), bankVO);
            holder.relativeLayout.setTag(holder.relativeLayout.getId(), bankVO);

            holder.bankFavIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BankVO vo = (BankVO) view.getTag(view.getId());
                    if (vo.isFavourite()) {
                        holder.bankFavIcon.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        banksList.remove(vo);
                    } else {
                        holder.bankFavIcon.setImageResource(R.drawable.ic_favorite_black_24dp);
                    }
                    vo.setFavourite(!vo.isFavourite());
                    updateCache(vo.getMobileCode(), vo.isFavourite());
                }
            });
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleBankClick(view);
                }
            });
        } catch (IOException ex) {
            Log.e("Image Exception", bankVO.getName() + ex.toString());
        }
    }

    private void handleBankClick(View view) {
        fragment.checkRuntimePermission((BankVO) view.getTag(view.getId()));
    }

    private boolean getFromCache(String key) {
        SharedPreferences prefs = fragment.getActivity().getSharedPreferences(AppUtil.BALANCECHECK_PREF, Activity.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    private void updateCache(String key, boolean value) {
        SharedPreferences prefs = fragment.getActivity().getSharedPreferences(AppUtil.BALANCECHECK_PREF, Activity.MODE_PRIVATE);
        prefs.edit().putBoolean(key, value).apply();
    }

    @Override
    public int getItemCount() {
        return banksList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bankName;
        ImageView bankLogo;
        ImageView bankFavIcon;
        RelativeLayout relativeLayout;

        MyViewHolder(View view) {
            super(view);
            bankName = (TextView) view.findViewById(R.id.bankNameView);
            bankLogo = (ImageView) view.findViewById(R.id.bankLogoView);
            bankFavIcon = (ImageView) view.findViewById(R.id.bankFavIcon);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        }
    }
}