package PracticeTetris;

public class Shape {

	enum Tetrominose {

		NoShape(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }),
		ZShape(new int[][] { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }),
		SShape(new int[][] { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }),
		LineShape(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }),
		TShape(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }),
		SquareShape(new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }),
		LShape(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }),
		MirroedShape(new int[][] { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } });

		public int[][] coords;

		private Tetrominose(int[][] coords) {
			// TODO Auto-generated constructor stub
			this.coords = coords;
		}
	}
	
	private Tetrominose pieceShape;
	private int[][] coords;
	
	public Shape() {
		coords = new int[4][2];
	}
	
	public void setShape (Tetrominose shape) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				
			}
		}
	}

}
