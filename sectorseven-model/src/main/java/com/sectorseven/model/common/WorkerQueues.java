package com.sectorseven.model.common;

import java.util.concurrent.ArrayBlockingQueue;

import com.sectorseven.model.common.SMS;

public class WorkerQueues {

    public static final ArrayBlockingQueue<SMS> SMS_QUEUE = new ArrayBlockingQueue<>(10);

    
}
