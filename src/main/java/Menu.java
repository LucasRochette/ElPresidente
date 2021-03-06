import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
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
        String choice;
        boolean isNumeric=false;
        do{
            System.out.println("1 - ??Définir les facteurs Agriculture et Industrie");
            System.out.println("2 - Passer au tour suivant");
            System.out.println("3 - Abandonner la partie");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );

        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>3);

        switch (choice)
        {
            case "1":
                System.out.println("case 1");
                break;
            case "2":
                System.out.println("case 2");
                this.callEvent();
                break;
            case "3":
                System.out.println("case 3 - end game");
                break;
        }


    }

    public void callEvent()
    {
        Random rand = new Random();
        int randomIndex = rand.nextInt(game.getEvents().size());
        Event randomEvent = game.getEvents().get(randomIndex);
        String eventName = randomEvent.getName();
        List<Choice> eventChoices = randomEvent.getChoices();

        String choice;

        boolean isNumeric=false;
        int i;
        do{
            i=0;
            System.out.println("/!\\/!\\/!\\ Evenement /!\\/!\\/!\\");
            System.out.println(eventName);

            for(Choice e : eventChoices)
            {
                i++;
                System.out.println(i+" - "+e.getName());

            }
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );

        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>i);

        Choice choosenChoiceForEvent=eventChoices.get(Integer.parseInt(choice)-1);
        System.out.println(choosenChoiceForEvent);

    }



}
