plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.s4a4.core"
    compileSdk = 33

    defaultConfig {

        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

val navVersion: String by project
val retrofitVersion: String by project

val ktxVersion: String by project
val runtimeKtxVersion: String by project
val activityComposeVersion: String by project
val composeBomVersion: String by project

dependencies {

    implementation("androidx.core:core-ktx:$ktxVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$runtimeKtxVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}