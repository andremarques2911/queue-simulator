import java.util.List;

public class Topology {

   private int seed;
   private int arrival;
   private int randomNumbersPerSeed;
   private List<Queue> queues;
   private List<Double> randomNumbers;

   public int getSeed() {
      return seed;
   }

   public void setSeed(int seed) {
      this.seed = seed;
   }

   public int getArrival() {
      return arrival;
   }

   public void setArrival(int arrival) {
      this.arrival = arrival;
   }

   public int getRandomNumbersPerSeed() {
      return randomNumbersPerSeed;
   }

   public void setRandomNumbersPerSeed(int randomNumbersPerSeed) {
      this.randomNumbersPerSeed = randomNumbersPerSeed;
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
