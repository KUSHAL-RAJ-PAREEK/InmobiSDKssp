package com.inmobiAds.inmobiads

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.inmobi.ads.AdMetaInfo
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiInterstitial
import com.inmobi.ads.listeners.InterstitialAdEventListener
import com.inmobi.sdk.InMobiSdk
import com.inmobi.sdk.SdkInitializationListener
import com.inmobiAds.inmobiads.BannerAds
import com.inmobiAds.inmobiads.R
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var interstitialAd: InMobiInterstitial? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SDK initialize
        val consentObject = JSONObject()
        try {
            consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, true)
            consentObject.put(InMobiSdk.IM_GDPR_CONSENT_IAB, "1")
            // Set user's GDPR consent status accordingly
            consentObject.put("gdpr", "1")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        InMobiSdk.init(
            this,
            "1e1d03f3f9ce47a3afe48f906e76ad0e",
            consentObject,
            SdkInitializationListener { error ->
                if (error != null) {
                    Log.e("tagok", "InMobi Init failed - " + error.message.toString())
                } else {
                    Log.d("tagok", "InMobi Init Successful")
                }
            }
        )
        InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG)

        val btn = findViewById<Button>(R.id.btn)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        btn.setOnClickListener {
            val intent = Intent(this, BannerAds::class.java)
            startActivity(intent)
        }
        btn1.setOnClickListener {
            // Initialize InMobiInterstitial if not already initialized
            if (interstitialAd == null) {
                interstitialAd = InMobiInterstitial(this@MainActivity, 1700682378384, CustomInterstitialAdEventListener())
            }

            // Load the interstitial ad
            interstitialAd?.load()

            // Show the ad only if it is in a ready state
            if (interstitialAd?.isReady == true) {
                interstitialAd?.show()
            }
        }
        btn2.setOnClickListener{
            NativeAdsActivity()
//            val intent = Intent(this, NativeAdsActivity::class.java)
//            startActivity(intent)
        }

    }
}



    class CustomInterstitialAdEventListener : InterstitialAdEventListener() {

    override fun onAdFetchFailed(ad: InMobiInterstitial, status: InMobiAdRequestStatus){
            Log.d("InterAdTAG","Fetch Failed!");Log.d("InterAdTAG","Fetch Failed!");
    }

    override fun onAdWillDisplay(ad: InMobiInterstitial) {
        Log.d("InterAdTAG","on will Displayed!");
    }

    override fun onAdDisplayed(ad: InMobiInterstitial, info: AdMetaInfo) {
        Log.d("InterAdTAG","on Displayed!");
    }

    override fun onAdDisplayFailed(ad: InMobiInterstitial) {
        Log.d("InterAdTAG","On Display Failed!");
    }

    override fun onAdDismissed(ad: InMobiInterstitial) {
        Log.d("InterAdTAG","Ad Dismissed!");
    }

    override fun onUserLeftApplication(ad: InMobiInterstitial) {
        Log.d("InterAdTAG","User Left Application!");
    }

    override fun onAdImpression(ad: InMobiInterstitial) {
        Log.d("InterAdTAG","Ad Impression!");
    }


}

