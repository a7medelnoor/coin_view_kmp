import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            implementation(libs.ktor.client.okhttp)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            implementation(libs.bundles.ktor)
            implementation(libs.coil.compose)
            implementation(libs.landscapist.coil3)
        }

        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        iosMain.dependencies {
            // iOS-specific dependencies if needed
        }

        commonMain {
            // Resources for multiplatform (access via `resources/`)
            resources.srcDirs("src/commonMain/resources")
        }
    }
}

android {
    namespace = "com.a7medelnoor.coin_view_kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.a7medelnoor.coin_view_kmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        getByName("main") {
            // Just use regular directories without trying to include secrets.properties
            assets.srcDirs("src/androidMain/assets")
            res.srcDirs("src/androidMain/res")
        }
    }

    configurations.all {
        resolutionStrategy {
            // Force using a single version of the annotations library
            force("org.jetbrains:annotations:23.0.0")
            // Exclude the older version
            exclude(group = "com.intellij", module = "annotations")
        }
    }
}

dependencies {
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.compiler)
    debugImplementation(compose.uiTooling)
}

// Alternative approach: Create your own directory for secrets
// This avoids messing with the build process
tasks.register<Copy>("copySecretsFile") {
    from(rootProject.file("secrets.properties"))
    into(file("src/androidMain/assets"))
    // Run this task before the assemble tasks
    outputs.upToDateWhen { file("src/androidMain/assets/secrets.properties").exists() }
}

// Make all tasks that involve processing assets depend on our copy task
gradle.taskGraph.whenReady {
    allTasks.forEach { task ->
        if (task.name.contains("Assets") || task.name.contains("assemble")) {
            task.dependsOn("copySecretsFile")
        }
    }
}