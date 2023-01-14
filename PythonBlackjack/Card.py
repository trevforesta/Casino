class Card:

    def __init__(self, value, suit):
        self.value = value
        self.suit = suit

    def printCard(self):
        if self.value == 1:
            print("A", self.suit, sep="", end=" ")
        elif self.value == 13:
            print("K", self.suit, sep="", end=" ")
        elif self.value == 12:
            print("Q", self.suit, sep="", end=" ")
        elif self.value == 11:
            print("J", self.suit, sep="", end=" ")
        else:
            print(self.value, self.suit, sep="", end=" ")
