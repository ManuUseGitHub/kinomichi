package be.technifutur.kinomichi.C;


import java.util.concurrent.Flow;

public abstract class Agent implements Flow.Subscription {

    @Override
    public void cancel() {}
}
