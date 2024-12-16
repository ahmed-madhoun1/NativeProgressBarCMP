plugins {
    kotlin("multiplatform") version "1.8.20" // Ensure the correct Kotlin version
    `maven-publish`
}

kotlin {
    androidTarget() // Registering Android target

    jvm() // Example target for JVM
    iosX64() // iOS target for X64
    iosArm64() // iOS target for ARM64

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        val iosMain by creating {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["android"])

            groupId = "com.example" // Change this to your group ID
            artifactId = "mylibrary" // Change this to your artifact ID
            version = "1.0.0" // Define your version
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressBarCMP") // GitHub Package URL

            credentials {
                // Fetching GitHub credentials from project properties or environment variables
                username = (project.findProperty("gpr.user") as String?) ?: System.getenv("GT_USERNAME")
                password = (project.findProperty("gpr.token") as String?) ?: System.getenv("GT_TOKEN")
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}
