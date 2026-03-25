import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/** Submit to the TA, and he will assign you a grade based on a few selected responses.  */





public class Week10_labs
{
    public static void main(String[] args)
	{
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

		// Collect elements into a Set
		System.out.println("set of fruit:");
		Set<String> fruitSet = fruit.stream().collect(Collectors.toSet());

		for(String s : fruitSet)
		{
			System.out.println(s);
		}

		System.out.println("");

        // Collect the fruit into groups based on their first character
		System.out.println("fruit by first character:");
		Map<Character, List<String>> fruitByFirstChar = fruit.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
		 for (Map.Entry<Character, List<String>> entry : fruitByFirstChar.entrySet()) {
			System.out.println("first: " + entry.getKey());
			System.out.println("fruits: " + entry.getValue());
		}

		System.out.println("");


		
		// Group fruit by the length of the name
		System.out.println("fruit by length:");
		Map<Integer, List<String>> fruitByLength = fruit.stream().collect(Collectors.groupingBy(String::length));
		 for (Map.Entry<Integer, List<String>> entry : fruitByLength.entrySet()) {
			System.out.println("length: " + entry.getKey());
			System.out.println("fruits: " + entry.getValue());
		}

		System.out.println("");

		//Collect the fruit that has erry in it
		System.out.println("fruit with 'erry':");
		List<String> fruitWithErry = fruit.stream().filter(s -> s.contains("erry")).collect(Collectors.toList());
		 for (String s : fruitWithErry) {
			System.out.println(s);
		}

		 System.out.println("");

		//Create a partition of fruit based on if it contains erry
		Map<Boolean, List<String>> partitionedFruit = fruit.stream().collect(Collectors.partitioningBy(s -> s.contains("erry")));
		System.out.println("partitioned by contains 'erry':");
		 for (Map.Entry<Boolean, List<String>> entry : partitionedFruit.entrySet()) {
			System.out.println("contains 'erry': " + entry.getKey());
			System.out.println("fruits: " + entry.getValue());
		}

		 System.out.println("");

		//collect/ the fruit that has 5 or less symbols
		List<String> shortNamedFruit = fruit.stream().filter(s -> s.length() <= 5).collect(Collectors.toList());
		System.out.println("fruit with 5 or less symbols:");
		 for (String s : shortNamedFruit) {
			System.out.println(s);
		}

		 System.out.println("");

		//find the total number of symbols in all the fruit stored
		int totalSymbols = fruit.stream().mapToInt(String::length).sum();
		 System.out.println("Total number of symbols: " + totalSymbols);

		 System.out.println("");




		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

        // Partition data based on if >=50
		System.out.println("partitioned by >=50:");
		Map<Boolean, List<Integer>> partitionedData = data.stream().collect(Collectors.partitioningBy(x -> x >= 50));
		 for (Map.Entry<Boolean, List<Integer>> entry : partitionedData.entrySet()) {
			System.out.println("Key: " + entry.getKey());
			System.out.println("Values: " + entry.getValue());
		}

		System.out.println("");


		//divide data into groups based on the remainder when divided by 7
		Map<Integer, List<Integer>> dataByRemainder = data.stream().collect(Collectors.groupingBy(x -> x % 7));
		 for (Map.Entry<Integer, List<Integer>> entry : dataByRemainder.entrySet()) {
			System.out.println("remainder: " + entry.getKey());
			System.out.println("data: " + entry.getValue());
		}

		System.out.println("");



		//find the sum of the data
		int sum = data.stream().mapToInt(Integer::intValue).sum();
		 System.out.println("Sum of data: " + sum);


		 System.out.println("");



		//collect the unique values
		Set<Integer> uniqueValues = data.stream().collect(Collectors.toSet());
		 System.out.println("unique values:");
		 for (Integer i : uniqueValues) {
			System.out.println(i);
		}

		System.out.println("");


        //compute the cube of each values
		List<Integer> cubes = data.stream().map(x -> x * x * x).collect(Collectors.toList());
		System.out.println("cubes:");
		 for (Integer i : cubes) {
			System.out.println(i);
		}	

		System.out.println("");


		//find the sum of the cubes of each value
		int sumOfCubes = data.stream().mapToInt(x -> x * x * x).sum();
		 System.out.println("Sum of cubes: " + sumOfCubes);


		System.out.println("");

		//increase the value of each element by 5
		List<Integer> increasedBy5 = data.stream().map(x -> x + 5).collect(Collectors.toList());
		System.out.println("increased by 5:");
		 for (Integer i : increasedBy5) {
			System.out.println(i);
		}

		System.out.println("");


		//compute the cube of the even values
		List<Integer> cubesOfEven = data.stream().filter(x -> x % 2 == 0).map(x -> x * x * x).collect(Collectors.toList());
		System.out.println("even cubes:"); 
		for (Integer i : cubesOfEven) {
			System.out.println(i);
		 }

		System.out.println("");
   }
}
