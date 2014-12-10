/*
 * Klasa będąca szablonem danych atrybutów zawodników z których korzystamy w programie
 * 
 */
package data;

/**
 *
 * @author Ariel
 */
public class PlayerStat {
    
    String playerID;
    int year;
    int gamesStarted;
    int completeGames;
    int earnedRuns;
    int homeRuns;
    int wins;
    int losses;
    int walks;
    int strikeOuts;
    int hits;
    int opponentsBattingAvg;

    public int getCompleteGames() {
        return completeGames;
    }

    public void setCompleteGames(int completeGames) {
        this.completeGames = completeGames;
    }

    public int getEarnedRuns() {
        return earnedRuns;
    }

    public void setEarnedRuns(int earnedRuns) {
        this.earnedRuns = earnedRuns;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public void setGamesStarted(int gamesStarted) {
        this.gamesStarted = gamesStarted;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getHomeRuns() {
        return homeRuns;
    }

    public void setHomeRuns(int homeRuns) {
        this.homeRuns = homeRuns;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getOpponentsBattingAvg() {
        return opponentsBattingAvg;
    }

    public void setOpponentsBattingAvg(int opponentsBattingAvg) {
        this.opponentsBattingAvg = opponentsBattingAvg;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getStrikeOuts() {
        return strikeOuts;
    }

    public void setStrikeOuts(int strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    public int getWalks() {
        return walks;
    }

    public void setWalks(int walks) {
        this.walks = walks;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public int getValueByStringName(String name)
    {
        if(name.equalsIgnoreCase("year")) return this.year;
        if(name.equalsIgnoreCase("gamesStarted")) return this.gamesStarted;
        if(name.equalsIgnoreCase("completeGames")) return this.completeGames;
        if(name.equalsIgnoreCase("earnedRuns")) return this.earnedRuns;
        if(name.equalsIgnoreCase("homeRuns")) return this.homeRuns;
        if(name.equalsIgnoreCase("wins")) return this.wins;
        if(name.equalsIgnoreCase("losses")) return this.losses;
        if(name.equalsIgnoreCase("walks")) return this.walks;
        if(name.equalsIgnoreCase("strikeOuts")) return this.strikeOuts;
        if(name.equalsIgnoreCase("hits")) return this.hits;
        if(name.equalsIgnoreCase("opponentsBattingAvg")) return this.opponentsBattingAvg;
        return 0;
    }
    
    
}
