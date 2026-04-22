import java.util.Random;

public class Week14_lab {

	static class SumRunnable implements Runnable {
		private long sum;

		@Override
		public void run() {
            //summation logic
			sum = 0;
			for (int i = 1; i <= 100; i++) {
				sum += i;
			}
		}

		public long getSum() {
			return sum;
		}
	}

	static class SumThread extends Thread {
		private long sum;

		@Override
		public void run() {
            //sumation logic
			sum = 0;
			for (int i = 1; i <= 100; i++) {
				sum += i;
			}
		}

		public long getSum() {
			return sum;
		}
	}

    //
	static class Counter {
		private int value;

		public synchronized void increment() {
			value++;
		}

		public int getValue() {
			return value;
		}
	}

    //main thread joins created thread after starting it.
	static class CounterNoSync {
		private int value;

		public void increment() {
			value++;
		}

		public int getValue() {
			return value;
		}
	}

    //main thread runs without joining any thread
	static class SyncWorker extends Thread {
		private final Counter counter;
		private final int repeats;

		SyncWorker(Counter counter, int repeats) {
			this.counter = counter;
			this.repeats = repeats;
		}

		@Override
		public void run() {
			for (int i = 0; i < repeats; i++) {
				counter.increment();
			}
		}
	}

    //the increment method is not synced.
	static class NoSyncWorker extends Thread {
		private final CounterNoSync counter;
		private final int repeats;

		NoSyncWorker(CounterNoSync counter, int repeats) {
			this.counter = counter;
			this.repeats = repeats;
		}

		@Override
		public void run() {
			for (int i = 0; i < repeats; i++) {
				counter.increment();
			}
		}
	}

	static class SquareSumWorker extends Thread {
		private final int[] array;
		private final int start;
		private final int end;
		private long partialSum;

		SquareSumWorker(int[] array, int start, int end) {
			this.array = array;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			long sum = 0;
			for (int i = start; i < end; i++) {
				sum += (long) array[i] * array[i];
			}
			partialSum = sum;
		}

		public long getPartialSum() {
			return partialSum;
		}
	}

    //run everything
	public static void main(String[] args) throws InterruptedException {
		runPart1();
		runPart2();
		runPart3(args);
	}

    //part 1
	private static void runPart1() throws InterruptedException {
		System.out.println(" 1(a): Runnable sum 1 to 100 ");
		SumRunnable sumRunnable = new SumRunnable();
		Thread runnableThread = new Thread(sumRunnable);
		runnableThread.start();
		runnableThread.join();
		System.out.println("Sum from Runnable thread = " + sumRunnable.getSum());

		System.out.println("\n 1(b): Class using thread interface: sum 1 to 100");
		SumThread sumThread = new SumThread();
		sumThread.start();
		sumThread.join();
		System.out.println("Sum from Thread subclass = " + sumThread.getSum());
	}

    //part 2
	private static void runPart2() throws InterruptedException {
		final int threadCount = 10;
		final int incrementsPerThread = 1000;

    
		System.out.println("\n 2(a), 2(b):");
		int withJoinResult = runSyncWithJoin(threadCount, incrementsPerThread);
		System.out.println("counter output= " + withJoinResult);

		System.out.println("\n 2(c):  main thread runs without joining any thread.");
		int withoutJoinResult = runSyncNoJoin(threadCount, incrementsPerThread);
		System.out.println("Counter output without join = " + withoutJoinResult);

		System.out.println("\n 2(d): the increment method is not synchronized.");
		int noSyncResult = runNoSyncWithJoin(threadCount, incrementsPerThread);
		System.out.println("counter output without sync = " + noSyncResult);
	}

	private static int runSyncWithJoin(int threadCount, int incrementsPerThread) throws InterruptedException {
		Counter counter = new Counter();
		Thread[] threads = new Thread[threadCount];

		for (int i = 0; i < threadCount; i++) {
			threads[i] = new SyncWorker(counter, incrementsPerThread);
			threads[i].start();
		}
		for (int i = 0; i < threadCount; i++) {
			threads[i].join();
		}
		return counter.getValue();
	}

	private static int runSyncNoJoin(int threadCount, int incrementsPerThread) throws InterruptedException {
		Counter counter = new Counter();
		Thread[] threads = new Thread[threadCount];

		for (int i = 0; i < threadCount; i++) {
			threads[i] = new SyncWorker(counter, incrementsPerThread);
			threads[i].start();
		}

		int snapshot = counter.getValue();

		for (int i = 0; i < threadCount; i++) {
			threads[i].join();
		}

		return snapshot;
	}

	private static int runNoSyncWithJoin(int threadCount, int incrementsPerThread) throws InterruptedException {
		CounterNoSync counter = new CounterNoSync();
		Thread[] threads = new Thread[threadCount];

		for (int i = 0; i < threadCount; i++) {
			threads[i] = new NoSyncWorker(counter, incrementsPerThread);
			threads[i].start();
		}
		for (int i = 0; i < threadCount; i++) {
			threads[i].join();
		}
		return counter.getValue();
	}


    //part 3
	private static void runPart3(String[] args) throws InterruptedException {
		if (args.length == 0) {
			System.out.println("\n 3: Sum of squares timing ");
			System.out.println("Give n as a command line arg i.e. java Week14_lab 100");
			return;
		}

		int n;
        n = Integer.parseInt(args[0]);
		

		int[] numbers = new int[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			numbers[i] = random.nextInt(100) + 1;
		}

		long startTime1 = System.nanoTime();
		long directSum = 0;
		for (int i = 0; i < numbers.length; i++) {
			directSum += (long) numbers[i] * numbers[i];
		}
		long endTime1 = System.nanoTime();
		long duration1 = endTime1 - startTime1;

		int mid = numbers.length / 2;
		SquareSumWorker worker1 = new SquareSumWorker(numbers, 0, mid);
		SquareSumWorker worker2 = new SquareSumWorker(numbers, mid, numbers.length);

		long startTime2 = System.nanoTime();
		worker1.start();
		worker2.start();
		worker1.join();
		worker2.join();
		long threadedSum = worker1.getPartialSum() + worker2.getPartialSum();
		long endTime2 = System.nanoTime();
		long duration2 = endTime2 - startTime2;

		System.out.println("\n 3: Sum of squares timing ");
		System.out.println("n = " + n);
		System.out.println("Single thread sum = " + directSum + ", time = " + duration1 + " ns");
		System.out.println("Two thread sum    = " + threadedSum + ", time = " + duration2 + " ns");
		System.out.println("Sums match: " + (directSum == threadedSum));
	}
}
