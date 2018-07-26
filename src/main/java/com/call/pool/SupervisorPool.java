package com.call.pool;

import com.call.domain.Supervisor;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * The class is a concrete implementation of an Agent pool of Supervisor objects.
 * It takes a BlockingQueue implementation that is thread-safe.
 *
 * @see BlockingQueue
 * @see com.call.domain.Agent
 * @see com.call.domain.Supervisor
 * @author  German Valencia
 */
public class SupervisorPool implements GenericPool<Supervisor> {

    private int size;
    private BlockingQueue<Supervisor> supervisors;

    public SupervisorPool(int size) {
        this.size = size;
        this.init();
    }

    /**
     * This method initializes a queue of Supervisors using a LinkedBlockingQueue,
     * making sure a sync access from threads.
     * */
    private void init() {
        supervisors = new LinkedBlockingQueue<>();
        for (int i = 0; i<size; i++) {
            supervisors.add(new Supervisor());
        }
    }

    @Override
    public Supervisor get() {
        if (!supervisors.isEmpty()){
            try {
                return supervisors.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void release(Supervisor supervisor) {
        supervisors.offer(supervisor);
    }

    @Override
    public boolean isEmpty() {
        return supervisors.isEmpty();
    }

    @Override
    public int getSize() {
        return supervisors.size();
    }
}
