// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    //Dagger Hilt
    id("com.google.dagger.hilt.android") version "2.55" apply false

    //Firebase Analytics
    id("com.google.gms.google-services") version "4.4.2" apply false
}