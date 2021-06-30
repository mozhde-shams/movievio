object Versions {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
    const val gradle = "4.1.2"
    const val kotlin = "1.3.72"
    const val core_ktx = "1.3.2"
    const val rxJava = "2.2.10"
    const val rxAndroid = "2.1.1"
    const val appcompat = "1.2.0"
    const val material = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val junit = "4.13.2"
    const val androidx_junit = "1.1.2"
    const val espresso_core = "3.3.0"
    const val retrofit = "2.9.0"
    const val converter_gson = "2.9.0"
    const val adapter_rxjava = "2.6.0"
    const val dagger = "2.23.2"
    const val okhttp = "3.14.9"
    const val logging_interceptor = "3.11.0"
    const val lifecycle_extensions = "2.2.0"
    const val navigation = "2.3.1"
    const val legacy_support = "1.0.0"
    const val picasso = "2.71828"
    const val paging = "3.0.0-beta01"
    const val mockito = "3.11.2"
    const val espresso = "3.3.0"
    const val dexmaker = "2.28.1"
    const val core_testing = "2.1.0"
}

object Dependencies {
    //kotlin Region
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val core_ktxLibrary = "androidx.core:core-ktx:${Versions.core_ktx}"

    //Rx Region
    const val rxjavaLibrary = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroidLibrary = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    //Ui Region
    const val appcompatLibrary = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val materialLibrary = "com.google.android.material:material:${Versions.material}"
    const val constraintLayoutLibrary =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    //Test Region
    const val junitLibrary = "junit:junit:${Versions.junit}"
    const val androidx_junitLibrary = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val espresso_coreLibrary =
        "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val dexmaker = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.dexmaker}"
    const val core_testing = "androidx.arch.core:core-testing:${Versions.core_testing}"


    //Gradle Region
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"

    //Retrofit Region
    const val retrofitLibrary = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonLibrary = "com.squareup.retrofit2:converter-gson:${Versions.converter_gson}"
    const val adapterRxJavaLibrary =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.adapter_rxjava}"

    //Dagger Region
    const val daggerLibrary = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compilerLibrary =
        "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_androidLibrary = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_supportLibrary =
        "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_android_processorAnnotation =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger_android_processorKapt =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    //OKhttp3 Region
    const val okhttpLibrary = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val logging_interceptorLibrary =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"

    //Lifecycle Region
    const val lifecycle_extensionsLibrary =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extensions}"

    //Navigation Region
    const val navigation_fragmentLibaray =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktxLibrary =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val legacy_supportLibrary = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    const val navigation_safe_args_gradle_plugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    //Picasso Region
    const val picassoLibrary = "com.squareup.picasso:picasso:${Versions.picasso}"

    //Paging Region
    const val paging_runtimeLibrary = "androidx.paging:paging-runtime:${Versions.paging}"
    const val paging_rxjavaLibrary = "androidx.paging:paging-rxjava2:${Versions.paging}"

}