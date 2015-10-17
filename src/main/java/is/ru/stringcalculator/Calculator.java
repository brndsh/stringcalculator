package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}

		else if(text.contains("-")){
			return checkIfNumberIsNegative(splitNumbers(text));
		}
		else if(text.contains("//")){
			return sum(removeBeginning(text));
		}
		else if(text.contains(",") || text.contains("\n") || text.contains(";")){
			return sum(splitNumbers(text));
		}
		else
			return toInt(text);
	}

	private static int checkIfNumberIsNegative(String[] numbers){
		String storeAllNegativeNumbers = "";
		int checkIfNegative = 0;

		for(String number : numbers){
			if(number.contains ("-"))
			{
				checkIfNegative = 1;
			}

			else if(checkIfNegative == 1 && toInt(number) < 0){
		    	storeAllNegativeNumbers += number;
		    	storeAllNegativeNumbers += ", ";
		    	checkIfNegative = 0;
			}
		}


		throw new IllegalArgumentException("Negatives not allowed: " + storeAllNegativeNumbers);
	}

	private static String[] removeBeginning(String numbers){
		String storeDelim = numbers.substring(2,3);		//Store the delimeter
		numbers = numbers.replaceAll("\n", "");			//Replace newline with empty string
		numbers = numbers.substring(3);					//Removing first 3 letters
		numbers = numbers.replaceAll(storeDelim, ";");	//Replace every delimeter with ";"
		
		return splitNumbers(numbers);
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",|\n|;");
	}
        
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
		}
		return total;
    }
}