package com.call.pool;

/**
 * The GenericPool interface is a basic implementation of
 * a pool of Agents based on a FIFO queue
 *
 * @see Agent
 * @author  German Valencia
 */
public interface GenericPool<Agent> {

    /**
     * This method gets an Agent from the pool, and removes it
     * from the head of the queue.
     *
     * @return an new available agent
     * */
    public Agent get();

    /**
     * This method gets an Agent back to the pool, inserting it
     * into the queue.
     *
     * @param object The Agent to get back to the pool
     * */
    public void release(Agent object);

    /**
     * This method returns true just in case the pool is empty
     * @return true if the pool is empty
     * */
    public boolean isEmpty();

    /**
     * @return The size of the pool
     * */
    public int getSize();
}
