/*
 * Klasa dająca metody operowania wczytanymi statystykami zawodników
 */
package data;

/**
 *
 * @author Ariel
 */

import io.CsvReader;
import java.util.*;

public class DataOperations {
    
    int wins;
    int losses;
    int games;
    int gamesStarted;
    int completeGames;
    int shutouts;
    int saves;
    int outsPitched;
    int hits;
    int earnedRuns;
    int homeRuns;
    int walks;
    int strikeOuts;
    int opponentsBattingAvg;
    double earnedRunAvg;
    double intentionalWalks;
    int wildPitches;
    int battersHitByPitch;
    int balks;
    int battersFacedByPitcher;
    int gamesFinished;
    int runsAllowed;
    
    /**
     * Metoda tworząca histogramy atrybutów
     * @param tempList 
     */
    
    public void createHistogramsOfColumns(List<PlayerExtensionStat> tempList)
    {

        double columnBalks[] = new double[tempList.size()];
        double columnWins[] = new double[tempList.size()];
        double columnLosses[] = new double[tempList.size()];
        double columnGames[] = new double[tempList.size()];
        double columnGamesStarted[] = new double[tempList.size()];
        double columnCompleteGames[] = new double[tempList.size()];
        double columnShutouts[] = new double[tempList.size()];
        double columnSaves[] = new double[tempList.size()];
        double columnOutsPitched[] = new double[tempList.size()];
        double columnHits[] = new double[tempList.size()];       
        double columnEarnedRuns[] = new double[tempList.size()];
        double columnHomeRuns[] = new double[tempList.size()];
        double columnWalks[] = new double[tempList.size()];
        double columnStrikeOuts[] = new double[tempList.size()];
        double columnOppAvgSav[] = new double[tempList.size()];
        double columnERunAvg[] = new double[tempList.size()];
        double columnIntentionalWalks[] = new double[tempList.size()];
        double columnWildPitches[] = new double[tempList.size()];
        double columnBattersHit[] = new double[tempList.size()];
        double columnBattersFaced[] = new double[tempList.size()];
        double columnGamesFinished[] = new double[tempList.size()];
        double columnRunsAllowed[] = new double[tempList.size()]; 
        
        for(int i=0;i<tempList.size();i++)
        {
            columnBalks[i]=tempList.get(i).balks;
            columnWins[i]=tempList.get(i).wins;
            columnLosses[i]=tempList.get(i).losses;
            columnGames[i]=tempList.get(i).games;
            columnGamesStarted[i]=tempList.get(i).gamesStarted;
            columnCompleteGames[i]=tempList.get(i).completeGames;      
            columnShutouts[i]=tempList.get(i).shutouts;
            columnSaves[i]=tempList.get(i).saves;
            columnOutsPitched[i]=tempList.get(i).outsPitched;
            columnHits[i]=tempList.get(i).hits;
            columnEarnedRuns[i]=tempList.get(i).earnedRuns;
            columnHomeRuns[i]=tempList.get(i).homeRuns;
            columnWalks[i]=tempList.get(i).walks;
            columnStrikeOuts[i]=tempList.get(i).strikeOuts;
            columnOppAvgSav[i]=tempList.get(i).opponentsBattingAvg;
            columnERunAvg[i]=tempList.get(i).earnedRunAvg;
            columnIntentionalWalks[i]=tempList.get(i).intentionalWalks;
            columnWildPitches[i]=tempList.get(i).wildPitches;
            columnBattersHit[i]=tempList.get(i).battersHitByPitch;
            columnBattersFaced[i]=tempList.get(i).battersFacedByPitcher;
            columnGamesFinished[i]=tempList.get(i).gamesFinished;
            columnRunsAllowed[i]=tempList.get(i).runsAllowed;
        }
        Histogram hg = new Histogram("balks",columnBalks, tempList.size());
        Histogram hg1 = new Histogram("wins",columnWins, tempList.size());
        Histogram hg2 = new Histogram("losses",columnLosses, tempList.size());
        Histogram hg3 = new Histogram("games",columnGames, tempList.size());
        Histogram hg4 = new Histogram("games started",columnGamesStarted, tempList.size());
        Histogram hg5 = new Histogram("complete games",columnCompleteGames, tempList.size());
        Histogram hg6 = new Histogram("shutouts",columnShutouts, tempList.size());
        Histogram hg7 = new Histogram("outs pitched",columnOutsPitched, tempList.size());
        Histogram hg8 = new Histogram("hits",columnHits, tempList.size());
        Histogram hg9 = new Histogram("earned runs",columnEarnedRuns, tempList.size());
        Histogram hg10 = new Histogram("home runs",columnHomeRuns, tempList.size());
        Histogram hg11 = new Histogram("walks",columnWalks, tempList.size());
        Histogram hg12 = new Histogram("strike outs",columnStrikeOuts, tempList.size());
        Histogram hg13 = new Histogram("opponent avg batting",columnOppAvgSav, tempList.size());
        Histogram hg14 = new Histogram("intentional walks",columnIntentionalWalks, tempList.size());
        Histogram hg15 = new Histogram("wild pitches",columnWildPitches, tempList.size());
        Histogram hg16 = new Histogram("batters hit",columnBattersHit, tempList.size());
        Histogram hg17 = new Histogram("batters faced",columnBattersFaced, tempList.size());
        Histogram hg18 = new Histogram("game finished",columnGamesFinished, tempList.size());
        Histogram hg19 = new Histogram("runs allowed",columnRunsAllowed, tempList.size());

    }
    
    /**
     * Metoda zwracająca listę rozszerzonych statystyk piłkarzy na podstawie listy tokenów danych z 
     * obiektu wczyującego plik .csv
     * @return 
     */
    
