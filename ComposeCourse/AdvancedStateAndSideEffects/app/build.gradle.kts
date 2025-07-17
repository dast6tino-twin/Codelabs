import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.kotlin.compose)
}

// Reads the Google maps key that is used in the AndroidManifest
val properties = Properties()
if (rootProject.file("local.properties").exists()) {
    properties.load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "ru.dast_6_tino.advancedstateandsideeffects"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.dast_6_tino.advancedstateandsideeffects"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.compose.samples.crane.CustomTestRunner"

        manifestPlaceholders["MAPS_API_KEY"] = properties.getProperty("MAPS_API_KEY", "")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true

        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
}

dependencies {

    implementation(libs.kotlinx.coroutines)
    implementation(libs.android.maps)
    implementation(libs.google.maps)
    constraints {
        implementation(libs.android.volley) {
            because("Only volley 1.2.0 or newer are available on maven.google.com")
        }
    }

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.tracing)

    val composeBom = libs.androidx.compose.bom
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.runtime)
    implementation(libs.androidx.material)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.ui.tooling.preview)
    androidTestImplementation(libs.androidx.ui.test.juint4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.google.hilt.android)
    kapt(libs.google.hilt.compiler)

    implementation(libs.coil.compose)

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.google.hilt.android)
    androidTestImplementation(libs.google.hilt.testing)
    kaptAndroidTest(libs.google.hilt.compiler)

}
