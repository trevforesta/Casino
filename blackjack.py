

# python is dumb and stuipid and i hate it

import random

cardValues = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
cardSuits = ["clubs", "diamonds", "hearts", "spades"]
faceCards = {"J": 11, "Q": 12, "K": 13, "A": 14,
    11: "J", 12: "Q", 13: "K", 14: "A"}

class Card:
    def __init__(self, value, suit):
        self.value = value
        self.suit = suit
    
    def printCard(self):
        print(self.value, self.suit, end = "")

class Deck:
    def __init__(self):
        self.deck = []
        self.fillDeck()

    def fillDeck(self):
        for value in cardValues:
            for suit in cardSuits:
                if value in faceCards:
                    newCard = Card(faceCards[value], suit)
                else:
                    newCard = Card(value, suit)
                self.deck.append(newCard)
    
    def shuffleDeck(self):
        random.shuffle(self.deck)

    def printDeck(self):
        for card in self.deck:
            card.printCard()
    
    def dealCards(self, players):
        for player in players:
            player.giveCard(self.deck.pop(0))
        for player in players:
            player.giveCard(self.deck.pop(0))

class Player:
    def __init__(self, name, isDealer):
        self.name = name
        self.isDealer = isDealer
        self.hand = []

    def giveCard(self, card):
        self.hand.append(card)

    def printHand(self):
        print(self.name, "has")
        for card in self.hand:
            card.printCard()
        print()

deck = Deck()
deck.shuffleDeck()
#deck.printDeck()
player1 = Player("Logan", False)
player2 = Player("Andrew", False)
deck.dealCards([player1, player2])
player1.printHand()
player2.printHand()
