package com.call.pool;

import com.call.domain.Director;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class is a concrete implementation of an Agent pool of Director objects.
 * It takes a BlockingQueue implementation that is thread-safe.
 *
 * @see BlockingQueue
 * @see com.call.domain.Agent
 * @see com.call.domain.Director
 * @author  German Valencia
 */
public class DirectorPool implements GenericPool<Director> {

    private int size;
    private BlockingQueue<Director> directors;

    /**
     * @param size the pool size
     * */
    public DirectorPool(int size) {
        this.size = size;
        this.init();
    }

    /**
     * This method initializes a queue of Directors using a LinkedBlockingQueue,
     * making sure a sync access from threads.
     * */
    private void init() {
        directors = new LinkedBlockingQueue<>();
        for (int i = 0; i<size; i++) {
            directors.add(new Director());
        }
    }

    @Override
    public Director get() {
        if (!directors.isEmpty()){
            try {
                return directors.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void release(Director director) {
        directors.offer(director);
    }

    @Override
    public boolean isEmpty() {
        return directors.isEmpty();
    }

    @Override
    public int getSize() {
        return directors.size();
    }
}
