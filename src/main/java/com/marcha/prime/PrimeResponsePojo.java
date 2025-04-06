
package com.marcha.prime;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "limit",
    "count",
    "primes"
})
@Generated("jsonschema2pojo")
public class PrimeResponsePojo {

    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("primes")
    private List<Integer> primes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PrimeResponsePojo() {
    }

    public PrimeResponsePojo(Integer limit, Integer count, List<Integer> primes) {
        super();
        this.limit = limit;
        this.count = count;
        this.primes = primes;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("primes")
    public List<Integer> getPrimes() {
        return primes;
    }

    @JsonProperty("primes")
    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PrimeResponsePojo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("limit");
        sb.append('=');
        sb.append(((this.limit == null)?"<null>":this.limit));
        sb.append(',');
        sb.append("count");
        sb.append('=');
        sb.append(((this.count == null)?"<null>":this.count));
        sb.append(',');
        sb.append("primes");
        sb.append('=');
        sb.append(((this.primes == null)?"<null>":this.primes));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
