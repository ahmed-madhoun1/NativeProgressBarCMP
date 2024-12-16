plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("maven-publish") // Keep this for publishing to GitHub Packages
}

android {
    compileSdkVersion(33)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(33)
    }
}

kotlin {
    android() // Ensure android target is created
    jvm() // Example of another target, you can configure this as needed
    // Add targets for iOS, JS, etc., if needed
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["android"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressBarCMP")

            credentials {
                username = project.findProperty("gpr.user") ?: System.getenv("GT_USERNAME")
                password = project.findProperty("gpr.token") ?: System.getenv("GT_TOKEN")
            }
        }
    }
}
