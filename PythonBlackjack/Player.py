class Player:

    def __init__(self, name, money):
        self.name = name
        self.hand = []
        self.money = money
        self.bet = 0

    def setHand(self, hand):
        self.hand = hand

    def printHand(self):
        print(self.name, ": [", sep="", end=" ")
        for card in self.hand:
            card.printCard()
        print("]", end="")

    def placeBet(self, bet):
        self.bet = bet
        self.money -= bet

    def addMoney(self, money):
        self.money += money

    def printPlayerInfo(self):
        print(self.name, " ($", self.money, ")", sep="")
