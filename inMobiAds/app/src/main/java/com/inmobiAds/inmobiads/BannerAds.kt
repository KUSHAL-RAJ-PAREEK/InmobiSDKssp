package com.inmobiAds.inmobiads

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiBanner
import com.inmobi.sdk.InMobiSdk

class BannerAds : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ads)

        // Banner ad
        val bannerAd: InMobiBanner = findViewById(R.id.banner)
        bannerAd.load()
        bannerAd.setRefreshInterval(60)
    }
}
