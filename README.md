# updater
Offer update info when start up service, in case of server dying

#### How to get json
```
Rest url (using GET method) :
https://github.com/seose/updater/blob/master/[json file name with .json]
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
