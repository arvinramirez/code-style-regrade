package style;

public class PokerHandImpl implements PokerHand {

	private Card[] cardArray;

	public PokerHandImpl(Card[] cards) {
		
		if (cards == null) {
			throw new RuntimeException("card is a null  value");
			
		}
		
		if (cards.length != 5) {
			throw new RuntimeException("not enough cards");
			
		}
		
		for (int i=0; i<5; i++) {
			if (cards[i] == null) {
				throw new RuntimeException("Current card is null");
				
			}
		}

		cardArray = cards.clone();
		
		for (int i=0; i<5; i++) {			
			for (int j=i; j<5; j++) {
				if (cardArray[j].r() < cardArray[i].r()) {
					Card tmp = cardArray[i];
					cardArray[i] = cardArray[j];
					cardArray[j] = tmp;
				}
			}
		}
	}

	public Card[] getCards() {
		
		return cardArray.clone();
		
	}

	public boolean contains(Card c2) {
		
		for (int i=0; i<5; i++) {
			if (cardArray[i].equals(c2)) {
				return true;
				
			}
		}
		
		return false;
		
	}

	public boolean isFlush() {
		
		for (int i=1; i<5; i++) {
			if (cardArray[i].s() != cardArray[0].s()) {
				return false;
				
			}
		}
		
		return true;
	}

	public boolean isStraight() {
		
		boolean isStraight = true;
		for (int i=0; i<4; i++) {
			if (cardArray[i].r()+1 != cardArray[i+1].r()) {
				isStraight = false;
				break;
				
			}
		}
		
		return isStraight || isTheWheel();
		
	}

	private boolean isTheWheel() {
		
		if (cardArray[0].r() == 2) {
			if (cardArray[1].r() == 3) {
				if (cardArray[2].r() == 4) {
					if (cardArray[3].r() == 5) {
						if (cardArray[4].r() == 14) {
							return true;
							
						}
					}
				}
			}
		}
		
		return false;
		
	}

	public boolean isOnePair() {

		int pairIndex = startingPairAt(0);
		boolean noOtherPairs = startingPairAt(pairIndex+1) == -1;
		return ((pairIndex != -1) && noOtherPairs);
		
	}

	public boolean isTwoPair() {
		
		int firstPairIdx = startingPairAt(0);
		int secondPairIdx = startingPairAt(firstPairIdx+2);

		return ((firstPairIdx != -1) && (secondPairIdx != -1) && 
				!isFourOfAKind() && !isFullHouse());
	}

	public boolean isThreeOfAKind() {
		
		int firstPairIndex = startingPairAt(0);
		
		if ((firstPairIndex == -1) || (firstPairIndex == 3)) {
			return false;
			
		}
		
		return ((cardArray[firstPairIndex].r() == cardArray[firstPairIndex].r()) 
				&& !isFourOfAKind() && !isFullHouse());
		
	}

	public boolean isFullHouse() {
		
		boolean firstSecondEqual = ((cardArray[0].r() == cardArray[1].r()));
		boolean thirdFourthEqual = ((cardArray[2].r() == cardArray[3].r()));
		boolean fourthFifthEqual = ((cardArray[3].r() == cardArray[4].r()));
		boolean secondThirdEqual = ((cardArray[1].r() == cardArray[2].r()));
		
		return ((firstSecondEqual && thirdFourthEqual && fourthFifthEqual)
				|| (firstSecondEqual && secondThirdEqual && fourthFifthEqual));		
	}

	public boolean isFourOfAKind() {
		
		boolean firstSecondEqual = (cardArray[0].r() == cardArray[1].r());
		boolean secondThirdEqual = (cardArray[1].r() == cardArray[2].r());
		boolean thirdFourthEqual = (cardArray[2].r() == cardArray[3].r());
		boolean fourthFifthEqual = (cardArray[3].r() == cardArray[4].r());
		
		return ((firstSecondEqual && secondThirdEqual && thirdFourthEqual)||
				secondThirdEqual && thirdFourthEqual && fourthFifthEqual);		
	}	
	
	public boolean isStraightFlush() {
		
		if (isStraight() == true) {
			if (isFlush() == true) {
				return true;
			}
		}
		
		return false;
		
	}

	public int getHandRank() {
		
		if (isOnePair() == true) {
			return cardArray[startingPairAt(0)].r();
			
		} else if (isTwoPair() == true) {
			return cardArray[3].r();
			
		} else if (isThreeOfAKind() == true || isFourOfAKind() == true || isFullHouse() == true) {
			return cardArray[2].r();
			
		} else if (isTheWheel() == true) {
			return 5;
			
		} else {
			return cardArray[4].r();
			
		}
	}

	public int compareTo(PokerHand other) {
		
		if (getHandTypeValue() < other.getHandTypeValue()) {
			return -1;
			
		} else if (getHandTypeValue() > other.getHandTypeValue()) {
			return 1;
			
		} else {
			if (getHandRank() < other.getHandRank()) {
				return -1;
				
			} else if (getHandRank() > other.getHandRank()) {
				return 1;
				
			} else {
				return 0;
				
			}
		}
	}
	
	public int getHandTypeValue() {
		
		if (isStraightFlush()) return 9;
		
		if (isOnePair()) return 2;
		
		if (isTwoPair()) return 3;
		
		if (isThreeOfAKind()) return 4;
		
		if (isStraight()) return 5;
		
		if (isFlush()) return 6;
		
		if (isFullHouse()) return 7;
		
		if (isFourOfAKind()) return 8;
		
		return 1;
	}
	
	private int startingPairAt(int num) {		
		if (num < 0) num = 0;
		if (num >= 4) return -1;
		
		for (int i=num; i<4; i++) {
			if (cardArray[i].r() == cardArray[i+1].r()) 
			return i;
			
		}
		
		return -1;
		
	}
}
