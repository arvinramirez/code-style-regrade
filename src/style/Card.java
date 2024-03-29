package style;

public interface Card {
	
	public enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS};
	
		int r();
		Card.Suit s();
		String toString();
		boolean equals(Card other);
		
		public static String suitToString(Card.Suit s) {
			switch (s) {
				case SPADES:return "Spades";
				case HEARTS:return "Hearts";
				case DIAMONDS:return "Diamonds";
				case CLUBS:return "Clubs";}
				return null;
			}
	}