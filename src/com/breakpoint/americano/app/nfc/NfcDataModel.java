package com.breakpoint.americano.app.nfc;

import android.util.Log;

public class NfcDataModel {

	private static final String	TAG	= NfcDataModel.class.getSimpleName();
	private int					mNumber;

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		Log.i(TAG, "setNumber " + this.mNumber + " -> " + mNumber);
		this.mNumber = mNumber;
	}

}
