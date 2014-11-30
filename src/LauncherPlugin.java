package com.inclusionhub.launcherplugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class LauncherPlugin extends CordovaPlugin {
	
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

		if (action.equals("getInstalledApps")) {

			PackageManager manager;
			List<AppDetail> apps; 
			manager = cordova.getActivity().getPackageManager();
			apps = new ArrayList<AppDetail>();
			Intent i = new Intent(Intent.ACTION_MAIN, null);
			i.addCategory(Intent.CATEGORY_LAUNCHER);

			List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
			for(ResolveInfo ri:availableActivities){
				AppDetail app = new AppDetail();
				app.label = ri.loadLabel(manager);
				app.name = ri.activityInfo.packageName;
				app.icon = ri.activityInfo.loadIcon(manager);
				apps.add(app);
                
			}
			JSONArray appsjson = new JSONArray(apps);
            callbackContext.success(appsjson);
			return true;
		}
		return false;


	}

}
