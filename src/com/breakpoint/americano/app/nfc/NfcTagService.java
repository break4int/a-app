package com.breakpoint.americano.app.nfc;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

public class NfcTagService extends HostApduService {

	private static final String	TAG				= NfcTagService.class.getSimpleName();

	public static final String	AUTHORITY		= "americano.ticket.nfc.contentproviderdata";
	public static final String	PATH_GET		= "AUTH_GET";
	public static final String	PATH_UPDATE		= "/AUTH_UPDATE";
	byte[]						tmpTest			= new byte[4];
	public static final Uri		CONTENT_URI		= Uri.parse("content://" + AUTHORITY + PATH_GET);
	public static final Uri		CONTENT_URI2	= Uri.parse("content://" + AUTHORITY + PATH_UPDATE);

	@Override
	public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {

		Log.i(TAG, "apdu size : " + commandApdu.length);

		byte[] ret = new byte[] { 'O', 'K', 0 };

		for (int i = 0, iMax = commandApdu.length; i < iMax; i++) {
			Log.i(TAG, "apdu data[" + i + "] = " + commandApdu[i]);
		}

		String returnRet = "false";

		if (commandApdu.length < 10) {

			Log.i(TAG, "apdu.length = " + commandApdu.length);
			String retNumber = new String(commandApdu);
			returnRet = "true";
			Log.i(TAG, "apdu retNumber = " + retNumber);

			Intent in = new Intent("com.breakpoint.americano.app.nfc.test");
			in.putExtra("number", retNumber);
			getApplicationContext().sendBroadcast(in);
		}

		return returnRet.getBytes();
	}

	@Override
	public void onDeactivated(int reason) {

	}

	public boolean isPackageInstalled(String pkgName) {

		try {

			getApplicationContext().getPackageManager().getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES);
		} catch (NameNotFoundException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean isRunningProcess(String pkgName) {

		boolean isRunning = false;

		ActivityManager actManager = (ActivityManager) getApplicationContext().getSystemService(
				Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> list = actManager.getRunningAppProcesses();

		for (RunningAppProcessInfo rap : list) {
			if (rap.processName.equals(pkgName)) {
				isRunning = true;
				break;
			}
		}

		return isRunning;
	}
}
