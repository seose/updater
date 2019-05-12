/**
 * 1. Need to permissions in Manifest.xml
 *     <uses-permission android:name="android.permission.INTERNET"/>
 *
 *
 * 2. Need to that dependencies in build.gradle
 *
 * implementation 'com.squareup.retrofit2:retrofit:2.4.0'
 * implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
 * implementation 'com.squareup.okhttp3:okhttp:3.9.1'
 *
 */
class Updater(val activity: Activity){

    companion object {

        /**
         * Need to update [ THREE LINE ]
         */
        private const val endPoint = "laqStore.json"
        private const val pkgNameForPlayStore = "seoft.co.kr.laq_store"
        private val starterActivity = MainActivity::class.java

    }
    private val updaterUrl = "https://raw.githubusercontent.com/seose/updater/master/"
    private val playStoreUrl = "https://play.google.com/store/apps/details?id=$pkgNameForPlayStore"

    fun proc(){
        val apiService = Retrofit.Builder().baseUrl(updaterUrl).build().create(UpdaterService::class.java)
        apiService.getUpdater().enqueue(object: Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.body() == null) return
                val updaterDto = Gson().fromJson(response.body()!!.string(), UpdaterDTO::class.java)
                with(updaterDto){
                    if(msg != null) Toast.makeText(activity,msg,Toast.LENGTH_LONG).show()
                    if(!run) {
                        activity.finish()
                        return
                    }

                    if(BuildConfig.VERSION_CODE < version ) {
                        if(versionMsg != null) Toast.makeText(activity,versionMsg,Toast.LENGTH_LONG).show()
                        if(versionRun) runApp() else activity.finish()
                        if(versionMarket) {
                            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(playStoreUrl)))
                            activity.finish()
                        }
                    } else {
                        if(run) runApp()
                    }
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(activity,"internet error",Toast.LENGTH_LONG).show()
                activity.finish()
            }
        })
    }

    private fun runApp(){
        activity.startActivity(Intent(activity.applicationContext, starterActivity))
        activity.finish()
    }

    private interface UpdaterService{
        @GET(endPoint)
        fun getUpdater() : Call<ResponseBody>

    }

    private data class UpdaterDTO(
        val version : Int,
        val versionMsg : String?,
        val versionMarket : Boolean,
        val versionRun : Boolean,
        val run : Boolean,
        val msg : String?
    )
}