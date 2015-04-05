package com.breakpoint.americano.app.nfc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class NfcTagAdapter extends BroadcastReceiver {

	private static final String		TAG	= NfcTagAdapter.class.getSimpleName();
	private static NfcTagAdapter	mNfcTagAdapter;

	NfcDataModel					mNfcDataModel;
	Handler							mHandler;

	public NfcTagAdapter(Handler handler) {
		mNfcTagAdapter = this;
		mNfcDataModel = new NfcDataModel();
		mHandler = handler;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		String name = intent.getAction();

		Log.i(TAG, "onreceive name = " + name);

		if (name.equals("com.breakpoint.americano.app.nfc.test")) {

			String tt = intent.getStringExtra("number");
			Log.i(TAG, "tt = " + tt);
			int ret = Integer.parseInt(tt);
			Log.i(TAG, "ret = " + ret);

			if (mNfcTagAdapter != null) {

				mNfcTagAdapter.mNfcDataModel.setNumber(ret);
				mNfcTagAdapter.mHandler.sendEmptyMessage(ret);
			}
		}
	}

}
