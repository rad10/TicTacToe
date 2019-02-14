
from sys import exit
board = [
    [" "," "," "],
    [" "," "," "],
    [" "," "," "]
]
def error():
    print("Error: that is not allowed")
    exit()
def construct():
    print("\239a")
    print("    A   B   C")
    for row in range(0, 3):
        print("  +---+---+---+")
        print(str(row), end='')
        for col in range(0,3):
            print(" | "+board[row][col], end='')
        print(" |")
    print("  +---+---+---+")
def chooseCoord(tile):
    tile = str(tile)
    coord = str(input("Choose Coordinates: "))
    row = int(coord[1])
    if(row>2):
        chooseCoord(tile)
        return
    col = 0
    if(str(coord[0]).upper()=="A"): col = 0
    elif(str(coord[0]).upper()=="B"): col = 1
    elif(str(coord[0]).upper()=="C"): col = 2
    else:
        chooseCoord(tile)
        return
    if(board[row][col] == " "): board[row][col]=tile
    else: chooseCoord(tile)
def decideWin():
    x = 0
    o = 0
    s = 0
    for row in range(0,3):
        x=0
        o=0
        for col in range(0,3):
            x += 1 if (board[row][col]=="x") else 0
            o += 1 if (board[row][col]=="o") else 0
        if (x==3): return "x"
        elif (o==3): return "o"
    for col in range(0,3):
        x=0
        o=0
        for row in range(0,3):
            x += 1 if (board[row][col]=="x") else 0
            o += 1 if (board[row][col]=="o") else 0
        if (x==3): return "x"
        elif (o==3): return "o"
    for row in range(0,3):
        for col in range(0,3):
            s += 1 if (board[row][col]==" ") else 0
    if((board[0][0]=="x" and board[1][1]=="x" and board[2][2]=="x") or (board[0][2]=="x" and board[1][1]=="x" and board[2][0]=="x")): return "x"
    elif((board[0][0]=="o" and board[1][1]=="o" and board[2][2]=="o") or (board[0][2]=="o" and board[1][1]=="o" and board[2][0]=="o")): return "o"
    elif(s == 0): return "nobody"
    else: return "e"
def main():
    construct()
    while (True):
        chooseCoord("x")
        construct()
        #print(decideWin())
        if(decideWin()!="e"): break
        chooseCoord("o")
        construct()
        #print(decideWin())
        if(decideWin()!="e"): break
    print(decideWin()+" is the winner!")
main()