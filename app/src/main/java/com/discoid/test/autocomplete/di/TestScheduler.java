package com.discoid.test.autocomplete.di;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jahsavage on 16/08/2016.
 */
public class TestScheduler implements IScheduler {

    @Override
    public Scheduler currentThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computationThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.trampoline();
    }

}
