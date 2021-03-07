import org.junit.Assert;
import org.junit.Test;


public class GameTest {
    Game game = new Game();


    @Test
    public void scenarioLoading() {
        game.loadScenario("attackOnTitans.json");
        Assert.assertEquals("Attack on Titans", game.getName());
        Assert.assertEquals(63.75, game.getGlobalSatisfactionPercentage(), 0);
        Assert.assertEquals("Capitalists",game.getFactions().get(0).getName());
        Assert.assertEquals("Communists",game.getFactions().get(1).getName());
        Assert.assertEquals("Liberals",game.getFactions().get(2).getName());
        Assert.assertEquals("Religious",game.getFactions().get(3).getName());
        Assert.assertEquals("Militarists",game.getFactions().get(4).getName());
        Assert.assertEquals("Ecologists",game.getFactions().get(5).getName());
        Assert.assertEquals("Loyalists",game.getFactions().get(6).getName());
        Assert.assertEquals("Nationalists",game.getFactions().get(7).getName());
    }

    @Test
    public void bribing(){
        game.loadScenario("attackOnTitans.json");
        game.getFactions().get(0).setApprobation(30);
        game.bribes(game.getFactions().get(0));
        game.getFactions().get(1).setApprobation(0);
        game.getFactions().get(2).setApprobation(100);
        Assert.assertEquals(33,game.getFactions().get(0).getApprobation());
        Assert.assertEquals(0,game.getFactions().get(1).getApprobation());
        Assert.assertEquals(100,game.getFactions().get(2).getApprobation());


    }
}
