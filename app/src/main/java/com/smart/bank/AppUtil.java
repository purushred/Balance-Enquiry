package com.smart.bank;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by purushoy on 11/20/2016.
 */

public class AppUtil {
    private static int adCount = 0;
    public static String selectedUSSDCode = "";
    private static InterstitialAd mInterstitialAd = null;

    public static void initInterstitialAds(final Context context) {
        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(context);
            mInterstitialAd.setAdUnitId(context.getString(R.string.interAdUnitId));
            mInterstitialAd.setAdListener(new AdListener() {

                @Override
                public void onAdClosed() {
                    requestNewInterstitial();
                    invokeCall(context);
                }
            });
            requestNewInterstitial();
        }
        if (mInterstitialAd.isLoaded()) {
            if ((++adCount) == 3) {
                mInterstitialAd.show();
                adCount = 0;
            } else {
                invokeCall(context);
            }
        } else {
            ++adCount;
            invokeCall(context);
        }
    }

    private static void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private static void invokeCall(Context context) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + selectedUSSDCode + Uri.encode("#")));
        context.startActivity(phoneIntent);
    }

    public static void makeCall(Context context, String mobileCode) {
        selectedUSSDCode = mobileCode;
        initInterstitialAds(context);
    }
}
