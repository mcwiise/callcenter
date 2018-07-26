package com.call.pool;

import com.call.domain.Director;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class DirectorPoolTest {

    public GenericPool<Director> directorPool;

    @Before
    public void init(){
        this.directorPool = new DirectorPool(10);
    }

    @Test
    public void shouldCreatePoolWithSize10(){
        assertEquals(10, this.directorPool.getSize());
    }

    @Test
    public void shouldGetOneOperatorFromPool(){
        Director director = directorPool.get();
        assertNotNull(director);
        assertEquals(9, directorPool.getSize());
    }

    @Test
    public void shouldReleaseOneOperatorToPool(){
        Director director = directorPool.get();
        Director director1 = directorPool.get();
        assertNotNull(director);
        assertNotNull(director1);
        directorPool.release(director);
        assertEquals(9, directorPool.getSize());
    }
}
