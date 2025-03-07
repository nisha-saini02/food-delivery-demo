plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
//    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.infosys"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.infosys"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.hilt.compiler)

    //Gson
    implementation(libs.gson.converter)

    //coroutines
    implementation (libs.coroutine.core)
    implementation (libs.coroutine.android)

    // ViewModel
    implementation (libs.viewmodel)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.scalar)

//    Interceptor
    implementation (libs.interceptor)

    //firebase
//    implementation(libs.firebase.messaging.ktx)
//    implementation ("com.google.firebase:firebase-firestore:25.1.2")
//    implementation (platform("com.google.firebase:firebase-bom:33.9.0"))
//    implementation("com.google.firebase:firebase-auth:23.2.0")
//    implementation ("com.google.firebase:firebase-storage-ktx")

    //coil
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")

    // Android Maps Compose composables for the Maps SDK for Android
    implementation("com.google.maps.android:maps-compose:6.4.1")

    //Room db
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.1.0")

    //datastore
    implementation ("androidx.datastore:datastore-preferences:1.1.3")
    implementation("androidx.datastore:datastore-preferences-core:1.1.3")
}