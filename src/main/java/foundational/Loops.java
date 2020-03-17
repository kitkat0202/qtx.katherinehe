package foundational;

import org.testng.annotations.Test;

public class Loops {

	@Test
	public void forLoops() {
		String[] things = new String[] { "This", "That", "Other" };

		for (int i = 0;i<things.length;i++)
		{
			System.out.println(things[i]);
		}

		for (String thing : things)
		{
			System.out.println(thing);
		}
	}
}
