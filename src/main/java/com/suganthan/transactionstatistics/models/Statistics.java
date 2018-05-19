package com.suganthan.transactionstatistics.models;

/**
 * Created by msuganthan on 19/5/18.
 */
public class Statistics {

    Double sum;
    Double avg;
    Double max;
    Double min;
    Long count;

    public Statistics(Double sum, Double avg, Double max, Double min, Long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Statistics{");
        sb.append("sum=").append(sum);
        sb.append(", avg=").append(avg);
        sb.append(", max=").append(max);
        sb.append(", min=").append(min);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
