package style;

public class CardImpl implements Card {

	private static String[] stringArray = {null, null, "Two", "Three", "Four", "Five", "Six",
			"Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	
	// Card values after 1-10
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;

	private int rank;
	private Card.Suit suit;

	public CardImpl(int rank, Card.Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int r() {
		return rank;
	}
	
	public Suit s() {
		return suit;
	}
	
	public boolean equals(Card other) {
		int rank = this.rank;
		Card.Suit suit = this.suit;
		
		return (rank == other.r() && suit == other.s());
	}
	
	public String toString() {
		//Returns a string representation of the card
		return stringArray[r()] + " of " + Card.suitToString(s());
	}
}
