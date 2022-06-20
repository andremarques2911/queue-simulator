package model;

import java.util.List;

public class Topology {
   private List<Integer> seeds;
   private int seed;
   private int randomNumbersPerSeed;
   private List<Arrival> arrivals;
   private List<Queue> queues;
   private List<Double> randomNumbers;

   public List<Integer> getSeeds() {
      return seeds;
   }

   public void setSeeds(List<Integer> seeds) {
      this.seeds = seeds;
   }

   public int getSeed() {
      return seed;
   }

   public void setSeed(int seed) {
      this.seed = seed;
   }

   public int getRandomNumbersPerSeed() {
      return randomNumbersPerSeed;
   }

   public void setRandomNumbersPerSeed(int randomNumbersPerSeed) {
      this.randomNumbersPerSeed = randomNumbersPerSeed;
   }

   public List<Arrival> getArrivals() {
      return arrivals;
   }

   public void setArrivals(List<Arrival> arrivals) {
      this.arrivals = arrivals;
   }

   public List<Queue> getQueues() {
      return queues;
   }

   public void setQueues(List<Queue> queues) {
      this.queues = queues;
   }

   public List<Double> getRandomNumbers() {
      return randomNumbers;
   }

   public void setRandomNumbers(List<Double> randomNumbers) {
      this.randomNumbers = randomNumbers;
   }
}
