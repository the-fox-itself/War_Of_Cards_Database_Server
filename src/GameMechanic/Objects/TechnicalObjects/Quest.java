package GameMechanic.Objects.TechnicalObjects;

import GameMechanic.GameThreads;

import java.io.Serializable;

public class Quest implements Serializable {
    public int numberOfQuest;
    public String goal;
    public int goalNumber;
    public int reachedNumber;
    public boolean isCompleted = false;
    public String searchCardName;

    public Quest(int numberOfQuest, String goal, int goalNumber, String searchCardName) {
        System.out.println("Creating object of class Quest...");
        this.numberOfQuest = numberOfQuest;
        this.goal = goal;
        this.goalNumber = goalNumber;
        this.searchCardName = searchCardName;
        System.out.println("Finished creating object of class Quest.");
        System.out.println("Created quest:  numberOfQuest: " + numberOfQuest + ", goal: " + goal + ", goalNumber: " + goalNumber + ", reachCards: " + reachedNumber + ", isCompleted: " + isCompleted + ", searchCard: " + searchCardName);
    }
    public void runnableOn() {
        new GameThreads.QuestThread(this).start();
    }

    public int getNumberOfQuest() {
        return numberOfQuest;
    }

    public void setNumberOfQuest(int numberOfQuest) {
        this.numberOfQuest = numberOfQuest;
    }

    public String getSearchCardName() {
        return searchCardName;
    }

    public void setSearchCardName(String searchCardName) {
        this.searchCardName = searchCardName;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getGoalNumber() {
        return goalNumber;
    }

    public void setGoalNumber(int goalNumber) {
        this.goalNumber = goalNumber;
    }

    public int getReachedNumber() {
        return reachedNumber;
    }

    public void setReachedNumber(int reachedNumber) {
        this.reachedNumber = reachedNumber;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
