package cz.cvut.fel.pjv.pieces;

//    string feedback for printing pieces on board
public enum PieceType {

    PAWN("[P]"),
    KNIGHT("[N]"),
    BISHOP("[B]"),
    ROOK("[R]"),
    QUEEN("[Q]"),
    KING("[K]");

    private String pieceName;

    PieceType(final String pieceName) {
        this.pieceName = pieceName;
    }

    @Override
    public String toString() {
        return this.pieceName;
    }
}