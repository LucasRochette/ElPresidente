import org.junit.Assert;
import org.junit.Test;

public class FactionTest {
    @Test
    public void createFaction(){
        Faction faction = new Faction("Imperialists", 43, 23);
        Assert.assertEquals("Imperialists", faction.getName());
        Assert.assertEquals(43, faction.getApprobation());
        Assert.assertEquals(23, faction.getSupporters());
    }
}
