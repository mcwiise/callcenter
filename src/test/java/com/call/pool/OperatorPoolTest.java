package com.call.pool;

import com.call.domain.Operator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class OperatorPoolTest {

    public GenericPool<Operator> operatorPool;

    @Before
    public void init(){
        this.operatorPool = new OperatorPool(10);
    }

    @Test
    public void shouldCreatePoolWithSize10(){
        assertEquals(10, this.operatorPool.getSize());
    }

    @Test
    public void shouldGetOneOperatorFromPool(){
        Operator operator = operatorPool.get();
        assertNotNull(operator);
        assertEquals(9, operatorPool.getSize());
    }

    @Test
    public void shouldReleaseOneOperatorToPool(){
        Operator operator = operatorPool.get();
        Operator operator1 = operatorPool.get();
        assertNotNull(operator);
        assertNotNull(operator1);
        operatorPool.release(operator);
        assertEquals(9, operatorPool.getSize());
    }
}
