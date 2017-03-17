package wenyu.learning.OODesign;

import java.util.LinkedList;
import java.util.Queue;



public class SnakeGame {
	// Cell structure
	private static class Cell {
		public final static int CELL_TYPE_EMPTY = 0;
		public final static int CELL_TYPE_FOOD = 10;
		public final static int CELL_TYPE_SNAKE_NODE = 20;
	    final int row, col;
	    int type;
	    public Cell(int row, int col) {
	        this.row = row;
	        this.col = col;
	        this.type = CELL_TYPE_EMPTY;
	    }
	}
	
	// Board structure
	private static class Board {
	    private final int ROW_COUNT, COL_COUNT;
	    private final Cell[][] cells;
	    public Board(int rowCount, int columnCount) {
	        ROW_COUNT = rowCount;
	        COL_COUNT = columnCount;
	        cells = new Cell[ROW_COUNT][COL_COUNT];
	        for (int row=0; row<ROW_COUNT; row++) {
	            for (int column=0; column<COL_COUNT; column++) {
	                cells[row][column] = new Cell(row, column);
	            }
	        }
	    }
	    public void generateFood() {
	        int row = (int) (Math.random() * ROW_COUNT);
	        int column = (int) (Math.random() * COL_COUNT);
	        cells[row][column].type = Cell.CELL_TYPE_FOOD;
	    }
	}
	
	// Snake structure
	private static class Snake {
	    private final Queue<Cell> snakePartList = new LinkedList<Cell>();
	    private Cell head;
	    public Snake(Cell initPos) {
	        head = initPos;
	        snakePartList.add(head);
	    }
	    public void grow() {
	        snakePartList.add(head);
	    }
	    public void move(Cell nextCell) {
	        Cell tail = snakePartList.poll();
	        tail.type = Cell.CELL_TYPE_EMPTY;

	        head = nextCell;
	        snakePartList.offer(head);
	    }
	    public boolean checkCrash(Cell nextCell) {
	    	if(snakePartList.contains(nextCell)) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	}
	
	// Route structure
	private static class Router {
	    public static final int DIRECTION_NONE = 0;
	    public static final int DIRECTION_RIGHT = 1;
	    public static final int DIRECTION_LEFT = -1;
	    public static final int DIRECTION_UP = 2;
	    public static final int DIRECTION_DOWN = -2;
	    private Snake snake;
	    private Board board;
	    private int direction;
	    private boolean gameOver;
	    public Router(Snake snake, Board board) {
	        this.snake = snake;
	        this.board = board;
	    }
	    public void setDirection(int direction) {
	        this.direction = direction;
	    }
	    public void update() {
	        if (!gameOver) {
	            if (direction != DIRECTION_NONE) {
	                Cell nextCell = getNextCell(snake.head, board);
	                if(nextCell == null) {
	                	gameOver = true;
	                } else if (snake.checkCrash(nextCell)) {
	                    setDirection(DIRECTION_NONE);
	                    gameOver = true;
	                } else {
	                    snake.move(nextCell);
	                    if (nextCell.type == Cell.CELL_TYPE_FOOD) {
	                        snake.grow();
	                        board.generateFood();
	                    }
	                }
	            }
	        }
	    }

	    private Cell getNextCell(Cell currentPosition, Board board) {
	        int row = currentPosition.row;
	        int col = currentPosition.col;
	        if (direction == DIRECTION_RIGHT) {
	            col++;
	        } else if (direction == DIRECTION_LEFT) {
	            col--;
	        } else if (direction == DIRECTION_UP) {
	            row--;
	        } else if (direction == DIRECTION_DOWN) {
	            row++;
	        }
	        if(row<0 || row>=board.ROW_COUNT || col<0 || col>=board.COL_COUNT) {
	        	return null;
	        } else {
	        	Cell nextCell = board.cells[row][col];
		        return nextCell;
	        }
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
