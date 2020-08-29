package com.test.biddingsystem.biddingrules;

public interface BiddingRules<T, U> {
    public boolean apply(T t, U v);
}

