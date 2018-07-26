package com.call.pool;
import com.call.domain.Operator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class is a concrete implementation of an Agent pool of Operator objects.
 * It takes a BlockingQueue implementation that is thread-safe.
 *
 * @see BlockingQueue
 * @see com.call.domain.Agent
 * @see Operator
 * @author  German Valencia
 */
public class OperatorPool implements GenericPool<Operator> {

    private int size;
    private BlockingQueue<Operator> operators;

    /**
     * @param size the pool size
     * */
    public OperatorPool(int size) {
        this.size = size;
        this.init();
    }

    /**
     * This method initializes a queue of Operators using a LinkedBlockingQueue,
     * making sure a sync access from threads.
     * */
    private void init() {
        operators = new LinkedBlockingQueue<>();
        for (int i = 0; i<size; i++) {
            operators.add(new Operator());
        }
    }

    @Override
    public Operator get() {
        if (!operators.isEmpty()){
            try {
                return operators.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void release(Operator operator) {
        operators.offer(operator);
    }

    @Override
    public boolean isEmpty() {
        return operators.isEmpty();
    }

    @Override
    public int getSize() {
        return operators.size();
    }
}
