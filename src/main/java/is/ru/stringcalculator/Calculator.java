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

	private static String checkDelimetersOfAnyLength(String numbers){
		int delimeterEnds = numbers.indexOf("]");
		String storeDelimeters = numbers.substring(3, delimeterEnds);

		numbers = numbers.replace(storeDelimeters, ";");
		numbers = numbers.substring(delimeterEnds);
		

		return numbers;
	}

	private static int checkIfNumberIsNegative(String[] numbers){
		String storeAllNegativeNumbers = "";
		int checkIfNegative = 0;

		for(String number : numbers){
			if(number.contains ("-")){
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
		if (numbers.contains ("[") && numbers.contains("]")){
			numbers = checkDelimetersOfAnyLength(numbers);
		}
		
		else
		{
			String storeDelim = numbers.substring(2,3);		//Store the delimeter
			numbers = numbers.replaceAll(storeDelim, ";");	//Replace every delimeter with ";"
			numbers = numbers.substring(3);					//Removing first 3 letters
		}
		
		numbers = numbers.replaceAll("\n", "");			//Replace newline with empty string
		
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
        	if(toInt(number) < 1000){
        		total += toInt(number);
        	}
		}
		return total;
    }
}