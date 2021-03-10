
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String team;
    private int assists;
    private int goals;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoals() {
        return goals;
    }

    public int getTotalScore() {
        return getAssists() + getGoals();
    }

    public String getTeam() {
        return team;
    }

    public String getAmountOfSpaces(int prettySpaces) {
        StringBuilder spaces = new StringBuilder();
        for (int i = name.length(); i <= prettySpaces; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    @Override
    public String toString() {
        return this.getName() + this.getAmountOfSpaces(20) +
                this.getTeam() + "   " + this.getGoals() + this.getAmountOfSpaces(2)
                + " + " + this.getAmountOfSpaces(2) +
                this.getAssists() + " = " + this.getTotalScore();
    }


    @Override
    public int compareTo(Player p1) {
        return p1.getTotalScore() - this.getTotalScore();
    }

}
