package Game;

public class Player implements Comparable<Player> {
private String color;
	public Player(String color) {
		this.color = color;
	}
	public int compareTo(Player a) {
		if (color.equals(a.getColor()))
			return 0;
		else
			return 1;
	}
	public String getColor() {
		return color;
	}
}
