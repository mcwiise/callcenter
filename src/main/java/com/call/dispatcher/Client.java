package com.call.dispatcher;

/**
 * Client class is just a dummy user of Dispatcher
 * @see Dispatcher
 * @author German Valencia
 * */
public class Client {

    public static void main(String[] args) {
        Dispatcher dispatcher =
                new Dispatcher(10,
                        7,
                        2,
                        1);
        dispatcher.dispatchCall();
        dispatcher.dispatchCall();
        dispatcher.dispatchCall();
        dispatcher.dispatchCall();
        dispatcher.shutDown();
    }
}
