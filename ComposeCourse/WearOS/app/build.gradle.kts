plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "ru.dast_6_tino.wearos"
    compileSdk = 36

    defaultConfig {
        applicationId = "ru.dast_6_tino.wearos"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)

    // General compose dependencies
    implementation(composeBom)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.androidx.material.icons.extended)

    // Compose for Wear OS Dependencies
    implementation(libs.wear.compose.material)

    // Foundation is additive, so you can use the mobile version in your Wear OS app.
    implementation(libs.wear.compose.foundation)

    // Compose preview annotations for Wear OS.
    implementation(libs.androidx.compose.ui.tooling)

    implementation(libs.horologist.compose.layout)

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(composeBom)

}
