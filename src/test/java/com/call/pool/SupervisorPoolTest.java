package com.call.pool;

import com.call.domain.Supervisor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SupervisorPoolTest {

    public GenericPool<Supervisor> supervisorPool;

    @Before
    public void init(){
        this.supervisorPool = new SupervisorPool(10);
    }

    @Test
    public void shouldCreatePoolWithSize10(){
        assertEquals(10, this.supervisorPool.getSize());
    }

    @Test
    public void shouldGetOneSupervisorFromPool(){
        Supervisor supervisor = supervisorPool.get();
        assertNotNull(supervisor);
        assertEquals(9, supervisorPool.getSize());
    }

    @Test
    public void shouldReleaseOneOperatorToPool(){
        Supervisor supervisor = supervisorPool.get();
        Supervisor supervisor1 = supervisorPool.get();
        assertNotNull(supervisor);
        assertNotNull(supervisor1);
        supervisorPool.release(supervisor);
        assertEquals(9, supervisorPool.getSize());
    }
}
