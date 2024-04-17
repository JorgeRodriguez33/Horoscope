package com.example.vviiblue.horoscapp.domain.usecase

import com.example.vviiblue.horoscapp.domain.Repository
import javax.inject.Inject

/** En la capa de dominio (es la logica de negocio, si la app exisitiera en anroid y ios,
 * ambas app deberian tener la misma informacion en sus casos de uso con syntaxis diferente),
 * los casos de usos son clases que tienen una unica responsabilidad
 * en este caso la unica responsabilidad es obtener las preducciones*/
/** los casos de uso pueden nombrarse como "elnombre" , "elnombre+UseCase" , "elnombre+Interactor" */

/** Se tuvo que agregar "provideRepository" para poder inyectar "Repository" , ya que Dagger Hilt no sabra como devolver "Repository"
 * recordar que:
 * -para las librerias ,
 * -clases que no se puedan acceder (privada) o
 * -las interfaces(el caso de "Repository")
 * hay que proveer desde el Modulo definido ("NetworkModule")
 * */
class GetPredictionUseCase @Inject constructor(private val repository: Repository) {

    /** La palabra clave "operator" se utiliza para sobrecargar operadores, en este caso la funcion "invoke" y
     *  sobrecargo el operador "invoke" para que reciba por parametro un string
     *  entonces al definir la opracion de esta manera, no tengo que ponerle un nombre a la operacion, sino
     *  que basta con poner el nombre del caso de uso y pasarle por parametro el signo del zodiaco
     *  en el viewModel se invoca la consulta de la prediccion a la capa de negocio de la forma siguiente
     *  "getPredictionUseCase(sign.name)" nada mas, no hay una operacion con un nombre determinado, "que se podria", simplemente con el "invoke" no es necesario*/

    suspend operator fun invoke(sign:String) = repository.getPrediction(sign)
}