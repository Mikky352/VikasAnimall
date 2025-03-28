plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.vikasanimall"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.vikasanimall"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
       // sourceCompatibility = JavaVersion.VERSION_1_8
       // targetCompatibility = JavaVersion.VERSION_1_8

           sourceCompatibility =  JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
      //  jvmTarget = "1.8"
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation ("com.google.android.material:material:1.12.0")
   // implementation("androidx.compose.material3:material3")
    implementation("androidx.fragment:fragment-ktx:1.8.6")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
  //  implementation("androidx.recyclerview:recyclerview:1.4.0")


    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.6.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation ("com.squareup.okhttp3:okhttp:3.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.8.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.1.0")
    implementation("androidx.compose.material3:material3-android:1.3.1")
    // swipe referesh view
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    //Glider
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    //Dagger - Hilt
    //implementation ("com.google.dagger:hilt-android:2.42")
    //annotationProcessor ("com.google.dagger:dagger-compiler:2.42")
    //kapt ("com.google.dagger:hilt-android-compiler:2.42")
    implementation ("com.google.dagger:hilt-android:2.48.1")
    annotationProcessor ("com.google.dagger:dagger-compiler:2.48.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.48.1")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}