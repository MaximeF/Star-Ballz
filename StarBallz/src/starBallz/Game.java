package starBallz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game 
{
	public void removeNullsFromList(ArrayList<?> list)
	{
		for (Object object : list)
		{
			if (object == null)
			{
				list.remove(object);
			}
		}
	}
	
	public void fillTimeList(ArrayList<Integer> list,String fileName)
	{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("ressources/"+fileName+".txt"));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) 
			{
				list.add(Integer.parseInt(line));
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
