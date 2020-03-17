package foundational;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Operators {

	@Test
	public void isEqualsVsEquals() {
		
		int uno = 1;
		int dos = 1;
		
		Assert.assertTrue(uno == dos); //content comparison
		
		String oneColor = "blue";
		String twoColor = "blue";
		
		Assert.assertTrue(oneColor == twoColor); //reference comparison
		Assert.assertTrue(oneColor.equals(twoColor)); //content comparison
		
		String one = new String("blue");
		String two = new String("blue");
		
		Assert.assertFalse(one == two); //reference comparison
		Assert.assertTrue(one.equals(two)); //content comparison
		
		Car oneCar = new Car("blue");
		Car twoCar = new Car("blue");
		
		Assert.assertFalse(oneCar == twoCar); //reference comparison
		Assert.assertFalse(oneCar.equals(twoCar)); //default behavior is reference comparison
		
		//https://javarevisited.blogspot.com/2012/12/difference-between-equals-method-and-equality-operator-java.html
	}

	private class Car {
		
		private String color;

		public Car(String color) {
			this.color = color;
		}
	}
}
