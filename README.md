## Call Center

This is an implementation of a Dispatcher of calls based on 
a ThreadPoolExecutor in a non-blocking fashion.

## Class Diagram

Please, click on:

https://github.com/valennciag/callcenter/blob/master/call-center.jpg

## Run Test Cases and Coverage

This is a maven project, so from the project root folder, please execute

`mvn clean test`

The project also supports Jacoco, so go to the following folder and open index.html file to see the coverage report:

$HOME_FOLDER/callcenter/target/site/jacoco/

There is also a dummy client on com.call.dispatcher.Client that you can use 
to run the application.

## Extra Points

Dar alguna solución sobre qué pasa con una llamada cuando entran
más de 10 llamadas concurrentes.

- La aplicación basa su implementación en un ThreadPool configurable (10 hilos), el cuál recibe las tareas a ejecutar
(en este caso llamadas) a través de un ExecutorService, por tanto en caso de que haya mas de 10 llamadas concurrentes,
el ExecutorService encolará la llamada, hasta que un hilo del pool se encuentre de nuevo disponible.

JavaDoc y test unitarios fueron creados de acuerdo a necesidad.