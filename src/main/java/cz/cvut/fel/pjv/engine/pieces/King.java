package cz.cvut.fel.pjv.engine.pieces;

import com.google.common.collect.ImmutableList;
import cz.cvut.fel.pjv.engine.Colour;
import cz.cvut.fel.pjv.engine.board.Board;
import cz.cvut.fel.pjv.engine.board.moves.Move;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private boolean isCastled;

    public King(
                int pieceRow,
                int pieceColumn,
                Colour pieceColour) {
        super(PieceType.KING, pieceRow, pieceColumn, pieceColour);
        this.isCastled = false;
    }

    @Override
    public Collection<Move> calculateMoves(Board board) {
        final List<Move> legalMoves
                = new ArrayList<>(PieceMoveCalculator.calculateStraightSliderMoves(board,
                this, 1));
        legalMoves.addAll(PieceMoveCalculator.calculateDiagonalSliderMoves(board,
                this, 1));
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    public boolean isCastled() {
        return this.isCastled;
    }

    public void setCastled() {
        isCastled = true;
    }
}
