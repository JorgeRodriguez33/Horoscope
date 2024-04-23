package com.example.vviiblue.horoscapp.data.network.response

import com.example.vviiblue.horoscapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.Test



class PredictionResponseTest{
    /** ¡¡¡Los nombres de las operaciones de test, deben ser jodidamente explicativos
     * no importa si son re largos
     * esta correcto que se llame "toDomainShouldReturnACorrectPredictionModel" porque el nombre
     * explica lo que deberia pasar en la operacion de test
     * en este caso el metodo "toDomain" deberia retornar un objeto PredictionModel, listo mas nada
     * SOLO PARA LOS TEST, LOS NOMBRES DE LAS OPERACIONES DE LOS TEST, PUEDEN IR SEPARADOS CON ESPACIOS USANDO COMILLAS
     * fun `toDomain should return a correct PredictionModel`()
     * */
    @Test // para que la operacion de test, sea un TEST, debe tener su Annotation @Test
    fun `toDomain should return a correct PredictionModel`(){
        /** las operaciones de "test" se deben dividir en
         *
         * Given - Obtengo la informacion necesaria para el test
         * When -  Cuando "pase una accion"/"algo ocurra"/"Se ejecute" ...
         * Then -  Entonces verifico que ha pasado algo
         *
         * */


       // val horoscopeResponse = PredictionResponse("date","prediction","taurus")
        /** Given
         * Genero un dato, da igual los parametros para este test
         * aqui le doy lo que necesito testear*/
         val horoscopeResponse = anyResponse

        /** Para agregar nomas */
        /** Si para alguna pruba no necesito modificar ese modelo basico para un test en especifico
         * bastaria hacer algo asi...*/
        // val horoscopeResponseModificado = anyResponse.copy(sign = "gemini")
        /** y se trabaja con ese modelo basico pero con la modificacion.... */
        //***************************

        /**When
         * Entonces cuando ocurra la "accion" que voy a "testear", en este caso sera "toDomain"
         * */
        val predictionModel = horoscopeResponse.toDomain()

        /**Then
         * Entonces, debo comprobar que lo que me dio el paso When el val "predictionModel"
         * es igual a lo que me dio el paso Given "horoscopeResponse" en los sitios correctos
         * (ej: que el atributo sign de "predictionModel" sea igual al sign de horoscopeResponse,
         * y asi....)
         * para este caso entonces, se comprueba que el modelo se convierto en el otro modelo, que se mappea correctamente
         * */
        predictionModel.sign shouldBe horoscopeResponse.sign //shouldBe es gracias a una de las librerias que se agrego
        predictionModel.horoscope shouldBe  horoscopeResponse.horoscope
    }

}