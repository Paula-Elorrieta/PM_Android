plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.booksliveapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.booksliveapp"
        minSdk = 23
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("mysql:mysql-connector-java:5.1.49")
    implementation ("org.osmdroid:osmdroid-android:6.1.15")
    // Dependencia principal de Parceler
    implementation("org.parceler:parceler-api:1.1.13")
    // Procesador de anotaciones para Parceler
    annotationProcessor("org.parceler:parceler:1.1.13")
}