/**
  Problem Statement:
  Given
  An array of numbers of length n
  k sub arrays start and end indices. Each sub array contains elements from the given Start index and end index in the original array(inclusive of start and end).
  
  Sum 1 - Sum of the all the sub arrays
  Sum 2 - Arrange the original array optimally(such that sum is maximum) before calculating the sum of all sub arrays.
  
  Output
  Difference between the 2 sums 
  
  Example:
  Lengths n and k - 4 3
  Original array -  9 1 6 1
  Sub Arrays
  0 0
  0 1
  0 2
  
  Sum 1 - 9 + 10 + 16 = 35
  Sum 2 - 9 + 15 + 16 = 40 (Optimal rearragement is 9 6 1 1)
  
  Difference 5
  
*/


import java.util.*;
import java.util.stream.Stream;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

class Test {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // length
    System.out.print("Enter the lengths: ");
    String input = scanner.nextLine();
    String[] numbers = input.trim().split("\\s+");

    int numberOfElements = Integer.parseInt(numbers[0]);
    int numberOfSubArrays = Integer.parseInt(numbers[1]);

    // array elements
    System.out.print("Enter the array elements: ");
    input = scanner.nextLine();

    int[] elements = Stream.of(input.trim().split("\\s+")).mapToInt(x -> Integer.parseInt(x)).toArray();

    Map<Integer, int[]> subArrays = new HashMap<>();
    for(int i = 0; i < numberOfSubArrays; i++) {
      System.out.println("Enter Sub Array Elements: ");
      input = scanner.nextLine();
      int[] subArray = Stream.of(input.trim().split("\\s+")).mapToInt(x -> Integer.parseInt(x)).toArray();

      subArrays.put(i, subArray);
    }

    /* 
      Rearrangement solution

      1. Find the occurence of each index in the sub arrays
      2. Sort the original array in descending order
      3. Place the highest number in the sorted number at the index with maximum count. Repeat this for second highest.
      4. This way the highest number is included in the final sum maximum times.

    */

    // TODO optimize the rearrangement solution

    // find out the occurence of each index in the sub arrays
    Map<Integer, Integer> indexCounts = new HashMap<>();

    for(Map.Entry<Integer, int[]> entry : subArrays.entrySet()) {
      int[] subArray = entry.getValue();
      for(int i = subArray[0]; i <= subArray[1]; i++) {
        int count = 1;
        if(indexCounts.containsKey(i)) {
          int prevCount = indexCounts.get(i);
          count = prevCount + 1;
        }
        
        indexCounts.put(i, count);
      }
    }

    // sort the index count array in descending order and store it in linked hash map to preserve the order
    Map<Integer, Integer> sortedIndexCounts = indexCounts.entrySet().stream()
      .sorted(comparingByValue(Comparator.reverseOrder())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, 
      (e1, e2) -> e1, LinkedHashMap::new));

    System.out.print("Index Count Array:");
    sortedIndexCounts.entrySet().stream().forEach(i -> System.out.print(" " + i));
    System.out.println();

    // sort the original array in descending order
    int[] sortedElements = Arrays.stream(elements).boxed().sorted(Collections.reverseOrder()).mapToInt(i -> i).toArray();

    int[] arrangedArray = new int[sortedElements.length];
    int index = 0;

    // create the re arranged array using the index count and the sorted array
    for(int key : sortedIndexCounts.keySet()) {
      arrangedArray[key] = sortedElements[index];
      index++;
    }

    System.out.print("Rearranged Array:");
    Arrays.stream(arrangedArray).forEach(i -> System.out.print(" " + i));
    System.out.println();

    int aliceSum = 0;
    int bobSum = 0;

    // calculate the sum
    for(Map.Entry<Integer, int[]> entry : subArrays.entrySet()) {
      int[] subArray = entry.getValue();
      for(int i = subArray[0]; i <= subArray[1]; i++) {
        aliceSum += elements[i];
        bobSum += arrangedArray[i];
      }

      System.out.println("Alice Sum " + aliceSum);
      System.out.println("Bob Sum " + bobSum);
    }

    System.out.println("Final Alice Sum " + aliceSum);
    System.out.println("Final Bob Sum " + bobSum);

    System.out.println("Difference " + Math.abs(bobSum - aliceSum));
  }
}
