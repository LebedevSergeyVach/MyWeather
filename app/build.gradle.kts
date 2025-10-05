import java.io.BufferedReader
import java.util.Properties
import kotlin.apply

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

android {
    namespace = "space.serphantom.myweather"
    compileSdk = 36

    defaultConfig {
        applicationId = "space.serphantom.myweather"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // file("secrets.properties")
        val secretsProperties = rootDir.resolve("secrets.properties")
            .bufferedReader()
            .use { buffer: BufferedReader ->
                Properties().apply {
                    load(buffer)
                }
            }

        val urlServerWeather = "URL_SERVER_WEATHER"

        buildConfigField(
            type = "String",
            name = urlServerWeather,
            value = secretsProperties.getProperty(urlServerWeather)
        )
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // DI

    /** KOIN для Dependency Injection (DI)
     *
     * https://github.com/InsertKoinIO/koin
     * https://insert-koin.io/
     * https://insert-koin.io/docs/setup/annotations/
     * https://github.com/InsertKoinIO/koin-annotations?tab=readme-ov-file
     *
     * implementation("io.insert-koin:koin-android:3.5.0")
     * implementation("io.insert-koin:koin-androidx-compose:3.5.0")
     */
    implementation("io.insert-koin:koin-android:3.5.0")
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")

    // NEWTWOR / ИНТЕРНЕТ

    /**
     * HTTP-клиент для Java и Android. Используется Retrofit под капотом.
     * Предоставляет поддержку HTTP/2, соединений через сокеты, пулинг и кэширование.
     *
     * @see <a href="https://square.github.io/okhttp/">Официальный сайт OkHttp</a>
     * @see <a href="https://medium.com/@nameisjayant/best-practices-of-retrofit-and-okhttp-in-android-development-bf4cf494f075">Best Practices Retrofit и OkHttp</a>
     */
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    /**
     * Логирующий интерсептор для OkHttp. Позволяет просматривать HTTP-запросы и ответы в Logcat.
     * Полезен для отладки сетевых запросов.
     *
     * @see <a href="https://square.github.io/okhttp/features/interceptors/">Interceptors в OkHttp</a>
     */
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    /**
     * Типобезопасный HTTP-клиент для Android и Java. Ретрофит преобразует HTTP API в интерфейс Java/Kotlin,
     * упрощая сетевые запросы и обработку ответов.
     *
     * @see <a href="https://square.github.io/retrofit/">Официальный сайт Retrofit</a>
     * @see <a href="https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet">Codelab от Android Developers</a>
     */
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // SERIALIZATION DATA / СЕРЕАЛИЗАЦИЯ ДАННЫХ

    /**
     * Конвертер Retrofit для сериализации JSON в Kotlin объекты с использованием
     * kotlinx.serialization. Альтернатива Moshi и Gson.
     *
     * @see <a href="https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter">Документация конвертера</a>
     * @see <a href="https://kotlinlang.org/docs/serialization.html">Kotlin Serialization</a>
     */
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    /**
     * Библиотека сериализации JSON от JetBrains. Позволяет преобразовывать
     * JSON-строки в объекты Kotlin и обратно без рефлексии.
     *
     * @see <a href="https://github.com/Kotlin/kotlinx.serialization">Официальный репозиторий</a>
     * @sample <a href="https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md">Примеры работы с JSON</a>
     */
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // UI COMPONENTS / UI КОМПОНЕНТЫ

    /**
     * Библиотека Fragment предоставляет компоненты для построения гибких UI-интерфейсов.
     * Включает поддержку жизненного цикла, переходов и back stack.
     *
     * @see <a href="https://developer.android.com/guide/fragments">Документация Fragment</a>
     * @sample <a href="https://github.com/android/architecture-components-samples">Примеры использования Fragment</a>
     */
    implementation("androidx.fragment:fragment-ktx:1.8.0")

    // NAVIGATION / НАВИГАЦИЯ

    /**
     * Navigation Component упрощает навигацию между экранами (Fragment/Composable) в приложении.
     * Предоставляет визуальный редактор графа навигации в Android Studio.
     *
     * @see <a href="https://developer.android.com/guide/navigation">Документация Navigation</a>
     * @sample <a href="https://github.com/android/architecture-components-samples/tree/main/NavigationAdvancedSample">Примеры навигации</a>
     */
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    /**
     * Интеграция Navigation с Jetpack Compose. Позволяет использовать навигацию между Composable-экранами.
     *
     * @see <a href="https://developer.android.com/jetpack/compose/navigation">Навигация в Compose</a>
     */
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // LIFE CYCLE FOR ANDROID COMPONENT / ЖИЗНЕННЫЙ ЦИКЛ ANDOIRD КОМПОНЕНТОВ

    /**
     * Библиотека ViewModel предоставляет компонент архитектуры для управления и хранения
     * UI-данных, устойчивых к изменениям конфигурации (например, поворот экрана).
     *
     * ViewModel отделяет логику управления данными от UI, что способствует лучшей
     * тестируемости и соблюдению принципов чистой архитектуры.
     *
     * @see <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">Официальная документация ViewModel</a>
     * @see androidx.lifecycle.ViewModel
     * @sample <a href="https://github.com/android/architecture-components-samples">Примеры использования</a>
     */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")

    // PREFERENCE and DATA / PREFERENCE и данные

    /**
     * Замена SharedPreferences. DataStore предоставляет асинхронный API на Kotlin корутинах и Flow
     * для хранения пар ключ-значение. Решает проблемы SharedPreferences: блокирование UI-потока,
     * отсутствие обработки ошибок и транзакционности.
     *
     * @see <a href="https://developer.android.com/topic/libraries/architecture/datastore">Официальная документация DataStore</a>
     * @see <a href="https://developer.android.com/codelabs/android-preferences-datastore">Codelab по DataStore</a>
     * @sample <a href="https://github.com/android/codelab-android-datastore">Пример приложения</a>
     */
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}