    public List<PlayerExtensionStat> read()
    {
        CsvReader reader = new CsvReader();
        return selectionData(reader.readDataFromFile());
    }
    
    /**
     * Metoda redukująca statystyki piłkarzy ze wszystkich znajdujących sie w pliku .csv do 
     * statystyk z których korzystamy podczas podsumowania
     * @param oldList
     * @return 
     */
    
    List<PlayerStat> reduceData(List<PlayerExtensionStat> oldList)
    {
        List<PlayerStat> newList = new ArrayList();
        
        for(PlayerExtensionStat player: oldList)
        {
            PlayerStat tempPlayer = new PlayerStat();
            tempPlayer.completeGames=player.completeGames;
            tempPlayer.playerID=player.playerID;    
            tempPlayer.year=player.year;
            tempPlayer.earnedRuns=player.earnedRuns;
            tempPlayer.gamesStarted=player.gamesStarted;
            tempPlayer.hits=player.hits;
            tempPlayer.homeRuns=player.homeRuns;
            tempPlayer.losses=player.losses;
            tempPlayer.opponentsBattingAvg=player.opponentsBattingAvg;
            tempPlayer.strikeOuts=player.strikeOuts;
            tempPlayer.walks=player.walks;
            tempPlayer.wins=player.wins;
            newList.add(tempPlayer);
        }
        
        return newList;
    }
    
    /**
     * Metoda wywolująca wyświetlanie na konsoli danych poszczególneh kolumny
     * @param numberOfColumn 
     */
    
    public void readColumn(Integer numberOfColumn)
    {
        CsvReader reader = new CsvReader();
        viewData(reader.readDataFromFile(),numberOfColumn);
        return ;
    }
    
    /**
     * Metoda wyświetlająca na konsoli wszystkie wczytane dane z pliku .csv
     * @param tempList 
     */
    
    public void showList(List<PlayerExtensionStat> tempList)
    {
        for(int i=0;i<tempList.size();i++)
        {
            System.out.println(tempList.get(i).toString());
        }
    }
    
    
    /**
     * Metoda obrabiająca dane z postaci listy tokenów do Listy obiektów klasy PlayerExtensionStat
     * @param tokensList
     * @return 
     */
    
    private List<PlayerExtensionStat> selectionData(List<List<Token>> tokensList)
    {
        List<PlayerExtensionStat> playerLists = new ArrayList();
        for(int i=1;i<tokensList.size();i++)            
        {
            PlayerExtensionStat playerTemp = new PlayerExtensionStat();
            for(int j=0;j<tokensList.get(i).size();j++)
            {
                  int tokenNumber=(tokensList.get(i).get(j).getTN())+1;
                  switch(tokenNumber){
                         case 1:
                            playerTemp.playerID=tokensList.get(i).get(j).getStringValue();
                            //System.out.println("String: " + tokensList.get(i).get(j).getStringValue());
                            break;
                         case 2:
                            playerTemp.year=tokensList.get(i).get(j).getIntegerValue();
                             //System.out.println("Integer: " + tokensList.get(i).get(j).getIntegerValue());
                            break;
                         case 3:
                            playerTemp.stint=tokensList.get(i).get(j).getIntegerValue();
                            //System.out.println("Integer: " + tokensList.get(i).get(j).getIntegerValue());
                            break;
                         case 4:
                            playerTemp.teamID=tokensList.get(i).get(j).getStringValue();
                            break;
                         case 5:
                            playerTemp.leagueID = tokensList.get(i).get(j).getStringValue();
                            break;
                         case 6:
                            playerTemp.wins=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 7:
                            playerTemp.losses=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 8:
                            playerTemp.gamesStarted=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 9:
                            playerTemp.completeGames=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 10:
                            playerTemp.shutouts=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 11:
                            playerTemp.saves=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 12:
                            playerTemp.outsPitched=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 13:
                            playerTemp.hits=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 14:
                            playerTemp.earnedRuns=tokensList.get(i).get(j).getIntegerValue();
                            break;
                          case 15:
                            playerTemp.homeRuns=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 16:
                            playerTemp.walks=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 17:
                            playerTemp.strikeOuts=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 18:
                            playerTemp.opponentsBattingAvg=tokensList.get(i).get(j).getIntegerValue();
                            break;
                          case 19:
                            playerTemp.earnedRunAvg=tokensList.get(i).get(j).getDoubleValue();
                            break;
                         case 20:
                            playerTemp.intentionalWalks=tokensList.get(i).get(j).getDoubleValue();
                            break;
                          case 21:
                            playerTemp.wildPitches=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 22:
                            playerTemp.battersHitByPitch=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 23:
                            playerTemp.balks=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 24:
                            playerTemp.balks=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 25:
                            playerTemp.battersFacedByPitcher=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 26:
                            playerTemp.gamesFinished=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         case 27:
                            playerTemp.runsAllowed=tokensList.get(i).get(j).getIntegerValue();
                            break;
                         default:
                            
            }
        }
        playerLists.add(playerTemp);
        }
        //showList(playerLists);
        return playerLists;
                              
        
    }
    
     private void viewData(List<List<Token>> tokensList, int columnData)
    {
        List<String> StringLists = new ArrayList();
        for(int i=1;i<tokensList.size();i++)            
        {
            for(int j=0;j<tokensList.get(i).size();j++)
            {
                if(tokensList.get(i).get(j).getTN()==columnData)
                    StringLists.add(tokensList.get(i).get(j).getStringValue());
            }
        }
        System.out.println("Dla kolumny: "+columnData+" mamy następujące dane: ");
        System.out.println(StringLists);
        
    }


    
}
