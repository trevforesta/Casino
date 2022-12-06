import random
import time

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

class Blackjack:

    def __init__(self):
        self.deck = []
        self.dealerHand = []
        self.playerList = []
        self.fillDeck()
        self.shuffleDeck()

    def addPlayer(self, player):
        self.playerList.append(player)

    def dealPlayerCard(self, player):
        player.hand.append(self.deck.pop(0))

    def dealDealerCard(self):
        self.dealerHand.append(self.deck.pop(0))

    def getPlayerHandTotal(self, player):
        playerHandTotal = 0
        hasAce = False
        for card in player.hand:
            if card.value == 11 or card.value == 12 or card.value == 13:
                playerHandTotal += 10
            elif card.value == 1:
                playerHandTotal += 11
                hasAce = True
            else:
                playerHandTotal += card.value
        if playerHandTotal > 21 and hasAce:
            playerHandTotal -= 10
        return playerHandTotal

    def getDealerHandTotal(self):
        dealerHandTotal = 0
        hasAce = False
        for card in self.dealerHand:
            if card.value == 11 or card.value == 12 or card.value == 13:
                dealerHandTotal += 10
            elif card.value == 1:
                dealerHandTotal += 11
                hasAce = True
            else:
                dealerHandTotal += card.value
        if dealerHandTotal > 21 and hasAce:
            dealerHandTotal -= 10
        return dealerHandTotal
    
    def isPlayerHandBust(self, player):
        if self.getPlayerHandTotal(player) > 21:
            return True
        return False

    def isPlayerHand21(self, player):
        if self.getPlayerHandTotal(player) == 21:
            return True
        return False

    def isDealerHandBust(self):
        if self.getDealerHandTotal() > 21:
            return True
        return False

    def isDealerHand21(self):
        if self.getDealerHandTotal() == 21:
            return True
        return False

    def isDealerHand17(self):
        if self.getDealerHandTotal() > 17:
            return True
        return False

    def resetAllHands(self):
        for player in self.playerList:
            player.hand.clear()
        self.dealerHand.clear()

    def dealTableCards(self):
        for player in self.playerList:
            self.dealPlayerCard(player)
        self.dealDealerCard()
        for player in self.playerList:
            self.dealPlayerCard(player)
        self.dealDealerCard()
        
    def printPartialDealerHand(self):
        print("Dealer: [", end=" ")
        self.dealerHand[0].printCard()
        print("-- ]", end="")

    def printFullDealerHand(self):
        print("Dealer: [", end=" ")
        for card in self.dealerHand:
            card.printCard()
        print("]", end="")

    def fillDeck(self):
        for value in range(1, 14):
            card = Card(value, "S")
            self.deck.append(card)
        for value in range(1, 14):
            card = Card(value, "D")
            self.deck.append(card)
        for value in range(13, 0, -1):
            card = Card(value, "C")
            self.deck.append(card)
        for value in range(13, 0, -1):
            card = Card(value, "H")
            self.deck.append(card)

    def shuffleDeck(self):
        random.shuffle(self.deck)

    def resetDeck(self):
        self.deck.clear()
        self.fillDeck()
        self.shuffleDeck()

    def printDeck(self):
        print("[", end=" ")
        for card in self.deck:
            card.printCard()
        print("]")

    def printAllMoney(self):
        print(self.playerList[0].name, " ($", self.playerList[0].money, ")", sep="", end="")
        for value in range(1, len(self.playerList)):
            print(", ", end="")
            print(self.playerList[value].name, " ($", self.playerList[value].money, ")", sep="", end="")
        print()

    def printAllBets(self):
        print(self.playerList[0].name, " ($", self.playerList[0].money, "): $", self.playerList[0].bet, sep="", end="")
        for value in range(1, len(self.playerList)):
            print(", ", end="")
            print(self.playerList[value].name, " ($", self.playerList[value].money, "): $", self.playerList[value].bet, sep="")
        print()

    def printAllHands(self):
        self.printPartialDealerHand()
        for player in self.playerList:
            print(", ", end="")
            player.printHand()
        print()

    def playerTurn(self, player):
        while True:
            playerAction = input(player.name + " take your action: ")
            if playerAction is "H":
                self.dealPlayerCard(player)
                player.printHand()
                print()
                if (self.isPlayerHandBust(player)):
                    print("You've bust out!")
                    player.bet = 0
                    return
                break
            elif playerAction is "S":
                return
            else:
                print("That is not proper input!")

    def runGame(self):
        print("Welcome to the Blackjack table!")
        playerCount = input("How many players are joining? ")
        playerCount = int(playerCount)
        for value in range(1, playerCount + 1):
            print("Player", value, end=" ")
            playerName = input ("please enter your name: ")
            playerMoney = input("How much money do you want to start with? ")
            playerMoney = int(playerMoney)
            newPlayer = Player(playerName, playerMoney)
            self.addPlayer(newPlayer)
        self.printAllMoney()
        while self.playerList:
            for player in self.playerList:
                playerBet = input(player.name + " place your bet: ")
                playerBet = int(playerBet)
                if playerBet > player.money:
                    print("Insufficient funds! Placing maximum bet.")
                    player.placeBet(player.money)
                else:
                    player.placeBet(playerBet)
            self.printAllBets()
            print("Dealing cards...")
            time.sleep(1)
            self.dealTableCards()
            self.printAllHands()
            time.sleep(2)
            if self.dealerHand[0].value == 1 or self.dealerHand[0].value == 10 or self.dealerHand[0].value == 11 or self.dealerHand[0].value == 12 or self.dealerHand[0].value == 13:
                print("Dealer is checking for blackjack...")
                time.sleep(2)
                if self.isDealerHand21():
                    self.printFullDealerHand()
                    print("Dealer has blackjack!")
                else:
                    print("Dealer does not have blackjack.")
            for player in self.playerList:
                if self.isPlayerHand21(player):
                    print(player.getName() + " has blackjack!")
            for player in self.playerList:
                if not self.isPlayerHand21(player):
                    self.playerTurn(player)
            self.printFullDealerHand()
            print()
            time.sleep(2)
            while not self.isDealerHand17():
                print("Dealer hits.")
                time.sleep(1)
                self.dealDealerCard()
                self.printFullDealerHand()
                print()
                time.sleep(2)
            if self.isDealerHandBust():
                print("Dealer has gone bust!")
                time.sleep(2)
            else:
                print("Dealer stands.")
                time.sleep(2)
            if self.isDealerHandBust():
                for player in self.playerList:
                    if not self.isPlayerHandBust(player):
                        if self.isPlayerHand21(player) and len(player.hand) == 2:
                            print(player.name + " has blackjack which pays 3 to 2!")
                            player.addMoney((player.bet * 1.5))
                        else:
                            print(player.name + " won!")
                            player.addMoney((player.bet * 2))
            else:
                for player in self.playerList:
                    if self.isDealerHand21() and len(self.dealerHand) == 2:
                        if self.isPlayerHand21(player) and len(player.hand) == 2:
                            print(player.name + " pushed.")
                            player.addMoney(player.bet)
                        elif self.getPlayerHandTotal(player) < self.getDealerHandTotal():
                            print(player.name + " lost!")
                            player.bet = 0
                    elif not self.isPlayerHandBust(player):
                        if self.isPlayerHand21(player) and len(player.hand) == 2:
                            print(player.name + " has blackjack which pays 3 to 2!")
                            player.addMoney((player.bet * 1.5))
                        else:
                            if self.getPlayerHandTotal(player) > self.getDealerHandTotal():
                                print(player.name + " won!")
                                player.addMoney((player.bet * 2))
                            elif self.getPlayerHandTotal(player) < self.getDealerHandTotal():
                                print(player.name + " lost!")
                                player.bet = 0
                            else:
                                print(player.name + " pushed.")
                                player.addMoney(player.bet)
            self.printAllMoney()
            for player in self.playerList:
                if player.money < 1:
                    self.playerList.remove(player)
            self.resetAllHands()
            self.resetDeck()
        time.sleep(2)
        print("No players at the table! Exiting...")

game = Blackjack()
game.runGame()