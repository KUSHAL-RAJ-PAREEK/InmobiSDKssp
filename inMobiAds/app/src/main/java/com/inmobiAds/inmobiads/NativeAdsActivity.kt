package com.inmobiAds.inmobiads

import android.app.Activity
import android.util.Log
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiNative
import com.inmobi.ads.listeners.NativeAdEventListener
import com.inmobi.ads.listeners.VideoEventListener

class NativeAdsActivity : Activity() {
    private val mNativeAds: MutableList<InMobiNative> = ArrayList()



        val nativeAd =  InMobiNative(this@NativeAdsActivity, 1702237351833, NativeAdEventListener())
    init {
        nativeAd.load()
        mNativeAds.add(nativeAd)
        nativeAd.load()
        nativeAd.setVideoEventListener (VideoEventListener())
    }




     class VideoEventListener : com.inmobi.ads.listeners.VideoEventListener() {
         override    fun onVideoCompleted(ad: InMobiNative) {
             Log.d("VideoEventListener", "Video completed for ad: $ad")
         }

         override   fun onVideoSkipped(ad: InMobiNative) {
             Log.d("VideoEventListener", "Video skipped for ad: $ad")
         }

         override fun onAudioStateChanged(inMobiNative: InMobiNative, isMuted: Boolean) {

             Log.d("VideoEventListener", "not runing")
         }
     }


    class NativeAdEventListener : com.inmobi.ads.listeners.NativeAdEventListener() {


        override fun onAdLoadFailed(ad: InMobiNative, status: InMobiAdRequestStatus) {
            Log.e("NativeAdTAG", "Failed to fetch ad for placement id: ${1702237351833}, Reason: ${status.message}")

        }
        override fun onAdFullScreenDismissed(ad: InMobiNative) {
            Log.d("NativeAdTAG", "Ad full screen dismissed")

        }

        override fun onAdFullScreenWillDisplay(ad: InMobiNative) {
            Log.d("NativeAdTAG", "Ad full screen will display")

        }

        override fun onAdFullScreenDisplayed(ad: InMobiNative) {
            Log.d("NativeAdTAG", "Ad full screen displayed")

        }

        override fun onUserWillLeaveApplication(ad: InMobiNative) {
            Log.d("NativeAdTAG", "User will leave application")

        }

        override fun onAdClicked(ad: InMobiNative) {
            Log.d("NativeAdTAG", "Ad clicked")

        }

        override fun onAdStatusChanged(nativeAd: InMobiNative) {
            Log.d("NativeAdTAG", "Ad status changed")

        }

        override fun onAdImpression(ad: InMobiNative) {
            Log.d("NativeAdTAG", "Ad impression")

        }
    }
}


