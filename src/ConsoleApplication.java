public class ConsoleApplication implements Application {

	private final Reader reader = new ConsolerReader();
	private final Writer writer = new ConsoleWriter();
	private final Calculator calculator = new Calculator();
	private final HistoryStorage fileHistoryStorage = new CollectionHistoryStorage();

	@Override
	public void run() {
		boolean wantToContinueFlag = true;
		while (wantToContinueFlag) {
			writer.write("Enter number 1");
			double num1 = reader.readDouble();
			writer.write("Enter number 2");
			double num2 = reader.readDouble();
			writer.write("Enter operation type");
			String type = reader.readString();
			Operation operation = new Operation(num1, num2, type);
			Operation result = calculator.calculate(operation);
			fileHistoryStorage.save(result.toString());
			writer.write("Result " + result);
			checkHistoryDialog();
			writer.write("Do you want to continue? (y/n)");
			String answer = reader.readString();
			if(answer.equals("n")){
				wantToContinueFlag = false;
			}

		}
	}

	private void checkHistoryDialog(){
		writer.write("Do you want to watch the history of operations? (y/n)");
		String answer = reader.readString();
		if(answer.equals("y")){
			fileHistoryStorage.show();
		}
	}
}
