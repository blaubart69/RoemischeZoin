package at.spi;

public class PrintNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i=0; i <= 3000; i++) {
			System.out.printf("%5d ... %s\n", i, Roem.convertTo1_SpindisErsterAnsatz(i));
		}
	}

}
