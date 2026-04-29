package com.learntocode.projects.stayEase.service;

import com.learntocode.projects.stayEase.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}

