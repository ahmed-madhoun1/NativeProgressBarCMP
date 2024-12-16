plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("maven-publish")
}

android {
    compileSdk = 33 // Use compileSdk property instead of compileSdkVersion
    defaultConfig {
        minSdk = 21 // Use minSdk property instead of minSdkVersion
        targetSdk = 33 // Use targetSdk property instead of targetSdkVersion
    }
}

kotlin {
    androidTarget() // Correctly configure the Android target

    // Other targets (e.g., jvm, ios, js) can be added as needed
    jvm() // Example for JVM target
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["android"]) // Ensure this references the correct component
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressBarCMP")

            credentials {
                username = (project.findProperty("gpr.user") as String?) ?: System.getenv("GT_USERNAME")
                password = (project.findProperty("gpr.token") as String?) ?: System.getenv("GT_TOKEN")
            }
        }
    }
}
