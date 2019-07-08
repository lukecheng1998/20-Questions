import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    String fileName = "/Users/luke/IdeaProjects/20_Questions/nouns.txt";
    public void startGame(){
        ArrayList<String> listOfNouns = new ArrayList<>();
        int temp = 0;
        while(true){
            int counter = 20;
            temp = JOptionPane.showConfirmDialog(null, "Would you like to guess the answer?", "20 Questions", JOptionPane.YES_NO_OPTION);
            if(temp == 0){ //If we want to guess the answer
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String line;
                    while((line = reader.readLine()) != null){
                        listOfNouns.add(line);
                    }
                    reader.close();
                }catch(Exception e){
                    System.err.format("Exception occurred trying to read '%s'.", fileName);
                    e.printStackTrace();
                }
                Random r = new Random();
                int getRandomNum = r.nextInt(listOfNouns.size());
                //String ansWord = listOfNouns.get(getRandomNum);
                String ansWord = "Fish";//Testing my idea out
                JOptionPane.showMessageDialog(null, "The Word has been selected press Ok to continue.", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                while(true){
                    if(counter == 0){
                        JOptionPane.showMessageDialog(null, "The correct word is " + ansWord + ". To play again press ok.", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    String answer = JOptionPane.showInputDialog(null, "Ask a Yes/No question, you have " + counter + " chances left.", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(answer);
                    ArrayList<String> stringAns = new ArrayList<String>();
                    for (String word: answer.split(" ")){
                        stringAns.add(word);
                    }
                    ArrayList<String> verbList = new ArrayList<>();
                    ArrayList<String> categoriesList = new ArrayList<>();
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader("/Users/luke/IdeaProjects/20_Questions/verbs.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            verbList.add(line);
                        }
                        reader.close();
                    }catch(Exception e){
                        System.err.format("Exception occured trying to read file");
                        e.printStackTrace();
                    }
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader("/Users/luke/IdeaProjects/20_Questions/categories.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            categoriesList.add(line);
                        }
                        reader.close();
                    }catch(Exception e){
                        System.err.print("Exception occured trying to read file");
                        e.printStackTrace();
                    }
                    for (int i = 0; i < stringAns.size(); i++){
                        if (stringAns.get(i).equalsIgnoreCase("is") || stringAns.get(i).equalsIgnoreCase("it") || stringAns.get(i).equalsIgnoreCase("a")){
                            continue;
                        }else{

                        }
                    }
                    counter--;
                }
            }else{ //If the AI wants to guess the answer

            }
        }
    }
}
