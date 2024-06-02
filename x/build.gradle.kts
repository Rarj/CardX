import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("com.vanniktech.maven.publish") version "0.28.0"
}

mavenPublishing {
    publishToMavenCentral(host = SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    pom {
        coordinates(groupId = "com.rioarj.labs", artifactId = "cardx", version = "0.1.0")

        name.set("CardX")
        description.set("CardX, a cutting-edge Kotlin library designed to revolutionize the way you preview Debit Cards within your Android applications.\n" +
                "\n" +
                "Leveraging the power of Jetpack Compose, CardX offers a seamless integration that enables developers to effortlessly showcase debit card designs with stunning visual appeal. What sets CardX apart is its incorporation of flip animation, adding an engaging and dynamic element to the card preview experience.\n" +
                "\n" +
                "With CardX, you can captivate users with realistic card flips, creating an immersive and interactive environment that elevates the user experience to new heights.")
        url.set("https://github.com/Rarj/CardX")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                name.set("Rio Arj")
                email.set("developer.arj@gmail.com")
                organizationUrl.set("https://github.com/Rarj/")
                organization.set("Rio Arj")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/Rarj/CardX.git")
            developerConnection.set("scm:git:ssh://github.com:Rarj/CardX.git")
            url.set("https://github.com/Rarj/CardX/")
        }

    }

}

android {
    namespace = "com.rioarj.labs"
    compileSdk = 34

    defaultConfig {
        minSdk = 22

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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.activity.compose)
    implementation(libs.ui.tooling.preview)
    implementation(libs.constraint.layout)
    implementation(libs.compose.foundation)
    implementation(libs.material3)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.ui.tooling)
}