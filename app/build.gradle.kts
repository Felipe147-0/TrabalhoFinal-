plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    kotlin("kapt")

}

android {
    namespace = "br.edu.ifsp.dmo.trabalhofinal"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.edu.ifsp.dmo.trabalhofinal"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Dependências do Room, versão atual 2.6.1
    val version = "2.6.1"
    implementation("androidx.room:room-runtime:$version")
    implementation("androidx.room:room-ktx:$version")
    kapt("androidx.room:room-compiler:$version")

    // Dependências de Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Dependências de DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-core:1.0.0")

    // Dependencias para LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
}