package com.call.dispatcher;

import com.call.domain.ClientCall;
import com.call.domain.Director;
import com.call.domain.Operator;
import com.call.domain.Supervisor;
import com.call.pool.DirectorPool;
import com.call.pool.GenericPool;
import com.call.pool.OperatorPool;
import com.call.pool.SupervisorPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The Dispatcher class handles calls coming from the client
 * and looks for the next available Agent to attend it. This makes
 * use of ThreadPoolExecutor class to manage the pool of worker threads,
 * and also contains a queue that keeps tasks to get executed.
 *
 * @author  German Valencia
 */
public class Dispatcher{

    private ThreadPoolExecutor executor;
    private GenericPool<Operator> operatorsPool;
    private GenericPool<Supervisor> supervisorPool;
    private GenericPool<Director> directorPool;

    /**
     * The constructor takes the size and instantiates the thread pool to handle calls,
     * and also the generic pools which provide the available Agents
     * to attend calls.
     *
     * @param callPoolSize the size of the threadPool that handles the call.
     * @param operatorPoolSize The number of available operators.
     * @param supervisorPoolSize The number of available supervisors.
     * @param directorPoolSize The number of available directors.
     * */
    public Dispatcher(int callPoolSize,
                      int operatorPoolSize,
                      int supervisorPoolSize,
                      int directorPoolSize){
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(callPoolSize);
        this.operatorsPool = new OperatorPool(operatorPoolSize);
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(callPoolSize);
        this.operatorsPool = new OperatorPool(operatorPoolSize);
        this.supervisorPool = new SupervisorPool(supervisorPoolSize);
        this.directorPool = new DirectorPool(directorPoolSize);
    }


    public void dispatchCall(){

        ClientCall clientCall = new ClientCall();

        if(!operatorsPool.isEmpty()){
            Operator operator = operatorsPool.get();
            clientCall.setAgent(operator);
            CompletableFuture
                    .supplyAsync(clientCall, executor)
                    .thenAccept(agent -> operatorsPool.release((Operator)agent));
        } else if(!supervisorPool.isEmpty()){
            Supervisor supervisor = supervisorPool.get();
            clientCall.setAgent(supervisor);
            CompletableFuture
                    .supplyAsync(clientCall, executor)
                    .thenAccept(agent -> supervisorPool.release((Supervisor)agent));
        } else if(!directorPool.isEmpty()){
            Director director = directorPool.get();
            clientCall.setAgent(director);
            CompletableFuture
                    .supplyAsync(clientCall, executor)
                    .thenAccept(agent -> directorPool.release((Director)agent));
        }
    }

    /**
     * This method kills the execution of the ThreadPoolExecutor,
     * as long as all the CompletableFuture instances have ended,
     * making sure all Suppliers where attended.
     *
     * */
    public void shutDown(){
        this.executor.shutdown();
    }
}
