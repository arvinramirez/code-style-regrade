package style;

public class DeckImpl implements Deck {
	
	private Card[] cardArray;			
	private int dealNumber;

	public DeckImpl() {
		dealNumber = 52;
		cardArray = new Card[dealNumber];

		int cardIndex = 0;
		
		for (Card.Suit s : Card.Suit.values()) {
			for (int rank = 2; rank <= CardImpl.ACE; rank++) {
				cardArray[cardIndex] = new CardImpl(rank, s);
				cardIndex++;
			}
		}
		
		for (int i = 0; i < cardArray.length; i++) {
			int swapIndex = i + ((int) (Math.random() * (cardArray.length - i)));
			
			//to swap cards between indexes
			Card tmp = cardArray[i];
			cardArray[i] = cardArray[swapIndex];
			cardArray[swapIndex] = tmp;
		}		
	}
	
	public boolean hasHand() {
		boolean hasHand = false;
		if (dealNumber >= 5) {
			hasHand = true;
		}
		return hasHand;
	}

	public Card dealNextCard() {
		if (dealNumber == 0) {
			throw new RuntimeException("No more cards");
		}
		
		Card dealtCard = cardArray[nextUndealtIndex()];
		dealNumber -= 1;
		return dealtCard;
	}

	public PokerHand dealHand() {
		if (!hasHand()) {
			throw new RuntimeException("Deck does not have enough cards to deal another hand");
		}
		
		Card[] handCards = new Card[5];
		
		for (int i=0; i<handCards.length; i++) {
			handCards[i] = dealNextCard();
		}
		
		PokerHand hand = new PokerHandImpl(handCards);
		return hand;
	}	

	public void findAndRemove(Card c) {
		if (dealNumber == 0) {
			return;
		}
		
		for (int i = nextUndealtIndex(); i < 52; i++) {
			//to remove card
			if (cardArray[i].equals(c)) {
				Card tmp = cardArray[i];
				cardArray[i] = cardArray[nextUndealtIndex()];
				cardArray[nextUndealtIndex()] = tmp;
				dealNextCard();
				return;
			}
		}
		
		return;
	}
	
	private int nextUndealtIndex() {
		int undealtNumber = 52 - dealNumber;
		return undealtNumber;
	}
}
