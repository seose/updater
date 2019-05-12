# updater
Offer update info when start up service, in case of server dying

#### How to get json
```
Rest url (using GET method) :
https://raw.githubusercontent.com/seose/updater/master/[json file name with .json]
```

#### How to use ( Android, Kotlin lang)
adjust here : https://github.com/seose/updater/blob/master/Updater.kt
```
1. Need to permissions in Manifest.xml
 <uses-permission android:name="android.permission.INTERNET"/>
2. Need to that dependencies in build.gradle
 implementation 'com.squareup.retrofit2:retrofit:2.4.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
 implementation 'com.squareup.okhttp3:okhttp:3.9.1'
```

#### Explain json
if version is less than [version]
 1. show versionMsg (pass step when this value is null)
 2. versionMarket
 3. versionRun
```
{
  "version" : 1,
  "versionMsg" : "if you want to hide msg, set this'key 's value is null",
  "versionMarket" : false,
  "versionRun" : true,

  "run" : true,
  "msg" : "if you want to hide msg, set this'key 's value is null"
}
```
