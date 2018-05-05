package cz.cvut.fel.pjv.engine.board.moves;

import cz.cvut.fel.pjv.engine.board.Board;
import cz.cvut.fel.pjv.engine.board.Tile;
import cz.cvut.fel.pjv.engine.pieces.*;

import java.util.Objects;

public class CastleMove extends Move {

    private MoveType moveType;
    private Rook castlingRook;
    private int castlingRookOldRow;
    private int castlingRookOldColumn;
    private int castlingRookNewRow;
    private int castlingRookNewColumn;

    CastleMove(final Board board,
               final King castlingKing,
               final int castlingKingNewRow,
               final int castlingKingNewColumn,
               final Rook castlingRook,
               final int castlingRookOldRow,
               final int castlingRookOldColumn,
               final int castlingRookNewRow,
               final int castlingRookNewColumn) {
        super(board, castlingKing, castlingKingNewRow, castlingKingNewColumn);
        this.castlingRook = castlingRook;
        this.castlingRookOldRow = castlingRookOldRow;
        this.castlingRookOldColumn = castlingRookOldColumn;
        this.castlingRookNewRow = castlingRookNewRow;
        this.castlingRookNewColumn = castlingRookNewColumn;
        this.moveType = MoveType.CASTLE;
    }

    // Creates a new board
    @Override
    public void execute() {
        this.movedPiece.move(this);
        movedPiece.setFirstMove(false);
        this.getSourceTile().setPieceOnTile(null);
        this.getDestinationTile().setPieceOnTile(this.getMovedPiece());
        Tile oldRookTile = board.getTile(castlingRook.getPieceRow(), castlingRook.getPieceColumn());
        oldRookTile.setPieceOnTile(null);
        Tile newRookTile = board.getTile(castlingRookNewRow, castlingRookNewColumn);
        newRookTile.setPieceOnTile(castlingRook);
        this.castlingRook.setPieceRow(castlingRookNewRow);
        this.castlingRook.setPieceColumn(castlingRookNewColumn);
        Board.setMove(this.board.getCurrentPlayer().getOpponent().getColour());
        board.recalculate();
    }

    private Rook getCastlingRook() {
        return castlingRook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CastleMove)) return false;
        if (!super.equals(o)) return false;
        CastleMove that = (CastleMove) o;
        return castlingRookOldRow == that.castlingRookOldRow &&
                castlingRookOldColumn == that.castlingRookOldColumn &&
                castlingRookNewRow == that.castlingRookNewRow &&
                castlingRookNewColumn == that.castlingRookNewColumn &&
                Objects.equals(getCastlingRook(), that.getCastlingRook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCastlingRook(), castlingRookOldRow, castlingRookOldColumn, castlingRookNewRow, castlingRookNewColumn);
    }

    @Override
    public MoveType getMoveType() {
        return this.moveType;
    }
}