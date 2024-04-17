plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.vviiblue.horoscapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vviiblue.horoscapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    /** buildTypes,
     *  son configuraciones para la app (como en reactjs existen o existian los "env's"),
     *  pueden existir varios entornos y para cada entorno una configuracion, por ejemplo
     *  cuando la app es "release" se necesita por ejemplo:
     *  cifrarlo,
     *  ofuscar,
     *  que no se pueda debugear,
     *  que tenga la url de produccion  */
    buildTypes {
//     originalmente estaba asi
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
        /** pero quiero tener mas de un entorno asi que lo hare asi */
        getByName("release") {
            /** Se especifica un fichero con valores, por ejemplo si quiero un nombre de app segun el entoerno RELEASE o DEBUGG */
            /** por ultimo se indica este string en el Manifest*/
            resValue("string", "nombreDeLaApp", "HoroscApp") // es como un fichero como string.xml, (tipo,nameClave,"valor"), se pueden almacenar de esta forma apiKey, GoogleMap key, una para produccion y otra para debugg
            isMinifyEnabled = false
            isDebuggable = false // si se esta en el entorno de release, NO se puedan poner breakPoints o debuggear..., deesta manera no se podra hacer debbug de mi app si la  bajan de algun lado
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            /**
             * IMPORTANTE, EL ENTORNO SE DECIDIRA SEGUN EL ENTOERNO SE ESTE TRABAJANDO EN DEBUG O EN RELEASE
             * el buildConfigField es una clase autogenerada, te geneera una clase a parte con los parametros de configuracion del entorno
             *
             * el buildConfigField recibe los siguientes parametros en la configuracion
             *  buildConfigField(
             *  1) el tipo,
             *  2) el nombre del valor que quiero dejar guardado en la config ej:url o direccion un servidor,
             *  3) el valor que se le va a dar a "el nombre"
             *   )
             *   */
            buildConfigField("String",
                            "BASE_URL",
                            "\"https://newastro.vercel.app/\"" // es necesario "escapearlo "\" " , para que la url se tome bien
                            )
        }

        /**  IMPORTANTE, EL ENTORNO SE DECIDIRA SEGUN EL ENTOERNO SE ESTE TRABAJANDO EN DEBUG O EN RELEASE */
        getByName("debug"){
            /** Se especifica un fichero con valores, por ejemplo si quiero un nombre de app segun el entoerno RELEASE o DEBUGG */
            /** por ultimo se indica este string en el Manifest*/
            resValue("string", "nombreDeLaApp", "[DEBUG] HoroscApp") // es como un fichero como string.xml, (tipo,nameClave,"valor"), se pueden almacenar de esta forma apiKey, GoogleMap key, una para produccion y otra para debugg
            isDebuggable = true // si se esta en el entorno de debugg, se puedan poner breakPoints, se puede debuggear o probar errores
            resValue("string", "arisname", "[DEBUG] HoroscApp")
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true

        /** para poder usar las BuildConfig y poder trabajar con entornos configurados , se debe activar la caracteristica
         * sino dara
         * "Build Type 'debug' contains custom BuildConfig fields, but the feature is disabled"*/
        buildConfig = true
    }

// Le indico a android que el java virtual machin toolchain que estoy usando es el 8, para eviar el error que se genera por las dependencias agregadas, ejemplo daggerhilt
    kotlin{
        jvmToolchain(8)
    }
}

dependencies {

    val navVersion = "2.7.1"

    //NavComponent
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //DaggerHilt  -- kapt es la dependencia que permite autogenerar codigo a Daggerhilt (asi es como funciona Daggerhilt, crea classes por detras de forma automatica para realizar su tarea)
    //asi que para agregar daggerHilt, se deben generar estas dos dependencias, "id ("com.google.dagger.hilt.android") version "2.48" apply false" a nivel del build.gradle del proyecto y
    // id("kotlin-kapt")
    // id("com.google.dagger.hilt.android")
    // en el plugin de este build.gradle
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Interceptor habitual, ejemplo este que muestra las respuestas de las peticiones a internet que muestra los códigos de error, los errores, (tambien es posible crear Interceptors de forma manual)
    //El interceptor se mete en el Modulo que se haya definido (para una clean arquitecture) (NetworkModule)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.3.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}