package com.inmobiAds.inmobiads

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.inmobi.sdk.InMobiSdk
import com.inmobi.sdk.SdkInitializationListener
import com.inmobiAds.inmobiads.BannerAds
import com.inmobiAds.inmobiads.R
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

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
        btn.setOnClickListener {
            val intent = Intent(this, BannerAds::class.java)
            startActivity(intent)
        }

//        btn1.setOnClickListener{
//            val intent = Intent(this, InterAds::class.java)
//            startActivity(intent)
//        }
    }
}
