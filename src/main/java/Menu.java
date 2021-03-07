import java.lang.reflect.Array;
import java.util.*;

public class Menu {
    Game game;

    public Menu(Game game) {
        this.game = game;


    }
    public void bribesRequest(){
            String factionChoice;
            boolean isNumeric=false;
            Scanner scanner = new Scanner(System.in);

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
            this.gameMenuForNewYear();
        }



    public void gameMenu()
    {
        int season = this.game.getSeason();
        int year = this.game.getYear();
        if(season==0 && year>0) // 1 year passed
        {
            this.game.reviewAgricultureEndOfYear();
            this.game.reviewIndustryEndOfYear();
            this.gameMenuForNewYear();
        }
        String choice;
        boolean isNumeric=false;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("1 - Afficher les statistiques de votre île");
            System.out.println("2 - Passer au tour suivant");
            System.out.println("3 - Abandonner la partie");

            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );
            if(choice.isEmpty())
            {
                isNumeric=false;
            }
        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>3);

        switch (choice)
        {
            case "1":
                this.game.displayAll();
                this.gameMenu();
                break;
            case "2":
                System.out.println("case 2");
                this.game.incrementSeason();
                this.callEvent();
                break;
            case "3":
                System.out.println("case 3 - end game");
                this.callExit();
                break;
        }


    }
    public void gameMenuForNewYear()
    {
        String choice;
        boolean isNumeric=false;
        System.out.println("___ Une année vient de s'écouler sur votre île ___");
        System.out.println("Votre score est de " + this.game.getScore() + " points");
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("1 - Afficher les statistiques de votre île");
            System.out.println("2 - Acheter des denrées alimentaires");
            System.out.println("3 - Soudoyer une faction");
            System.out.println("4 - Passer au tour suivant");
            System.out.println("5 - Abandonner la partie");
            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );
            if(choice.isEmpty())
            {
                isNumeric=false;
            }
        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>4);

        switch (choice)
        {
            case "1":
                this.game.displayAll();
                this.gameMenuForNewYear();
                break;
            case "2":
                this.foodMarket();
                break;
            case "3":
                System.out.println("case 2");
                this.bribesRequest();
                break;
            case "4":
                System.out.println("case 3");
                this.game.incrementSeason();
                this.callEvent();
                break;
            case "5":
                System.out.println("case 4 - end game");
                this.callExit();
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
        Scanner scan = new Scanner(System.in);
        do{
            i=0;
            System.out.println("/!\\/!\\/!\\ Evenement /!\\/!\\/!\\");
            System.out.println(eventName);

            for(Choice e : eventChoices)
            {
                i++;
                System.out.println(i+" - "+e.getName());

            }
            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );
            if(choice.isEmpty())
            {
                isNumeric=false;
            }
        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>i);

        Choice choosenChoiceForEvent=eventChoices.get(Integer.parseInt(choice)-1);
        Effect effectsObj = choosenChoiceForEvent.getEffectsObj();

        game.applyEffects(effectsObj);
        System.out.println(effectsObj);
        this.gameMenu();
    }

    public void callExit()
    {
        String choice;
        boolean isNumeric=false;

        do{
            System.out.println("Etes vous sûr d'abandonner la partie ? Toute la progression sera perdue");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );
            if(choice.isEmpty())
            {
                isNumeric=false;
            }

        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>2);

        switch (choice)
        {
            case "1":
                System.out.println("Exit game");
                return;
            case "2":
                System.out.println("back to the menu");
                this.gameMenu();
                break;
        }
    }
    public void chooseDifficulty()
    {
        String choice;
        boolean isNumeric=false;

        do{
            System.out.println("Choisissez le niveau de difficulté");
            System.out.println("1 - Facile");
            System.out.println("2 - Normal");
            System.out.println("3 - Difficile");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );
            if(choice.isEmpty())
            {
                isNumeric=false;
            }

        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>3);

        switch (choice)
        {
            case "1":
                System.out.println("Vous avez choisi le mode Facile");
                this.game.setDifficulty(0.5);
                break;
            case "2":
                System.out.println("Vous avez choisi le mode Normal");
                this.game.setDifficulty(1);
                break;
            case "3":
                System.out.println("Vous avez choisi le mode Difficile");
                this.game.setDifficulty(2);
                break;
        }
    }

    public void foodMarket(){
        String quantity;
        boolean isNumeric=false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Vous possedez " + game.getTreasury() + "$");
            System.out.println("Chaque unité de nourriture coûte 8$, veuillez saisir le montant désiré");
            System.out.println("0 - Revenir au menu précédent");

            quantity = scanner.nextLine();
            isNumeric = quantity.chars().allMatch( Character::isDigit );
            if(quantity.isEmpty())
            {
                isNumeric=false;
            }
            while (!isNumeric){
                System.out.println("Veuillez saisir un montant valide");
                quantity = scanner.nextLine();
                isNumeric = quantity.chars().allMatch( Character::isDigit );
                if(quantity.isEmpty())
                {
                    isNumeric=false;
                }
            }
            int quantityInt=Integer.parseInt(quantity);
            if(this.game.buyFood(quantityInt))
            {
                System.out.println("Vous avez bien acheté "+quantity+" unités de nourriture pour "+quantityInt*8+"$");
            }
            else
            {
                System.out.println("Vous n'avez pas la trésorerie nécessaire pour acheter cette quantité de nourriture");
            }
        }while (((Integer.parseInt(quantity)) !=0));
        this.gameMenuForNewYear();
    }


}
