package com.call.dispatcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DispatcherTest {

    @Test
    public void shouldDipatchOneCallTest(){
        Dispatcher dispatcher = new Dispatcher(10, 7,2,1);
        dispatcher.dispatchCall();
        dispatcher.shutDown();
    }

}
