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
        String factionChoice;
        boolean isNumeric=false;
        try (Scanner scanner = new Scanner(System.in)){

            do {
                System.out.println("Vous possedez " + game.getTreasury() + "$");

                for (Faction faction : game.getFactions()){

                    System.out.println(game.getFactions().indexOf(faction)+1 + " - Soudoyer la faction des " + faction.getName() + " pour " + faction.getSupporters()*15 + "$" );

                }

                System.out.println("0 - Ne soudoyer personne");
                factionChoice = scanner.nextLine();
                isNumeric = factionChoice.chars().allMatch( Character::isDigit );
                while (!isNumeric){
                    System.out.println("Veuillez saisir un chiffre entre 0 et 8");
                    factionChoice = scanner.nextLine();
                    isNumeric = factionChoice.chars().allMatch( Character::isDigit );
                }
                if ((Integer.parseInt(factionChoice) > 0) && ((Integer.parseInt(factionChoice) < 9))){
                    game.bribes(game.getFactions().get((Integer.parseInt(factionChoice)-1)));
                    System.out.println("Le taux d'approbation de la faction des "+ game.getFactions().get((Integer.parseInt(factionChoice)-1)).getName()+ " est de " + game.getFactions().get((Integer.parseInt(factionChoice)-1)).getApprobation() + "%");
                }
                else if ((Integer.parseInt(factionChoice) < 0) || ((Integer.parseInt(factionChoice) >= 9))){
                    System.out.println("Veuillez saisir un chiffre entre 0 et 8");
                }

            }while (((Integer.parseInt(factionChoice)) !=0));
        }

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
