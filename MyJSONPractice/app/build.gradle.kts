plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	id("kotlin-kapt")
	id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}

android {
	namespace = "com.example.myjsonuser"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.example.myjsonuser"
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
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.retrofit2.kotlinx.serialization.converter) // Or the latest version
	implementation(libs.kotlinx.serialization.json) // Or the latest version
	implementation(libs.converter.moshi) // Or the latest version
	implementation(libs.moshi.kotlin) // Or the latest version
	kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0") // For Moshi code generation
	implementation(libs.converter.gson) // Or the latest version
	implementation(libs.kotlinx.serialization.json) // Use latest version
	implementation(libs.retrofit)
	implementation(libs.retrofit2.kotlinx.serialization.converter.v080)
	implementation(libs.okhttp)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}
