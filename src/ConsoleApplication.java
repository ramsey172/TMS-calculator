public class ConsoleApplication implements Application {

	private final Reader reader = new ConsolerReader();
	private final Writer writer = new ConsoleWriter();
	private final Calculator calculator = new Calculator();
	//private final OperationStorage operationStorage = new InMemoryOperationStorage();
	private final OperationStorage operationStorage = new FileOperationStorage("history.txt");

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
			operationStorage.save(result);
			writer.write("Result " + result);
			operationStorageDialog();
			writer.write("Do you want to continue? (y/n)");
			String answer = reader.readString();
			if(answer.equals("n")){
				wantToContinueFlag = false;
			}

		}
	}

	private void operationStorageDialog(){
		String answer;

		writer.write("Do you want to watch all calculated operations? (y/n)");
		answer = reader.readString();
		if(answer.equals("y")){
			for(Operation operation : operationStorage.findAll()){
				writer.write(operation.toString());
			}
		}

		writer.write("Do you want to find operation by id? (y/n)");
		answer = reader.readString();
		if(answer.equals("y")){
			writer.write("Enter ID:");
			int operationId = reader.readInt();
			Operation operation = operationStorage.findById(operationId);
			if(operation != null){
				writer.write(operation.toString());
			}else{
				writer.write("Operation is not found");
			}
		}

		writer.write("Do you want to remove operation by id");
		answer = reader.readString();
		if(answer.equals("y")){
			writer.write("Enter ID:");
			int operationId = reader.readInt();
			operationStorage.remove(operationId);
		}


	}
}
