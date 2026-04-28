package com.beleningalina.jetpackcomposepoc.ui.product

import android.util.Log

class MockAnalytics {
    fun logScreenView(productName: String) {
        Log.i("AnalyticsService", "📊 EVENT SENT: User is viewing -> $productName")
    }
}