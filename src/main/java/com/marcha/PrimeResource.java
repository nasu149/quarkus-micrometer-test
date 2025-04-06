package com.marcha;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.marcha.prime.PrimeResponsePojo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Path("/prime")
public class PrimeResource {

    @Inject
    MeterRegistry registry;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String isPrime(@QueryParam("number") int number) {
        if (number < 2) {
            return number + " is not a prime number.";
        }

        List<Integer> primes = sieveOfEratosthenes(number);
        if (primes.contains(number)) {
            return number + " is a prime number (via Sieve of Eratosthenes)!";
        } else {
            return number + " is not a prime number.";
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public PrimeResponsePojo getPrimes(@QueryParam("limit") int limit) {
        if (limit < 2) {
            registry.counter("com.marcha.prime.number", "type", "less-than-two").increment();
            Integer count = 0;
            return new PrimeResponsePojo(limit, count, new ArrayList<Integer>());
        }
        if (limit==10){
            throw new Error("itotekina error");
        }

        registry.counter("com.marcha.prime.number", "type", "more-than-two-or-equal").increment();

        Timer.Sample sample = Timer.start(registry);
        List<Integer> primes = sieveOfEratosthenes(limit);
        sample.stop(registry.timer("com.marcha.prime.number", "type", "list-seconds"));
        Integer count = primes.size();
        return new PrimeResponsePojo(limit, count, primes);
    }

    private List<Integer> sieveOfEratosthenes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}
