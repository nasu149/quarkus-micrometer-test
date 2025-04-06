package com.marcha;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

import com.marcha.prime.PrimeResponsePojo;

@Path("/prime")
public class PrimeResource {

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
            Integer count = 0;
            return new PrimeResponsePojo(limit, count, new ArrayList<Integer>());
        }

        List<Integer> primes = sieveOfEratosthenes(limit);
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
