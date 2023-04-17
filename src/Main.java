import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        afficher();
        dansLedico();

    }

    // fonction de tirage au sort d'un mot
    protected static ArrayList <Character> tirerMotAleatoirement() {

        ArrayList <String> dico = new ArrayList<>();
        ArrayList<Character> caractereWord = new ArrayList<>();

        // ouvrir le fichier et récupérer le nombre de ligne
        int ligne = 0;

        try (FileInputStream file = new FileInputStream("src/dictionnaire.txt");
             Scanner out = new Scanner(file)) {

            while (out.hasNextLine()) {
                dico.add(String.valueOf(out.nextLine()));
                ligne++;
            }

        } catch (IOException e) {
            System.out.println("erreur sur le fichier");
        }
        // générer un numéro entre 0 et le max de ligne du ficher TXT

        int minDico = 0;
        int maxDico = ligne;
        Random randomNumberLine = new Random();

        int alea = randomNumberLine.nextInt(minDico, maxDico);

        // afficher le mot tiré aléatoirement avec alea
        System.out.println("Le mot aléatoire est : " + dico.get(alea));

        // passer le mot dico en caractères
        char caractere =' ';

        for (int i = 0; i < dico.get(alea).length(); i++){
            caractere = dico.get(alea).charAt(i);
            caractereWord.add(i, caractere);
        }
        return caractereWord;
    }

    // fonction pour mélanger les caractères du mot tiré au sort
    protected static ArrayList <Character> melanger() {
        ArrayList <Character> touilleur = new ArrayList<>();
        touilleur = tirerMotAleatoirement();
        Collections.shuffle(touilleur);
        return touilleur;
    }

    // Fonction pour afficher le mot tiré au sort avec les lettres mélangées
    protected static void afficher(){
        ArrayList<Character> arrayAffichage = melanger();
        System.out.println(arrayAffichage);
    }

    // Fonction pour vérifier si la saisie utilisateur est bien dans le dictionnaire
    protected static void dansLedico(){

        Scanner question = new Scanner(System.in);
        System.out.println("Entrez votre proposition pour trouver le mot");
        String proposition = question.nextLine();
        question.close();

        System.out.println("Vous avez saisi : " + proposition);

        int positifRes = 0;
        String wordMatch = " ";

        try (FileInputStream file = new FileInputStream("src/dictionnaire.txt");
             Scanner out = new Scanner(file)) {

             while (out.hasNextLine()) {
                 wordMatch = out.nextLine();

                 if(wordMatch.equalsIgnoreCase(proposition)){
                    positifRes++;
                     System.out.println("bravo mot trouvé");
                 }
            }
            if (positifRes == 0){
                System.out.println("Mot non présent dans le dictionnaire");
            }

        } catch (IOException e) {
            System.out.println("erreur sur le fichier");
        }
    }
}

/*

Un mot est tiré au sort dans le dictionnaire
 Les lettres le constituant sont mélangées aléatoirement
 Le tirage est affiché au joueur
 Le joueur saisi t sa proposition
 La proposition est vérifiée
o uniquement les lettres tirées ont été utilisées
o mot présent dans le dictionnaire
 Le mot tiré au sort est dévoilé au joueur.

Il y a 22 506 lignes dans le dictionnaire

 */