import java.util.Scanner;

public class Menu {
    Game game;

    public Menu(Game game) {
        this.game = game;
        System.out.println(game.getFactions());

    }
    public void bribesRequest(){
        Scanner scanner = new Scanner(System.in);

        for (Faction faction : game.getFactions()){
            if ((this.game.getTreasury() >= (double)faction.getSupporters()*15) && (faction.getSupporters() < 100)){
                System.out.println("Vous possedez " + this.game.getTreasury() + "$");
                System.out.println("Soudoyer la faction des "+ faction.getName() + "pour " + faction.getSupporters()*15 + "$" );
                System.out.println(faction.getName() + " possède " + faction.getApprobation() + "% de satisfaction");
                System.out.println("Oui ou Non");
                String a = scanner.nextLine();
                if (a.equals("Oui") || a.equals("oui")){
                    this.game.bribes(faction);
                    System.out.println(faction.getName() + " possède à présent " + faction.getApprobation() + "% de satisfaction");
                }
            }
        };

    }

    public void gameMenu()
    {
        System.out.println("1 - ??Définir les facteurs Agriculture et Industrie");
        System.out.println("2 - Passer au tour suivant");
    }



}
