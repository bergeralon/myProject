package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Algorithm.Action;
import Algorithm.BFS;
import Domain.MazeState;

public class TestBFS
{

	@Test
	public void test()
	{
		MazeState s = new MazeState(1, 1);
		s.setCameWithAction(new Action("right"));
		
		BFS bfs = new BFS();
		ArrayList<Action> path = bfs.generatePathToGoal(s);
		
		Assert.assertEquals(1, path.size());
		Assert.assertEquals("right", path.get(0).toString());
		
	}
	
	@Test
	public void test1()
	{
		MazeState s = new MazeState(1, 1);
		MazeState s1 = new MazeState(0, 1);
		s.setCameWithAction(new Action("right"));
		s.setCameFrom(s1);
		s1.setCameWithAction(new Action("down"));
		
		BFS bfs = new BFS();
		ArrayList<Action> path = bfs.generatePathToGoal(s);
		
		Assert.assertEquals(2, path.size());
		Assert.assertEquals("down", path.get(0).toString());
		Assert.assertEquals("right", path.get(1).toString());
	}
	
//	@Test
//	public void test2()
//	{
//		fail("Not yet implemented");
//	}

}
