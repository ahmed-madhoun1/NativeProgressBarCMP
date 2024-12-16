plugins {
    id("com.android.library") // Ensure the Android plugin is applied
    kotlin("multiplatform") // Kotlin Multiplatform plugin
}

android {
    compileSdk = 33 // Use appropriate compile SDK version
    defaultConfig {
        minSdk = 21
        targetSdk = 33 // Target SDK version
    }
}

kotlin {
    androidTarget() // Correctly set the Android target for Kotlin Multiplatform

    // You can add other targets like JVM, iOS, etc.
    jvm() // Example JVM target
    iosX64() // Example iOS target (for iOS development)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.9.0") // Example Android dependency
            }
        }
        val iosMain by getting {
            dependencies {
                // Add any iOS-specific dependencies if required
            }
        }
    }
}

// If publishing, ensure the correct component is used
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["android"]) // Reference the Android component
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressBarCMP") // GitHub Package URL

            credentials {
                username = (project.findProperty("gpr.user") as String?) ?: System.getenv("GT_USERNAME")
                password = (project.findProperty("gpr.token") as String?) ?: System.getenv("GT_TOKEN")
            }
        }
    }
}
