import java.io.*;

/**
 * @author Simon Pirko on 5.01.23
 */
public class ConsoleApplication implements Application {

	private final Reader reader = new ConsolerReader();
	private final FileWriter fileWriter;

	private final BufferedReader bufferedFileReader;

	{
		try {
			fileWriter = new FileWriter("history.txt", true);
			bufferedFileReader = new BufferedReader(new FileReader("history.txt"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private final Writer writer = new ConsoleWriter();
	private final Calculator calculator = new Calculator();

	@Override
	public void run() {
		checkFile();
		while (true) {
			writer.write("Enter number 1");
			double num1 = reader.readDouble();
			writer.write("Enter number 2");
			double num2 = reader.readDouble();
			writer.write("Enter operation type");
			String type = reader.readString();
			Operation operation = new Operation(num1, num2, type);
			Operation result = calculator.calculate(operation);
			saveHistory(result.toString());
			writer.write("Result " + result);
			historyDialog();
		}
	}

	private void checkFile() {
		File file = new File("history.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void watchHistory(){
		try {
			String line;
			while ((line = bufferedFileReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void saveHistory(String line){
		try {
			fileWriter.write(line);
			fileWriter.write("\n");
			fileWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void historyDialog(){
		writer.write("Do you want to watch the history of operations? (y/n)");
		String answer = reader.readString();
		if(answer.equals("y")){
			watchHistory();
		}
	}
}
