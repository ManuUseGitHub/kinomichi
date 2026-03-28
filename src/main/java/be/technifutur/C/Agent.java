package be.technifutur.C;


import be.technifutur.M.StateEngine;

import java.util.concurrent.Flow;

public abstract class Agent implements Flow.Subscription {

    @Override
    public void cancel() {}
}
