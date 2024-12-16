plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.dokka")
    id("com.vanniktech.maven.publish") // For publishing the library
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "NativeProgressBarCMP"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val androidMain by getting {
            dependencies {
                // Add Android-specific dependencies if needed
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val desktopMain by getting {
            dependencies {
                // Add Desktop-specific dependencies if needed
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.am.nativeprogressindicator"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

mavenPublishing {
    // Publish to Maven Central (Sonatype OSS)
    publishToMavenCentral(SonatypeHost.S01, automaticRelease = true)
    signAllPublications()
    
    coordinates("com.am", "NativeProgressBarCMP", "1.0.0") // Group ID, Artifact ID, Version

    pom {
        name.set("NativeProgressBarCMP")
        description.set("A Kotlin Multiplatform library for showing a native-style progress bar for Android and iOS.")
        inceptionYear.set("2023")
        url.set("https://github.com/AhmedMadhoun/NativeProgressBarCMP")
        
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        
        developers {
            developer {
                id.set("AhmedMadhoun")
                name.set("Ahmed Madhoun")
                url.set("https://github.com/AhmedMadhoun")
            }
        }
        
        scm {
            url.set("https://github.com/AhmedMadhoun/NativeProgressBarCMP")
            connection.set("scm:git:git://github.com/AhmedMadhoun/NativeProgressBarCMP.git")
            developerConnection.set("scm:git:ssh://git@github.com/AhmedMadhoun/NativeProgressBarCMP.git")
        }
    }
}
