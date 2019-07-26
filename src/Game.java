import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    String fileName = "nouns.txt";
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
                ArrayList<String> questionsAsked = new ArrayList<>();
                int getRandomNum = r.nextInt(listOfNouns.size());

                /*LOL how am I gonnna get the ansWord dictionary XD
                * */
                String ansWord = listOfNouns.get(getRandomNum);
                //String ansWord = "Fish";//Testing my idea out
                System.out.println(ansWord);
                JOptionPane.showMessageDialog(null, "The Word has been selected press Ok to continue.", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                int QuestionLocation = 0;
                while(true){
                    if(counter == 0){
                        JOptionPane.showMessageDialog(null, "The correct word is " + ansWord + ". To play again press ok.", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    String questionsAskedString = "";
                    for(int i = 0; i < questionsAsked.size(); i++){
                        int j = i + 1;
                        questionsAskedString += j + ". " + questionsAsked.get(i);
                    }
                    String answer = JOptionPane.showInputDialog(null, "Ask a Yes/No question, you have " + counter + " chances left.\n Previous asked questions:\n" + questionsAskedString, "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                    //questionsAsked.add(answer);
                    answer = answer.replaceAll("\\s*\\p{Punct}+\\s*$", "");
                    System.out.println(answer);
                    ArrayList<String> stringAns = new ArrayList<String>();
                    for (String word: answer.split("\\s+")){
                        stringAns.add(word);
                    }
                    /*TODO We'll need to convert this to an ArrayList of Array of Strings in order to store the related words*/
                    ArrayList<String[]> verbList = new ArrayList<>();
                    ArrayList<String[]> categoriesList = new ArrayList<>();
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader("verbs.txt"));
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
                        BufferedReader reader = new BufferedReader(new FileReader("categories.txt"));
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
                    int gameIsWon = 0;
                    String getRes = "";
                    for (int i = 0; i < stringAns.size(); i++){
                        int isAnswered = 0; //Check to see if question has been answered only used for basic question as of the moment
                        if(stringAns.get(i).equalsIgnoreCase(ansWord)){
                            JOptionPane.showMessageDialog(null, "Congrats, you've guessed the word!", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                            gameIsWon =1;
                            break;
                        }else if (stringAns.get(i).equalsIgnoreCase("is") || stringAns.get(i).equalsIgnoreCase("it") || stringAns.get(i).equalsIgnoreCase("a") || stringAns.get(i).equalsIgnoreCase("Does") || stringAns.get(i).equalsIgnoreCase("an")){
                            continue;
                        }else{
                            for (int j = 0; j < verbList.size(); j++){
                                int k = 0;
                                String[] verbTemp = verbList.get(j);
                                if(verbTemp[k].equalsIgnoreCase(ansWord)){
                                    for(k = 1; k < verbTemp[k].length(); k++){
                                        if(verbTemp[k].equalsIgnoreCase(stringAns.get(i))){
                                            JOptionPane.showMessageDialog(null, "Yes", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                                            getRes = "Yes";
                                            isAnswered = 1;
                                            break;
                                        }
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
                                        if(categoriesTemp[k].equalsIgnoreCase(stringAns.get(i))){
                                            JOptionPane.showMessageDialog(null, "Yes", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                                            getRes = "Yes";
                                            isAnswered = 1;
                                            break;
                                        }
                                    }
                                }
                                if(isAnswered == 1){
                                    break;
                                }
                            }
                            //System.out.println(stringAns.get(i));
                            if(i == stringAns.size() - 1 && isAnswered == 0){
                                JOptionPane.showMessageDialog(null, "No", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                                getRes = "No";
                            }
                        }
                    }

                    counter--;
                    if(gameIsWon == 1){
                        JOptionPane.showConfirmDialog(null,"Would you like to play again?","20 Questions", JOptionPane.QUESTION_MESSAGE);
                    }
                    answer += "    Answer: " + getRes + '\n';
                    questionsAsked.add(answer);
                }
            }else{ //If the AI wants to guess the answer
                ArrayList<String[]> getListofQuestions = new ArrayList<>();
                int questionsRemaining = 20;
                int playagain = 0;
                while(true){
                    if(questionsRemaining == 0){
                        playagain = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "20 Questions", JOptionPane.INFORMATION_MESSAGE);
                        if (playagain == 0){
                            break;
                        }
                    }else{

                        questionsRemaining--;
                    }
                }
            }
        }
    }
}
