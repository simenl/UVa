
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 0;
		while(n < args.length){
			int number = Integer.parseInt(args[n]);
			n += 1;
			double sum = 0;
			for(int i = 0; i < number; i++){
				sum += Float.parseFloat((args[n + i]))*100;
			}
			double mean = Math.round((sum/number));
			double difference = 0;
			double leftovers = 0;
			for(int i = 0; i < number; i++){
				leftovers += (Float.parseFloat((args[n + i]))*100 - mean);
				difference += Math.abs(Float.parseFloat((args[n + i]))*100 - mean);
			}
			difference =Math.round((difference+leftovers)/2);
			if(number > 0){
				System.out.printf("$%.2f\n",(difference)/100);
			}
			n += number;
		}
	}

}
