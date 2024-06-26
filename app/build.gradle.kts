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

        /** testInstrumentationRunner : es el que se encarga de lanzar los test,
         * este sirve cuando NO SE USA DaggerHilt, cuando los test de ui son mas sencillos,
         * pero cuando los test son mas complejos o se usa DaggerHilt, se debe crear un "CustomTestRunner" y
         * asociar el lanzador con la clase creada*/
       // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.example.vviiblue.horoscapp.CustomTestRunner"
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



    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    val navVersion = "2.7.1"
    val daggerHiltVersion = "2.48"
    val retrofitVersion = "2.9.0"
    val cameraVersion = "1.2.3"

    //NavComponent
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //DaggerHilt  -- kapt es la dependencia que permite autogenerar codigo a Daggerhilt (asi es como funciona Daggerhilt, crea classes por detras de forma automatica para realizar su tarea)
    //asi que para agregar daggerHilt, se deben generar estas dos dependencias, "id ("com.google.dagger.hilt.android") version "2.48" apply false" a nivel del build.gradle del proyecto y
    // id("kotlin-kapt")
    // id("com.google.dagger.hilt.android")
    // en el plugin de este build.gradle
    implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
    kapt("com.google.dagger:hilt-compiler:$daggerHiltVersion")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    //Interceptor habitual, ejemplo este que muestra las respuestas de las peticiones a internet que muestra los códigos de error, los errores, (tambien es posible crear Interceptors de forma manual)
    //El interceptor se mete en el Modulo que se haya definido (para una clean arquitecture) (NetworkModule)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.3.1")

    //Camera X
    implementation ("androidx.camera:camera-core:${cameraVersion}")
    implementation ("androidx.camera:camera-camera2:${cameraVersion}")
    implementation ("androidx.camera:camera-lifecycle:${cameraVersion}")
    implementation ("androidx.camera:camera-view:${cameraVersion}")
    implementation ("androidx.camera:camera-extensions:${cameraVersion}")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    /** Importante, las librerias para hacer testing se dividen en "testImplementation" y "androidTestImplementation"
     *- Las librerias "testImplementation" son librrias que solo van a funcionario en el directorio "test"  (para pruebas unitarias que tengan que ver kotline, o operaciones de capa domain)
     *- Las librerias "androidTestImplementation" son librerias que solo van a funcionar en el directorio "androidTest" (para pruebas que tengan que ver con android y ui en general)*/
    //**UnitTesting
    testImplementation(libs.junit)
    testImplementation ("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    testImplementation ("io.mockk:mockk:1.12.3")

    //**UITesting
    androidTestImplementation(libs.androidx.junit)
       //Es util para automatizar los test de ui
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.4.0")
    //porque para los test se van a necesitar inyectar cosas
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.48")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48")
    androidTestImplementation ("androidx.fragment:fragment-testing:1.6.1")


    /** ********************** *************** IMPORTANTE!!! ************ ************************* ***************/
    //                      **************************************************
    /** las librerias que tienen (libs.La libreria) por ejemplo "libs.androidx.espresso.core"
     * estan definidas junto a su version en
     * ---->  C:\proyectos\Android\HoroscApp\gradle\libs.versions.toml  <---------- */
    //                      **************************************************
    /** ********************** *************************** ************************* ***************/
}