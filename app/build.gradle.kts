import com.android.build.api.variant.BuildConfigField
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.io.Serializable
import java.util.Properties

plugins {

    // APPLICATION
    alias(libs.plugins.android.application)

    // KOTLIN
    alias(libs.plugins.kotlin.android)

    // JETPACK COMPOSE
    alias(libs.plugins.kotlin.compose)

    // KOTLIN SERIALIZATION

    /**
     * Plugin for Kotlin Serialization
     *
     * https://kotlinlang.org/docs/serialization.html
     * https://github.com/Kotlin/kotlinx.serialization
     */
    alias(libs.plugins.jetbrains.kotlinx.serialization)

    // KSP - Sorry, kapt

    /**
     * Kotlin Symbol Processing API
     *
     * https://github.com/google/ksp
     */
    alias(libs.plugins.google.devtools.ksp)
}

val config = loadProperties("configs/config.properties")

android {
    namespace = "space.serphantom.myweather"
    compileSdk = 36

    defaultConfig {
        minSdk = 31
        targetSdk = 36

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        versionCode = config["APPLICATION_VERSION_CODE"].toString().toInt()
        versionName = config["APPLICATION_VERSION_NAME"].toString()
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            versionNameSuffix = "-debug_build"
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    androidComponents {
        onVariants { variant ->
            val buildType = variant.buildType ?: return@onVariants

            variant.buildConfigFields?.apply {
                putStringField(key = "URL_API_SERVER", value = config["URL_API_SERVER"].toString())
                putStringField(
                    key = "API_SERVER_KEY",
                    value = config["API_SERVER_KEY_${buildType.uppercase()}"].toString()
                )
            }
        }
    }

    sourceSets.configureEach {
        if (name == "main") {

            res.srcDirs(

                // layouts
                "src/main/res/layouts/activities",
                "src/main/res/layouts/fragments",
                "src/main/res/layouts",
                "src/main/res/navigation",

                // strings
                "src/main/res/strings",
//                "src/main/res/strings-ru",

                // styles
                "src/main/res/styles",

                // colors
                "src/main/res/colors",

                // fonts
//                "src/main/res/font",

                // res
                "src/main/res",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

fun loadProperties(path: String): Properties {
    return Properties().apply {
        load(FileInputStream(rootProject.file(path)))
    }
}

fun MapProperty<String, BuildConfigField<out Serializable>>.putStringField(
    key: String,
    value: String,
) {
    put(key, BuildConfigField("String", value, null))
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.material)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // COMPOSE

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    /**
     * Coil
     *
     * https://github.com/coil-kt/coil
     * https://coil-kt.github.io/coil/compose/
     */
    implementation(libs.coil.network.okhttp)
    implementation(libs.coil.compose)

    /**
     * Haze - blur for Compose
     *
     * https://github.com/chrisbanes/haze
     */
    implementation(libs.haze)
    implementation(libs.haze.materials)

    // DI

    /** KOIN для Dependency Injection (DI)
     *
     * https://github.com/InsertKoinIO/koin
     * https://insert-koin.io/
     * https://insert-koin.io/docs/setup/annotations/
     * https://github.com/InsertKoinIO/koin-annotations?tab=readme-ov-file
     */
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    // NETWORK / ИНТЕРНЕТ

    /**
     * HTTP-клиент для Java и Android. Используется Retrofit под капотом.
     * Предоставляет поддержку HTTP/2, соединений через сокеты, пулинг и кэширование.
     *
     * @see "https://square.github.io/okhttp/" Официальный сайт OkHttp
     * @see "https://medium.com/@nameisjayant/best-practices-of-retrofit-and-okhttp-in-android-development-bf4cf494f075"
     * Best Practices Retrofit и OkHttp
     */
    implementation(libs.okhttp)

    /**
     * Логирующий интерсептор для OkHttp. Позволяет просматривать HTTP-запросы и ответы в Logcat.
     * Полезен для отладки сетевых запросов.
     *
     * @see "https://square.github.io/okhttp/features/interceptors/"
     * Interceptors в OkHttp
     */
    implementation(libs.logging.interceptor)

    /**
     * Типобезопасный HTTP-клиент для Android и Java. Ретрофит преобразует HTTP API в интерфейс Java/Kotlin,
     * упрощая сетевые запросы и обработку ответов.
     *
     * @see "https://square.github.io/retrofit/" Официальный сайт Retrofit
     * @see "https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet"
     * Codelab от Android Developers
     */
    implementation(libs.retrofit)

    // SERIALIZATION DATA / СЕРЕАЛИЗАЦИЯ ДАННЫХ

    /**
     * Конвертер Retrofit для сериализации JSON в Kotlin объекты с использованием
     * kotlinx.serialization. Альтернатива Moshi и Gson.
     *
     * @see "https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter" Документация конвертера
     * @see "https://kotlinlang.org/docs/serialization.html" Kotlin Serialization
     */
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    /**
     * Библиотека сериализации JSON от JetBrains. Позволяет преобразовывать
     * JSON-строки в объекты Kotlin и обратно без рефлексии.
     *
     * @see "https://github.com/Kotlin/kotlinx.serialization" Официальный репозиторий
     * @sample "https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md" Примеры работы с JSON
     */
    implementation(libs.kotlinx.serialization.json)

    // UI COMPONENTS / UI КОМПОНЕНТЫ

    /**
     * Библиотека Fragment предоставляет компоненты для построения гибких UI-интерфейсов.
     * Включает поддержку жизненного цикла, переходов и back stack.
     *
     * @see "https://developer.android.com/guide/fragments" Документация Fragment
     * @sample "https://github.com/android/architecture-components-samples" Примеры использования Fragment
     */
//    implementation(libs.androidx.fragment.ktx)

    // NAVIGATION / НАВИГАЦИЯ

    /**
     * Navigation Component упрощает навигацию между экранами (Fragment/Composable) в приложении.
     * Предоставляет визуальный редактор графа навигации в Android Studio.
     *
     * @see "https://developer.android.com/guide/navigation" Документация Navigation
     * @sample "https://github.com/android/architecture-components-samples/tree/main/NavigationAdvancedSample"
     * Примеры навигации
     */
//    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.navigation.ui.ktx)

    /**
     * Интеграция Navigation с Jetpack Compose. Позволяет использовать навигацию между Composable-экранами.
     *
     * @see "https://developer.android.com/jetpack/compose/navigation" Навигация в Compose
     */
    implementation(libs.androidx.navigation.compose)

    // LIFE CYCLE FOR ANDROID COMPONENT / ЖИЗНЕННЫЙ ЦИКЛ ANDROID КОМПОНЕНТОВ

    /**
     * Библиотека ViewModel предоставляет компонент архитектуры для управления и хранения
     * UI-данных, устойчивых к изменениям конфигурации (например, поворот экрана).
     *
     * ViewModel отделяет логику управления данными от UI, что способствует лучшей
     * тестируемости и соблюдению принципов чистой архитектуры.
     *
     * @see "https://developer.android.com/topic/libraries/architecture/viewmodel" Официальная документация ViewModel
     * @see "androidx.lifecycle.ViewModel
     * @sample "https://github.com/android/architecture-components-samples" Примеры использования
     */
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // PREFERENCE and DATA / PREFERENCE и данные

    /**
     * Замена SharedPreferences. DataStore предоставляет асинхронный API на Kotlin корутинах и Flow
     * для хранения пар ключ-значение. Решает проблемы SharedPreferences: блокирование UI-потока,
     * отсутствие обработки ошибок и транзакционности.
     *
     * @see "https://developer.android.com/topic/libraries/architecture/datastore" Официальная документация DataStore
     * @see "https://developer.android.com/codelabs/android-preferences-datastore" Codelab по DataStore
     * @sample "https://github.com/android/codelab-android-datastore" Пример приложения
     */
    implementation(libs.androidx.datastore.preferences)
}
