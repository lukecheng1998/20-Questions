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
                System.out.println(ansWord);
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
                    /*TODO We'll need to convert this to an ArrayList of Array of Strings in order to store the related words*/
                    ArrayList<String[]> verbList = new ArrayList<>();
                    ArrayList<String[]> categoriesList = new ArrayList<>();
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader("/Users/luke/IdeaProjects/20_Questions/verbs.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String wordLine = line;
                            String[] words = wordLine.split("\\s+");
                            verbList.add(words);
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
                            String wordLine = line;
                            String[] words = wordLine.split("\\s+");
                            categoriesList.add(words);
                        }
                        reader.close();
                    }catch(Exception e){
                        System.err.print("Exception occured trying to read file");
                        e.printStackTrace();
                    }
                    /*END TODO*/
                    for (int i = 0; i < stringAns.size(); i++){
                        int isAnswered = 0; //Check to see if question has been answered only used for basic question as of the moment
                        if (stringAns.get(i).equalsIgnoreCase("is") || stringAns.get(i).equalsIgnoreCase("it") || stringAns.get(i).equalsIgnoreCase("a") || stringAns.get(i).equalsIgnoreCase("Does") || stringAns.get(i).equalsIgnoreCase("an")){
                            continue;
                        }else{
                            for (int j = 0; j < verbList.size(); j++){
                                int k = 0;
                                String[] verbTemp = verbList.get(j);
                                if(verbTemp[k].equalsIgnoreCase(ansWord)){
                                    for(k = 1; k < verbTemp[k].length(); k++){
                                        JOptionPane.showMessageDialog(null, "Yes", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                                        isAnswered = 1;
                                        break;
                                    }
                                }
                                if(isAnswered == 1){
                                    break;
                                }
                            }
                            if(isAnswered == 1){
                                continue;
                            }
                            for (int j = 0; j < categoriesList.size(); j++){
                                int k = 0;
                                String[] categoriesTemp = categoriesList.get(j);
                                if(categoriesTemp[k].equalsIgnoreCase(ansWord)){
                                    for(k = 0; k < categoriesTemp[k].length(); k++){
                                        JOptionPane.showMessageDialog(null, "Yes", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                                        isAnswered = 1;
                                        break;
                                    }
                                }
                                if(isAnswered == 1){
                                    break;
                                }
                            }
                            //System.out.println(stringAns.get(i));
                            if(i == stringAns.size() - 1 && isAnswered == 0){
                                JOptionPane.showMessageDialog(null, "No", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    counter--;
                }
            }else{ //If the AI wants to guess the answer

            }
        }
    }
}
