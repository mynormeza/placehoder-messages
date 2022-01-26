
object Versions {
    const val androidMinSdkVersion = 24
    const val androidTargetSdkVersion = 32
    const val androidCompileSdkVersion = 32

    const val material = "1.5.0"
    const val androidCore = "1.7.0"
    const val androidCompat = "1.4.1"
    const val constraintLayout = "2.1.3"
    const val navigation = "2.3.5"
    const val timber = "4.7.1"


    const val junitVersion = "4.13.2"
    const val junitExtVersion = "1.1.3"
    const val espressoVersion = "3.4.0"

}

object Dependencies {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
    const val androidCompat = "androidx.appcompat:appcompat:${Versions.androidCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}