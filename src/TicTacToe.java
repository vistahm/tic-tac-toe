import java.util.*;

public class TicTacToe {
    static Scanner input = new Scanner(System.in);

    static ArrayList<Integer> playerPosition = new ArrayList<>();
    static ArrayList<Integer> cpuPosition = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);


        while (true) {
            System.out.print("Enter your placement: ");
            int playerPos = input.nextInt();
            while (playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)) {
                System.out.println("Place is taken already!");
                playerPos = input.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            System.out.println(checkWinner());
        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String player) {

        char symbol = ' ';
        if (player.equals("player")) {
            symbol = 'X';
            playerPosition.add(position);
        } else if (player.equals("cpu")) {
            symbol = 'O';
            cpuPosition.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;

            case 2:
                gameBoard[0][2] = symbol;
                break;

            case 3:
                gameBoard[0][4] = symbol;
                break;

            case 4:
                gameBoard[2][0] = symbol;
                break;

            case 5:
                gameBoard[2][2] = symbol;
                break;

            case 6:
                gameBoard[2][4] = symbol;
                break;

            case 7:
                gameBoard[4][0] = symbol;
                break;

            case 8:
                gameBoard[4][2] = symbol;
                break;

            case 9:
                gameBoard[4][4] = symbol;
                break;


            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);

        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);

        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPosition.containsAll(l)) {
               return "YOU WON!";
            } else if (cpuPosition.containsAll(l)) {
                return "YOU LOSE!";
            } else if (playerPosition.size() + cpuPosition.size() == 9-1) {
                return "DRAW";
            }
        }

        return "";
    }
}
