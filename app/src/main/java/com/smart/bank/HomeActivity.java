package com.smart.bank;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.smart.bank.vo.UssdCodes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.accountInfoButton)
    Button accountInfoButton;

    @BindView(R.id.balanceCheckButton)
    Button balanceCheckButton;

    @BindView(R.id.sendMoneyButton)
    Button sendMoneyButton;

    @BindView(R.id.requestMoneyButton)
    Button receiveMoneyButton;

    private static final int MY_PERMISSIONS_REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        accountInfoButton.setOnClickListener(this);
        sendMoneyButton.setOnClickListener(this);
        receiveMoneyButton.setOnClickListener(this);
        balanceCheckButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        checkRuntimePermission(view.getId());
    }

    private void getAccountInfo() {
        AppUtil.makeCall(this, UssdCodes.ACCOUNT_INFO);
    }

    private void checkAccountBalance() {
        AppUtil.makeCall(this, UssdCodes.BALANCE_ENQUIRY);
    }

    private void sendMoney() {
        AppUtil.makeCall(this, UssdCodes.SEND_MONEY);
    }

    private void requestMoney() {
        AppUtil.makeCall(this, UssdCodes.REQUEST_MONEY);
    }

    /**
     * This method is to check the app has required permissions to behave properly.
     *
     * @param viewId
     */
    public void checkRuntimePermission(int viewId) {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Display a dialog to show the reason for requesting the persmission.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.CALL_PHONE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Phone call permission is required fetch your a/c information. Do you want to grant permission?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{android.Manifest.permission.CALL_PHONE},
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
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL);
            }
        } else {
            switch (viewId) {
                case R.id.accountInfoButton:
                    getAccountInfo();
                    break;
                case R.id.sendMoneyButton:
                    sendMoney();
                    break;
                case R.id.requestMoneyButton:
                    requestMoney();
                    break;
                case R.id.balanceCheckButton:
                    checkAccountBalance();
                    break;
            }
        }
    }

    /*private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String ussdResponseData = intent.getStringExtra(AppUtil.USSD_RESPONSE_DATA);
            Log.e("USSD Response ->", ussdResponseData);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(AppUtil.USSD_RESPONSE_INTENT));
    }*/
}
