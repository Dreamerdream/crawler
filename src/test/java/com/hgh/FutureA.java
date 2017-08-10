package com.hgh;

import java.util.concurrent.Callable;

public class FutureA implements Callable {
    @Override
    public Object call() throws Exception {
        return 1+1;
    }
}
