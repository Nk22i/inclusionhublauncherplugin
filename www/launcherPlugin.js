var launcherPlugin = {
	getInstalledApps: function(successCallback, errorCallback) {
        console.log("se llamo a get Installed Apps");
		cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'LauncherPlugin', // mapped to our native Java class called "Calendar"
            'getInstalledApps', // with this action name
            [{                  // and this array of custom arguments to create our entry
            	"!"
            }]
        ); 
	}
}

module.exports = launcherPlugin;